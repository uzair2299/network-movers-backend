package com.company.networkmovers.modules.approval.service.impl;

import com.company.networkmovers.modules.approval.entity.ApprovalEntity;
import com.company.networkmovers.modules.approval.repository.ApprovalRepository;
import com.company.networkmovers.modules.approval.service.ApprovalService;
import com.company.networkmovers.modules.approval.dto.request.ApprovalRequest;
import com.company.networkmovers.modules.approval.dto.response.ApprovalResponse;
import com.company.networkmovers.modules.approval.mapper.ApprovalMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApprovalServiceImpl implements ApprovalService {

    private final ApprovalRepository repository;
    private final ApprovalMapper mapper;

    public ApprovalServiceImpl(ApprovalRepository repository, ApprovalMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ApprovalResponse create(ApprovalRequest request) {
        ApprovalEntity entity = mapper.toEntity(request);
        ApprovalEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ApprovalResponse findById(Long id) {
        ApprovalEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApprovalResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ApprovalResponse update(Long id, ApprovalRequest request) {
        ApprovalEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        ApprovalEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        ApprovalEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Approval not found with id: " + id));
        repository.delete(entity);
    }
}
