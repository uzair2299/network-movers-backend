package com.company.networkmovers.modules.estimate.service.impl;

import com.company.networkmovers.modules.estimate.entity.EstimateEntity;
import com.company.networkmovers.modules.estimate.repository.EstimateRepository;
import com.company.networkmovers.modules.estimate.service.EstimateService;
import com.company.networkmovers.modules.estimate.dto.request.EstimateRequest;
import com.company.networkmovers.modules.estimate.dto.response.EstimateResponse;
import com.company.networkmovers.modules.estimate.mapper.EstimateMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EstimateServiceImpl implements EstimateService {

    private final EstimateRepository repository;
    private final EstimateMapper mapper;

    public EstimateServiceImpl(EstimateRepository repository, EstimateMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EstimateResponse create(EstimateRequest request) {
        EstimateEntity entity = mapper.toEntity(request);
        EstimateEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public EstimateResponse findById(Long id) {
        EstimateEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estimate not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EstimateResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EstimateResponse update(Long id, EstimateRequest request) {
        EstimateEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estimate not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        EstimateEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        EstimateEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Estimate not found with id: " + id));
        repository.delete(entity);
    }
}
