package com.company.networkmovers.modules.identity.service.impl;

import com.company.networkmovers.modules.identity.entity.IdentityEntity;
import com.company.networkmovers.modules.identity.repository.IdentityRepository;
import com.company.networkmovers.modules.identity.service.IdentityService;
import com.company.networkmovers.modules.identity.dto.request.IdentityRequest;
import com.company.networkmovers.modules.identity.dto.response.IdentityResponse;
import com.company.networkmovers.modules.identity.mapper.IdentityMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class IdentityServiceImpl implements IdentityService {

    private final IdentityRepository repository;
    private final IdentityMapper mapper;

    public IdentityServiceImpl(IdentityRepository repository, IdentityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public IdentityResponse create(IdentityRequest request) {
        IdentityEntity entity = mapper.toEntity(request);
        IdentityEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public IdentityResponse findById(Long id) {
        IdentityEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Identity not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<IdentityResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public IdentityResponse update(Long id, IdentityRequest request) {
        IdentityEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Identity not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        IdentityEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        IdentityEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Identity not found with id: " + id));
        repository.delete(entity);
    }
}
