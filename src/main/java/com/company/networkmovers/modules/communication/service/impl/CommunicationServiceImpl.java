package com.company.networkmovers.modules.communication.service.impl;

import com.company.networkmovers.modules.communication.entity.CommunicationEntity;
import com.company.networkmovers.modules.communication.repository.CommunicationRepository;
import com.company.networkmovers.modules.communication.service.CommunicationService;
import com.company.networkmovers.modules.communication.dto.request.CommunicationRequest;
import com.company.networkmovers.modules.communication.dto.response.CommunicationResponse;
import com.company.networkmovers.modules.communication.mapper.CommunicationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CommunicationServiceImpl implements CommunicationService {

    private final CommunicationRepository repository;
    private final CommunicationMapper mapper;

    public CommunicationServiceImpl(CommunicationRepository repository, CommunicationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CommunicationResponse create(CommunicationRequest request) {
        CommunicationEntity entity = mapper.toEntity(request);
        CommunicationEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CommunicationResponse findById(Long id) {
        CommunicationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Communication not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CommunicationResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CommunicationResponse update(Long id, CommunicationRequest request) {
        CommunicationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Communication not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        CommunicationEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        CommunicationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Communication not found with id: " + id));
        repository.delete(entity);
    }
}
