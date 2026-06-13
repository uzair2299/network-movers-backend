package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.dto.request.EmployeeAssetAssignmentRequest;
import com.company.networkmovers.modules.asset.dto.response.EmployeeAssetAssignmentResponse;
import com.company.networkmovers.modules.asset.entity.*;
import com.company.networkmovers.modules.asset.mapper.EmployeeAssetAssignmentMapper;
import com.company.networkmovers.modules.asset.repository.AssetRepository;
import com.company.networkmovers.modules.asset.repository.EmployeeAssetAssignmentRepository;
import com.company.networkmovers.modules.asset.service.AssetStockService;
import com.company.networkmovers.modules.asset.service.EmployeeAssetAssignmentService;
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
public class EmployeeAssetAssignmentServiceImpl implements EmployeeAssetAssignmentService {

    private final EmployeeAssetAssignmentRepository repository;
    private final EmployeeAssetAssignmentMapper mapper;
    private final AssetRepository assetRepository;
    private final AssetStockService stockService;

    @Override
    @Transactional
    public EmployeeAssetAssignmentResponse create(EmployeeAssetAssignmentRequest request, UUID locationId) {
        Asset asset = assetRepository.findById(request.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + request.getAssetId()));

        EmployeeAssetAssignment entity = mapper.toEntity(request);
        entity.setAsset(asset);
        entity.setStatus(request.getStatus().toUpperCase());

        EmployeeAssetAssignment saved = repository.save(entity);

        if ("ASSIGNED".equalsIgnoreCase(saved.getStatus()) && locationId != null) {
            stockService.adjustStock(
                    asset.getId(),
                    locationId,
                    BigDecimal.ONE,
                    "OUT",
                    "EMPLOYEE_ASSIGNMENT",
                    saved.getId(),
                    "Assigned to employee: " + saved.getEmployeeId()
            );
        }

        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public EmployeeAssetAssignmentResponse update(UUID id, EmployeeAssetAssignmentRequest request) {
        EmployeeAssetAssignment entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeAssetAssignment not found with id " + id));

        Asset asset = assetRepository.findById(request.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + request.getAssetId()));

        entity.setAsset(asset);
        entity.setEmployeeId(request.getEmployeeId());
        entity.setAssignedDate(request.getAssignedDate());
        entity.setReturnedDate(request.getReturnedDate());
        entity.setConditionOnIssue(request.getConditionOnIssue());
        entity.setConditionOnReturn(request.getConditionOnReturn());
        entity.setRemarks(request.getRemarks());

        if (!"RETURNED".equalsIgnoreCase(entity.getStatus())) {
            entity.setStatus(request.getStatus().toUpperCase());
        }

        EmployeeAssetAssignment saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeAssetAssignmentResponse getById(UUID id) {
        return repository.findByIdWithDetails(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeAssetAssignment not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmployeeAssetAssignmentResponse> getAll(RequestParamDto requestParams) {
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
        UUID employeeId = null;
        if (requestParams.getSearch() != null && !requestParams.getSearch().isBlank()) {
            try {
                employeeId = UUID.fromString(requestParams.getSearch().trim());
            } catch (IllegalArgumentException e) {
                // Not a UUID
            }
        }
        return repository.findAllActive(employeeId, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public EmployeeAssetAssignmentResponse updateStatus(UUID id, String status, UUID locationId, String conditionOnReturn) {
        EmployeeAssetAssignment entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeAssetAssignment not found with id " + id));

        String oldStatus = entity.getStatus();
        String newStatus = status.toUpperCase();

        if ("RETURNED".equalsIgnoreCase(oldStatus)) {
            throw new IllegalStateException("Cannot change status of an already RETURNED asset assignment.");
        }

        entity.setStatus(newStatus);

        if ("RETURNED".equalsIgnoreCase(newStatus)) {
            if (locationId == null) {
                throw new IllegalArgumentException("Location ID is required when returning assets to stock.");
            }
            entity.setReturnedDate(LocalDate.now());
            entity.setConditionOnReturn(conditionOnReturn);

            stockService.adjustStock(
                    entity.getAsset().getId(),
                    locationId,
                    BigDecimal.ONE,
                    "IN",
                    "EMPLOYEE_ASSIGNMENT",
                    entity.getId(),
                    "Returned by employee: " + entity.getEmployeeId()
            );
        }

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        EmployeeAssetAssignment entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("EmployeeAssetAssignment not found with id " + id));
        entity.delete(null);
        repository.save(entity);
    }
}
