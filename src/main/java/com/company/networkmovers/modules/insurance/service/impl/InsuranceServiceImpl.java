package com.company.networkmovers.modules.insurance.service.impl;

import com.company.networkmovers.modules.insurance.entity.InsuranceEntity;
import com.company.networkmovers.modules.insurance.repository.InsuranceRepository;
import com.company.networkmovers.modules.insurance.service.InsuranceService;
import com.company.networkmovers.modules.insurance.dto.request.InsuranceRequest;
import com.company.networkmovers.modules.insurance.dto.response.InsuranceResponse;
import com.company.networkmovers.modules.insurance.mapper.InsuranceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class InsuranceServiceImpl implements InsuranceService {

    private final InsuranceRepository repository;
    private final InsuranceMapper mapper;

    public InsuranceServiceImpl(InsuranceRepository repository, InsuranceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public InsuranceResponse create(InsuranceRequest request) {
        InsuranceEntity entity = mapper.toEntity(request);
        InsuranceEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public InsuranceResponse findById(Long id) {
        InsuranceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InsuranceResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public InsuranceResponse update(Long id, InsuranceRequest request) {
        InsuranceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        InsuranceEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        InsuranceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Insurance not found with id: " + id));
        repository.delete(entity);
    }
}
