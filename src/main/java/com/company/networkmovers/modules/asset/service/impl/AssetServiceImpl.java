package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.dto.request.AssetRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetResponse;
import com.company.networkmovers.modules.asset.entity.*;
import com.company.networkmovers.modules.asset.mapper.AssetMapper;
import com.company.networkmovers.modules.asset.repository.*;
import com.company.networkmovers.modules.asset.service.AssetService;
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
public class AssetServiceImpl implements AssetService {

    private final AssetRepository repository;
    private final AssetMapper mapper;
    private final AssetTypeRepository assetTypeRepository;
    private final AssetCategoryRepository assetCategoryRepository;
    private final AssetCompanyRepository assetCompanyRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final AssetSupplierRepository assetSupplierRepository;

    @Override
    @Transactional
    public AssetResponse create(AssetRequest request) {
        if (repository.existsByCodeAndDeletedFalse(request.getCode())) {
            throw new IllegalArgumentException("Asset with code " + request.getCode() + " already exists.");
        }

        AssetType type = assetTypeRepository.findById(request.getAssetTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("AssetType not found with id " + request.getAssetTypeId()));
        AssetCategory category = assetCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("AssetCategory not found with id " + request.getCategoryId()));
        AssetCompany company = assetCompanyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("AssetCompany not found with id " + request.getCompanyId()));
        UnitOfMeasure uom = unitOfMeasureRepository.findById(request.getUnitOfMeasureId())
                .orElseThrow(() -> new ResourceNotFoundException("UnitOfMeasure not found with id " + request.getUnitOfMeasureId()));

        AssetSupplier supplier = null;
        if (request.getSupplierId() != null) {
            supplier = assetSupplierRepository.findById(request.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException("AssetSupplier not found with id " + request.getSupplierId()));
        }

        Asset entity = mapper.toEntity(request);
        entity.setAssetType(type);
        entity.setCategory(category);
        entity.setCompany(company);
        entity.setUnitOfMeasure(uom);
        entity.setSupplier(supplier);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public AssetResponse update(UUID id, AssetRequest request) {
        Asset entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + id));

        if (repository.existsByCodeAndIdNotAndDeletedFalse(request.getCode(), id)) {
            throw new IllegalArgumentException("Asset with code " + request.getCode() + " already exists.");
        }

        AssetType type = assetTypeRepository.findById(request.getAssetTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("AssetType not found with id " + request.getAssetTypeId()));
        AssetCategory category = assetCategoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("AssetCategory not found with id " + request.getCategoryId()));
        AssetCompany company = assetCompanyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new ResourceNotFoundException("AssetCompany not found with id " + request.getCompanyId()));
        UnitOfMeasure uom = unitOfMeasureRepository.findById(request.getUnitOfMeasureId())
                .orElseThrow(() -> new ResourceNotFoundException("UnitOfMeasure not found with id " + request.getUnitOfMeasureId()));

        AssetSupplier supplier = null;
        if (request.getSupplierId() != null) {
            supplier = assetSupplierRepository.findById(request.getSupplierId())
                    .orElseThrow(() -> new ResourceNotFoundException("AssetSupplier not found with id " + request.getSupplierId()));
        }

        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setSku(request.getSku());
        entity.setBarcode(request.getBarcode());
        entity.setDescription(request.getDescription());
        entity.setModel(request.getModel());
        entity.setSerialNumber(request.getSerialNumber());
        entity.setPurchaseDate(request.getPurchaseDate());
        entity.setPurchaseCost(request.getPurchaseCost());
        entity.setStatus(request.getStatus());
        entity.setActive(request.isActive());
        entity.setAssetType(type);
        entity.setCategory(category);
        entity.setCompany(company);
        entity.setUnitOfMeasure(uom);
        entity.setSupplier(supplier);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public AssetResponse getById(UUID id) {
        return repository.findByIdWithDetails(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssetResponse> getAll(RequestParamDto requestParams) {
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
        String search = requestParams.getSearch();
        return repository.findAllActive(search, pageable).map(mapper::toResponse);
    }

    @Override
    @Transactional
    public AssetResponse updateStatus(UUID id, String status) {
        Asset entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + id));
        entity.setStatus(status);
        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Asset entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asset not found with id " + id));
        entity.setActive(false);
        entity.delete(null);
        repository.save(entity);
    }
}
