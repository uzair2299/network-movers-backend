package com.company.networkmovers.modules.asset.service.impl;

import com.company.networkmovers.modules.asset.dto.request.AssetCategoryRequest;
import com.company.networkmovers.modules.asset.dto.response.AssetCategoryResponse;
import com.company.networkmovers.modules.asset.entity.AssetCategory;
import com.company.networkmovers.modules.asset.entity.AssetType;
import com.company.networkmovers.modules.asset.mapper.AssetCategoryMapper;
import com.company.networkmovers.modules.asset.repository.AssetCategoryRepository;
import com.company.networkmovers.modules.asset.repository.AssetTypeRepository;
import com.company.networkmovers.modules.asset.service.AssetCategoryService;
import com.company.networkmovers.shared.dto.RequestParamDto;
import com.company.networkmovers.shared.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssetCategoryServiceImpl implements AssetCategoryService {

    private final AssetCategoryRepository repository;
    private final AssetCategoryMapper mapper;
    private final AssetTypeRepository assetTypeRepository;

    @Override
    @Transactional
    public AssetCategoryResponse create(AssetCategoryRequest request) {
        if (repository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException("Asset category with code " + request.getCode() + " already exists.");
        }

        AssetType assetType = assetTypeRepository.findById(request.getAssetTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("AssetType not found with id " + request.getAssetTypeId()));

        AssetCategory parent = null;
        if (request.getParentCategoryId() != null) {
            parent = repository.findById(request.getParentCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent AssetCategory not found with id " + request.getParentCategoryId()));
        }

        AssetCategory entity = mapper.toEntity(request);
        entity.setAssetType(assetType);
        entity.setParentCategory(parent);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional
    public AssetCategoryResponse update(UUID id, AssetCategoryRequest request) {
        AssetCategory entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetCategory not found with id " + id));

        if (repository.existsByCodeAndIdNot(request.getCode(), id)) {
            throw new IllegalArgumentException("Asset category with code " + request.getCode() + " already exists.");
        }

        AssetType assetType = assetTypeRepository.findById(request.getAssetTypeId())
                .orElseThrow(() -> new ResourceNotFoundException("AssetType not found with id " + request.getAssetTypeId()));

        AssetCategory parent = null;
        if (request.getParentCategoryId() != null) {
            parent = repository.findById(request.getParentCategoryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent AssetCategory not found with id " + request.getParentCategoryId()));
        }

        entity.setCode(request.getCode());
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        entity.setActive(request.isActive());
        entity.setAssetType(assetType);
        entity.setParentCategory(parent);

        return mapper.toResponse(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public AssetCategoryResponse getById(UUID id) {
        return repository.findByIdWithDetails(id)
                .map(mapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("AssetCategory not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssetCategoryResponse> getAllActive() {
        return repository.findAllActive().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AssetCategoryResponse> getAll(RequestParamDto requestParams) {
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
    public void delete(UUID id) {
        AssetCategory entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("AssetCategory not found with id " + id));
        entity.setActive(false);
        repository.save(entity);
    }
}
