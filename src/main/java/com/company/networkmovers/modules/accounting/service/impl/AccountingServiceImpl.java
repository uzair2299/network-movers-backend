package com.company.networkmovers.modules.accounting.service.impl;

import com.company.networkmovers.modules.accounting.entity.AccountingEntity;
import com.company.networkmovers.modules.accounting.repository.AccountingRepository;
import com.company.networkmovers.modules.accounting.service.AccountingService;
import com.company.networkmovers.modules.accounting.dto.request.AccountingRequest;
import com.company.networkmovers.modules.accounting.dto.response.AccountingResponse;
import com.company.networkmovers.modules.accounting.mapper.AccountingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AccountingServiceImpl implements AccountingService {

    private final AccountingRepository repository;
    private final AccountingMapper mapper;

    public AccountingServiceImpl(AccountingRepository repository, AccountingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AccountingResponse create(AccountingRequest request) {
        AccountingEntity entity = mapper.toEntity(request);
        AccountingEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AccountingResponse findById(Long id) {
        AccountingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accounting not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AccountingResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AccountingResponse update(Long id, AccountingRequest request) {
        AccountingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accounting not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        AccountingEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        AccountingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Accounting not found with id: " + id));
        repository.delete(entity);
    }
}
