package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.dto.request.MoveAssetUsageItemRequest;
import com.company.networkmovers.modules.asset.dto.request.MoveAssetUsageRequest;
import com.company.networkmovers.modules.asset.dto.response.MoveAssetUsageResponse;
import com.company.networkmovers.modules.asset.entity.*;
import com.company.networkmovers.modules.asset.mapper.MoveAssetUsageMapper;
import com.company.networkmovers.modules.asset.repository.AssetRepository;
import com.company.networkmovers.modules.asset.repository.MoveAssetUsageRepository;
import com.company.networkmovers.modules.asset.service.AssetStockService;
import com.company.networkmovers.modules.asset.service.MoveAssetUsageService;
import com.company.networkmovers.shared.dto.RequestParamDto;
import com.company.networkmovers.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MoveAssetUsageServiceImpl implements MoveAssetUsageService {

    private final MoveAssetUsageRepository repository;
    private final MoveAssetUsageMapper mapper;
    private final AssetRepository assetRepository;
    private final AssetStockService stockService;

    @Override
    @Transactional
    public MoveAssetUsageResponse create(MoveAssetUsageRequest request) {
        MoveAssetUsage entity = mapper.toEntity(request);
        entity.setStatus(request.getStatus().toUpperCase());

        List<MoveAssetUsageItem> items = new ArrayList<>();
        if (request.getItems() != null) {
            for (MoveAssetUsageItemRequest itemReq : request.getItems()) {
                Asset asset = assetRepository.findById(itemReq.getAssetId())
                        .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + itemReq.getAssetId()));

                MoveAssetUsageItem item = MoveAssetUsageItem.builder()
                        .moveAssetUsage(entity)
                        .asset(asset)
                        .quantity(itemReq.getQuantity())
                        .returnedQuantity(BigDecimal.ZERO)
                        .conditionOnIssue(itemReq.getConditionOnIssue())
                        .status(itemReq.getStatus().toUpperCase())
                        .build();
                items.add(item);
            }
        }
        entity.setItems(items);

        MoveAssetUsage saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public MoveAssetUsageResponse update(UUID id, MoveAssetUsageRequest request) {
        MoveAssetUsage entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MoveAssetUsage not found with id " + id));

        entity.setBookingId(request.getBookingId());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setRemarks(request.getRemarks());

        entity.getItems().clear();
        if (request.getItems() != null) {
            for (MoveAssetUsageItemRequest itemReq : request.getItems()) {
                Asset asset = assetRepository.findById(itemReq.getAssetId())
                        .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + itemReq.getAssetId()));

                MoveAssetUsageItem item = MoveAssetUsageItem.builder()
                        .moveAssetUsage(entity)
                        .asset(asset)
                        .quantity(itemReq.getQuantity())
                        .returnedQuantity(BigDecimal.ZERO)
                        .conditionOnIssue(itemReq.getConditionOnIssue())
                        .status(itemReq.getStatus().toUpperCase())
                        .build();
                entity.getItems().add(item);
            }
        }

        if (!"RETURNED".equalsIgnoreCase(entity.getStatus())) {
            entity.setStatus(request.getStatus().toUpperCase());
        }

        MoveAssetUsage saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MoveAssetUsageResponse getById(UUID id) {
        return repository.findByIdWithDetails(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("MoveAssetUsage not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MoveAssetUsageResponse> getAll(RequestParamDto requestParams) {
        String[] sortParams = requestParams.getSort().split(",");
        String sortField = sortParams[0];
        Sort.Direction direction = Sort.Direction.ASC;
        if (sortParams.length > 1 && "desc".equalsIgnoreCase(sortParams[1])) {
            direction = Sort.Direction.DESC;
        }
        Pageable pageable = PageRequest.of(
                requestParams.getPage(),
                requestParams.getSize(),
                Sort.by(direction, sortField)
        );
        UUID bookingId = null;
        if (requestParams.getSearch() != null && !requestParams.getSearch().isBlank()) {
            try {
                bookingId = UUID.fromString(requestParams.getSearch().trim());
            } catch (IllegalArgumentException e) {
                // Not a UUID, search remains null
            }
        }
        return repository.findAllActive(bookingId, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public MoveAssetUsageResponse updateStatus(UUID id, String status, UUID locationId) {
        MoveAssetUsage entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MoveAssetUsage not found with id " + id));

        String oldStatus = entity.getStatus();
        String newStatus = status.toUpperCase();

        if ("RETURNED".equalsIgnoreCase(oldStatus)) {
            throw new IllegalStateException("Cannot change status of an already RETURNED move asset usage.");
        }

        entity.setStatus(newStatus);

        if ("ISSUED".equalsIgnoreCase(newStatus) && !"ISSUED".equalsIgnoreCase(oldStatus)) {
            if (locationId == null) {
                throw new IllegalArgumentException("Location ID is required when issuing assets.");
            }
            // Decrement stock
            for (MoveAssetUsageItem item : entity.getItems()) {
                stockService.adjustStock(
                        item.getAsset().getId(),
                        locationId,
                        item.getQuantity(),
                        "OUT",
                        "BOOKING",
                        entity.getId(),
                        "Issued assets for booking: " + entity.getBookingId()
                );
            }
        } else if ("RETURNED".equalsIgnoreCase(newStatus)) {
            if (locationId == null) {
                throw new IllegalArgumentException("Location ID is required when returning assets.");
            }
            // Increment stock back
            for (MoveAssetUsageItem item : entity.getItems()) {
                item.setReturnedQuantity(item.getQuantity());
                item.setStatus("RETURNED");
                stockService.adjustStock(
                        item.getAsset().getId(),
                        locationId,
                        item.getQuantity(),
                        "IN",
                        "BOOKING",
                        entity.getId(),
                        "Returned assets from booking: " + entity.getBookingId()
                );
            }
        }

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        MoveAssetUsage entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("MoveAssetUsage not found with id " + id));
        entity.delete(null);
        repository.save(entity);
    }
}
