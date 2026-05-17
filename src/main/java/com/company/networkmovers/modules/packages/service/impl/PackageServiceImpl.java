package com.company.networkmovers.modules.packages.service.impl;

import com.company.networkmovers.modules.packages.entity.PackageEntity;
import com.company.networkmovers.modules.packages.repository.PackageRepository;
import com.company.networkmovers.modules.packages.service.PackageService;
import com.company.networkmovers.modules.packages.dto.request.PackageRequest;
import com.company.networkmovers.modules.packages.dto.response.PackageResponse;
import com.company.networkmovers.modules.packages.mapper.PackageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PackageServiceImpl implements PackageService {

    private final PackageRepository repository;
    private final PackageMapper mapper;

    public PackageServiceImpl(PackageRepository repository, PackageMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PackageResponse create(PackageRequest request) {
        PackageEntity entity = mapper.toEntity(request);
        PackageEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PackageResponse findById(Long id) {
        PackageEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PackageResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PackageResponse update(Long id, PackageRequest request) {
        PackageEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        PackageEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        PackageEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Package not found with id: " + id));
        repository.delete(entity);
    }
}
