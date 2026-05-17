package com.company.networkmovers.modules.finance.service.impl;

import com.company.networkmovers.modules.finance.entity.FinanceEntity;
import com.company.networkmovers.modules.finance.repository.FinanceRepository;
import com.company.networkmovers.modules.finance.service.FinanceService;
import com.company.networkmovers.modules.finance.dto.request.FinanceRequest;
import com.company.networkmovers.modules.finance.dto.response.FinanceResponse;
import com.company.networkmovers.modules.finance.mapper.FinanceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FinanceServiceImpl implements FinanceService {

    private final FinanceRepository repository;
    private final FinanceMapper mapper;

    public FinanceServiceImpl(FinanceRepository repository, FinanceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public FinanceResponse create(FinanceRequest request) {
        FinanceEntity entity = mapper.toEntity(request);
        FinanceEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public FinanceResponse findById(Long id) {
        FinanceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finance not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FinanceResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FinanceResponse update(Long id, FinanceRequest request) {
        FinanceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finance not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        FinanceEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        FinanceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Finance not found with id: " + id));
        repository.delete(entity);
    }
}
