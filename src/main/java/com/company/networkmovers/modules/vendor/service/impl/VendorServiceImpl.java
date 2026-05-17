package com.company.networkmovers.modules.vendor.service.impl;

import com.company.networkmovers.modules.vendor.entity.VendorEntity;
import com.company.networkmovers.modules.vendor.repository.VendorRepository;
import com.company.networkmovers.modules.vendor.service.VendorService;
import com.company.networkmovers.modules.vendor.dto.request.VendorRequest;
import com.company.networkmovers.modules.vendor.dto.response.VendorResponse;
import com.company.networkmovers.modules.vendor.mapper.VendorMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VendorServiceImpl implements VendorService {

    private final VendorRepository repository;
    private final VendorMapper mapper;

    public VendorServiceImpl(VendorRepository repository, VendorMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public VendorResponse create(VendorRequest request) {
        VendorEntity entity = mapper.toEntity(request);
        VendorEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public VendorResponse findById(Long id) {
        VendorEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VendorResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VendorResponse update(Long id, VendorRequest request) {
        VendorEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        VendorEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        VendorEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vendor not found with id: " + id));
        repository.delete(entity);
    }
}
