package com.company.networkmovers.modules.audit.service.impl;

import com.company.networkmovers.modules.audit.entity.AuditEntity;
import com.company.networkmovers.modules.audit.repository.AuditRepository;
import com.company.networkmovers.modules.audit.service.AuditService;
import com.company.networkmovers.modules.audit.dto.request.AuditRequest;
import com.company.networkmovers.modules.audit.dto.response.AuditResponse;
import com.company.networkmovers.modules.audit.mapper.AuditMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AuditServiceImpl implements AuditService {

    private final AuditRepository repository;
    private final AuditMapper mapper;

    public AuditServiceImpl(AuditRepository repository, AuditMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AuditResponse create(AuditRequest request) {
        AuditEntity entity = mapper.toEntity(request);
        AuditEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AuditResponse findById(Long id) {
        AuditEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Audit not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AuditResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AuditResponse update(Long id, AuditRequest request) {
        AuditEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Audit not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        AuditEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        AuditEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Audit not found with id: " + id));
        repository.delete(entity);
    }
}
