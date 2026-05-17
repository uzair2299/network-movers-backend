package com.company.networkmovers.modules.dispatch.service.impl;

import com.company.networkmovers.modules.dispatch.entity.DispatchEntity;
import com.company.networkmovers.modules.dispatch.repository.DispatchRepository;
import com.company.networkmovers.modules.dispatch.service.DispatchService;
import com.company.networkmovers.modules.dispatch.dto.request.DispatchRequest;
import com.company.networkmovers.modules.dispatch.dto.response.DispatchResponse;
import com.company.networkmovers.modules.dispatch.mapper.DispatchMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DispatchServiceImpl implements DispatchService {

    private final DispatchRepository repository;
    private final DispatchMapper mapper;

    public DispatchServiceImpl(DispatchRepository repository, DispatchMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DispatchResponse create(DispatchRequest request) {
        DispatchEntity entity = mapper.toEntity(request);
        DispatchEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DispatchResponse findById(Long id) {
        DispatchEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispatch not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DispatchResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DispatchResponse update(Long id, DispatchRequest request) {
        DispatchEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispatch not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        DispatchEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        DispatchEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispatch not found with id: " + id));
        repository.delete(entity);
    }
}
