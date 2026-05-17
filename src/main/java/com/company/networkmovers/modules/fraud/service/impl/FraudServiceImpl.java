package com.company.networkmovers.modules.fraud.service.impl;

import com.company.networkmovers.modules.fraud.entity.FraudEntity;
import com.company.networkmovers.modules.fraud.repository.FraudRepository;
import com.company.networkmovers.modules.fraud.service.FraudService;
import com.company.networkmovers.modules.fraud.dto.request.FraudRequest;
import com.company.networkmovers.modules.fraud.dto.response.FraudResponse;
import com.company.networkmovers.modules.fraud.mapper.FraudMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FraudServiceImpl implements FraudService {

    private final FraudRepository repository;
    private final FraudMapper mapper;

    public FraudServiceImpl(FraudRepository repository, FraudMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public FraudResponse create(FraudRequest request) {
        FraudEntity entity = mapper.toEntity(request);
        FraudEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public FraudResponse findById(Long id) {
        FraudEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fraud not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FraudResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FraudResponse update(Long id, FraudRequest request) {
        FraudEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fraud not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        FraudEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        FraudEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fraud not found with id: " + id));
        repository.delete(entity);
    }
}
