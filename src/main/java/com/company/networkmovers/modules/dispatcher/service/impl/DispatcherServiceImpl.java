package com.company.networkmovers.modules.dispatcher.service.impl;

import com.company.networkmovers.modules.dispatcher.entity.DispatcherEntity;
import com.company.networkmovers.modules.dispatcher.repository.DispatcherRepository;
import com.company.networkmovers.modules.dispatcher.service.DispatcherService;
import com.company.networkmovers.modules.dispatcher.dto.request.DispatcherRequest;
import com.company.networkmovers.modules.dispatcher.dto.response.DispatcherResponse;
import com.company.networkmovers.modules.dispatcher.mapper.DispatcherMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DispatcherServiceImpl implements DispatcherService {

    private final DispatcherRepository repository;
    private final DispatcherMapper mapper;

    public DispatcherServiceImpl(DispatcherRepository repository, DispatcherMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DispatcherResponse create(DispatcherRequest request) {
        DispatcherEntity entity = mapper.toEntity(request);
        DispatcherEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DispatcherResponse findById(Long id) {
        DispatcherEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispatcher not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DispatcherResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DispatcherResponse update(Long id, DispatcherRequest request) {
        DispatcherEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispatcher not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        DispatcherEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        DispatcherEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dispatcher not found with id: " + id));
        repository.delete(entity);
    }
}
