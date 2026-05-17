package com.company.networkmovers.modules.workflow.service.impl;

import com.company.networkmovers.modules.workflow.entity.WorkflowEntity;
import com.company.networkmovers.modules.workflow.repository.WorkflowRepository;
import com.company.networkmovers.modules.workflow.service.WorkflowService;
import com.company.networkmovers.modules.workflow.dto.request.WorkflowRequest;
import com.company.networkmovers.modules.workflow.dto.response.WorkflowResponse;
import com.company.networkmovers.modules.workflow.mapper.WorkflowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WorkflowServiceImpl implements WorkflowService {

    private final WorkflowRepository repository;
    private final WorkflowMapper mapper;

    public WorkflowServiceImpl(WorkflowRepository repository, WorkflowMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public WorkflowResponse create(WorkflowRequest request) {
        WorkflowEntity entity = mapper.toEntity(request);
        WorkflowEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public WorkflowResponse findById(Long id) {
        WorkflowEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WorkflowResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public WorkflowResponse update(Long id, WorkflowRequest request) {
        WorkflowEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        WorkflowEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        WorkflowEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Workflow not found with id: " + id));
        repository.delete(entity);
    }
}
