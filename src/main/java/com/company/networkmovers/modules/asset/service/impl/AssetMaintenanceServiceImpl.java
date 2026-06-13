package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.dto.request.AssetMaintenanceRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetMaintenanceResponse;
import com.company.networkmovers.modules.asset.entity.*;
import com.company.networkmovers.modules.asset.mapper.AssetMaintenanceMapper;
import com.company.networkmovers.modules.asset.repository.AssetRepository;
import com.company.networkmovers.modules.asset.repository.AssetMaintenanceRepository;
import com.company.networkmovers.modules.asset.service.AssetStockService;
import com.company.networkmovers.modules.asset.service.AssetMaintenanceService;
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
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssetMaintenanceServiceImpl implements AssetMaintenanceService {

    private final AssetMaintenanceRepository repository;
    private final AssetMaintenanceMapper mapper;
    private final AssetRepository assetRepository;
    private final AssetStockService stockService;

    @Override
    @Transactional
    public AssetMaintenanceResponse create(AssetMaintenanceRequest request, UUID locationId) {
        Asset asset = assetRepository.findById(request.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + request.getAssetId()));

        AssetMaintenance entity = mapper.toEntity(request);
        entity.setAsset(asset);
        entity.setStatus(request.getStatus().toUpperCase());

        AssetMaintenance saved = repository.save(entity);

        if ("IN_PROGRESS".equalsIgnoreCase(saved.getStatus()) && locationId != null) {
            stockService.adjustStock(
                    asset.getId(),
                    locationId,
                    BigDecimal.ONE,
                    "OUT",
                    "MAINTENANCE",
                    saved.getId(),
                    "Asset sent to maintenance (" + saved.getMaintenanceType() + ")"
            );
        }

        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public AssetMaintenanceResponse update(UUID id, AssetMaintenanceRequest request) {
        AssetMaintenance entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetMaintenance not found with id " + id));

        Asset asset = assetRepository.findById(request.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + request.getAssetId()));

        entity.setAsset(asset);
        entity.setMaintenanceType(request.getMaintenanceType());
        entity.setDescription(request.getDescription());
        entity.setScheduledDate(request.getScheduledDate());
        entity.setStartDate(request.getStartDate());
        entity.setCompletionDate(request.getCompletionDate());
        entity.setCost(request.getCost());
        entity.setPerformedBy(request.getPerformedBy());
        entity.setRemarks(request.getRemarks());

        if (!"COMPLETED".equalsIgnoreCase(entity.getStatus())) {
            entity.setStatus(request.getStatus().toUpperCase());
        }

        AssetMaintenance saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AssetMaintenanceResponse getById(UUID id) {
        return repository.findByIdWithDetails(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("AssetMaintenance not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssetMaintenanceResponse> getAll(RequestParamDto requestParams) {
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
        UUID assetId = null;
        if (requestParams.getSearch() != null && !requestParams.getSearch().isBlank()) {
            try {
                assetId = UUID.fromString(requestParams.getSearch().trim());
            } catch (IllegalArgumentException e) {
                // Not a UUID
            }
        }
        return repository.findAllActive(assetId, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public AssetMaintenanceResponse updateStatus(UUID id, String status, UUID locationId) {
        AssetMaintenance entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetMaintenance not found with id " + id));

        String oldStatus = entity.getStatus();
        String newStatus = status.toUpperCase();

        if ("COMPLETED".equalsIgnoreCase(oldStatus)) {
            throw new IllegalStateException("Cannot change status of completed maintenance.");
        }

        entity.setStatus(newStatus);

        if ("COMPLETED".equalsIgnoreCase(newStatus)) {
            if (locationId == null) {
                throw new IllegalArgumentException("Location ID is required when completed maintenance assets return to stock.");
            }
            entity.setCompletionDate(LocalDate.now());

            stockService.adjustStock(
                    entity.getAsset().getId(),
                    locationId,
                    BigDecimal.ONE,
                    "IN",
                    "MAINTENANCE",
                    entity.getId(),
                    "Asset returned from maintenance"
            );
        }

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        AssetMaintenance entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetMaintenance not found with id " + id));
        entity.delete(null);
        repository.save(entity);
    }
}
