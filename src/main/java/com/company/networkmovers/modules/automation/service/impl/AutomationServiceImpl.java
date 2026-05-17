package com.company.networkmovers.modules.automation.service.impl;

import com.company.networkmovers.modules.automation.entity.AutomationEntity;
import com.company.networkmovers.modules.automation.repository.AutomationRepository;
import com.company.networkmovers.modules.automation.service.AutomationService;
import com.company.networkmovers.modules.automation.dto.request.AutomationRequest;
import com.company.networkmovers.modules.automation.dto.response.AutomationResponse;
import com.company.networkmovers.modules.automation.mapper.AutomationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AutomationServiceImpl implements AutomationService {

    private final AutomationRepository repository;
    private final AutomationMapper mapper;

    public AutomationServiceImpl(AutomationRepository repository, AutomationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AutomationResponse create(AutomationRequest request) {
        AutomationEntity entity = mapper.toEntity(request);
        AutomationEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AutomationResponse findById(Long id) {
        AutomationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Automation not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AutomationResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AutomationResponse update(Long id, AutomationRequest request) {
        AutomationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Automation not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        AutomationEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        AutomationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Automation not found with id: " + id));
        repository.delete(entity);
    }
}
