package com.company.networkmovers.modules.optimization.service.impl;

import com.company.networkmovers.modules.optimization.entity.OptimizationEntity;
import com.company.networkmovers.modules.optimization.repository.OptimizationRepository;
import com.company.networkmovers.modules.optimization.service.OptimizationService;
import com.company.networkmovers.modules.optimization.dto.request.OptimizationRequest;
import com.company.networkmovers.modules.optimization.dto.response.OptimizationResponse;
import com.company.networkmovers.modules.optimization.mapper.OptimizationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OptimizationServiceImpl implements OptimizationService {

    private final OptimizationRepository repository;
    private final OptimizationMapper mapper;

    public OptimizationServiceImpl(OptimizationRepository repository, OptimizationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public OptimizationResponse create(OptimizationRequest request) {
        OptimizationEntity entity = mapper.toEntity(request);
        OptimizationEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public OptimizationResponse findById(Long id) {
        OptimizationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Optimization not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OptimizationResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OptimizationResponse update(Long id, OptimizationRequest request) {
        OptimizationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Optimization not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        OptimizationEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        OptimizationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Optimization not found with id: " + id));
        repository.delete(entity);
    }
}
