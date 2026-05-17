package com.company.networkmovers.modules.contract.service.impl;

import com.company.networkmovers.modules.contract.entity.ContractEntity;
import com.company.networkmovers.modules.contract.repository.ContractRepository;
import com.company.networkmovers.modules.contract.service.ContractService;
import com.company.networkmovers.modules.contract.dto.request.ContractRequest;
import com.company.networkmovers.modules.contract.dto.response.ContractResponse;
import com.company.networkmovers.modules.contract.mapper.ContractMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ContractServiceImpl implements ContractService {

    private final ContractRepository repository;
    private final ContractMapper mapper;

    public ContractServiceImpl(ContractRepository repository, ContractMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ContractResponse create(ContractRequest request) {
        ContractEntity entity = mapper.toEntity(request);
        ContractEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ContractResponse findById(Long id) {
        ContractEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ContractResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ContractResponse update(Long id, ContractRequest request) {
        ContractEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        ContractEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        ContractEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Contract not found with id: " + id));
        repository.delete(entity);
    }
}
