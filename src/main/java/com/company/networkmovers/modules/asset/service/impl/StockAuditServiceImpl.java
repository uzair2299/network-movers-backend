package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.dto.request.StockAuditItemRequest;
import com.company.networkmovers.modules.asset.dto.request.StockAuditRequest;
import com.company.networkmovers.modules.asset.dto.response.StockAuditResponse;
import com.company.networkmovers.modules.asset.entity.*;
import com.company.networkmovers.modules.asset.mapper.StockAuditMapper;
import com.company.networkmovers.modules.asset.repository.AssetLocationRepository;
import com.company.networkmovers.modules.asset.repository.AssetRepository;
import com.company.networkmovers.modules.asset.repository.StockAuditRepository;
import com.company.networkmovers.modules.asset.service.AssetStockService;
import com.company.networkmovers.modules.asset.service.StockAuditService;
import com.company.networkmovers.shared.dto.RequestParamDto;
import com.company.networkmovers.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StockAuditServiceImpl implements StockAuditService {

    private final StockAuditRepository repository;
    private final StockAuditMapper mapper;
    private final AssetRepository assetRepository;
    private final AssetLocationRepository locationRepository;
    private final AssetStockService stockService;

    @Override
    @Transactional
    public StockAuditResponse create(StockAuditRequest request) {
        StockAudit entity = mapper.toEntity(request);
        entity.setStatus(request.getStatus().toUpperCase());

        List<StockAuditItem> items = new ArrayList<>();
        if (request.getItems() != null) {
            for (StockAuditItemRequest itemReq : request.getItems()) {
                Asset asset = assetRepository.findById(itemReq.getAssetId())
                        .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + itemReq.getAssetId()));
                AssetLocation location = locationRepository.findById(itemReq.getLocationId())
                        .orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + itemReq.getLocationId()));

                StockAuditItem item = StockAuditItem.builder()
                        .stockAudit(entity)
                        .asset(asset)
                        .location(location)
                        .expectedQuantity(itemReq.getExpectedQuantity())
                        .actualQuantity(itemReq.getActualQuantity())
                        .discrepancy(itemReq.getActualQuantity().subtract(itemReq.getExpectedQuantity()))
                        .remarks(itemReq.getRemarks())
                        .build();
                items.add(item);
            }
        }
        entity.setItems(items);

        StockAudit saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public StockAuditResponse update(UUID id, StockAuditRequest request) {
        StockAudit entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockAudit not found with id " + id));

        entity.setAuditDate(request.getAuditDate());
        entity.setAuditorId(request.getAuditorId());
        entity.setRemarks(request.getRemarks());

        entity.getItems().clear();
        if (request.getItems() != null) {
            for (StockAuditItemRequest itemReq : request.getItems()) {
                Asset asset = assetRepository.findById(itemReq.getAssetId())
                        .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + itemReq.getAssetId()));
                AssetLocation location = locationRepository.findById(itemReq.getLocationId())
                        .orElseThrow(() -> new ResourceNotFoundException("Location not found with id " + itemReq.getLocationId()));

                StockAuditItem item = StockAuditItem.builder()
                        .stockAudit(entity)
                        .asset(asset)
                        .location(location)
                        .expectedQuantity(itemReq.getExpectedQuantity())
                        .actualQuantity(itemReq.getActualQuantity())
                        .discrepancy(itemReq.getActualQuantity().subtract(itemReq.getExpectedQuantity()))
                        .remarks(itemReq.getRemarks())
                        .build();
                entity.getItems().add(item);
            }
        }

        if (!"COMPLETED".equalsIgnoreCase(entity.getStatus())) {
            entity.setStatus(request.getStatus().toUpperCase());
        }

        StockAudit saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public StockAuditResponse getById(UUID id) {
        return repository.findByIdWithDetails(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("StockAudit not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<StockAuditResponse> getAll(RequestParamDto requestParams) {
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
        return repository.findAllActive(pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public StockAuditResponse updateStatus(UUID id, String status) {
        StockAudit entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockAudit not found with id " + id));

        String oldStatus = entity.getStatus();
        String newStatus = status.toUpperCase();

        if ("COMPLETED".equalsIgnoreCase(oldStatus)) {
            throw new IllegalStateException("Cannot change status of an already COMPLETED stock audit.");
        }

        entity.setStatus(newStatus);

        if ("COMPLETED".equalsIgnoreCase(newStatus)) {
            // Apply reconciliation stock adjustments
            for (StockAuditItem item : entity.getItems()) {
                stockService.adjustStock(
                        item.getAsset().getId(),
                        item.getLocation().getId(),
                        item.getActualQuantity(),
                        "ADJUST",
                        "STOCK_AUDIT",
                        entity.getId(),
                        "Audit reconciliation adjustment. Discrepancy: " + item.getDiscrepancy()
                );
            }
        }

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        StockAudit entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StockAudit not found with id " + id));
        entity.delete(null);
        repository.save(entity);
    }
}
