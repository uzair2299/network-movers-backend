package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.dto.request.DamageReportRequest;
import com.company.networkmovers.modules.asset.dto.response.DamageReportResponse;
import com.company.networkmovers.modules.asset.entity.*;
import com.company.networkmovers.modules.asset.mapper.DamageReportMapper;
import com.company.networkmovers.modules.asset.repository.AssetRepository;
import com.company.networkmovers.modules.asset.repository.DamageReportRepository;
import com.company.networkmovers.modules.asset.service.DamageReportService;
import com.company.networkmovers.shared.dto.RequestParamDto;
import com.company.networkmovers.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DamageReportServiceImpl implements DamageReportService {

    private final DamageReportRepository repository;
    private final DamageReportMapper mapper;
    private final AssetRepository assetRepository;

    @Override
    @Transactional
    public DamageReportResponse create(DamageReportRequest request) {
        Asset asset = assetRepository.findById(request.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + request.getAssetId()));

        DamageReport entity = mapper.toEntity(request);
        entity.setAsset(asset);
        entity.setStatus(request.getStatus().toUpperCase());

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public DamageReportResponse update(UUID id, DamageReportRequest request) {
        DamageReport entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DamageReport not found with id " + id));

        Asset asset = assetRepository.findById(request.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + request.getAssetId()));

        entity.setAsset(asset);
        entity.setReportedBy(request.getReportedBy());
        entity.setDamageDate(request.getDamageDate());
        entity.setDescription(request.getDescription());
        entity.setSeverity(request.getSeverity());
        entity.setStatus(request.getStatus().toUpperCase());
        entity.setActionTaken(request.getActionTaken());

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public DamageReportResponse getById(UUID id) {
        return repository.findByIdWithDetails(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("DamageReport not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DamageReportResponse> getAll(RequestParamDto requestParams) {
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
    public void delete(UUID id) {
        DamageReport entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DamageReport not found with id " + id));
        entity.delete(null);
        repository.save(entity);
    }
}
