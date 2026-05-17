package com.company.networkmovers.modules.scheduling.service.impl;

import com.company.networkmovers.modules.scheduling.entity.SchedulingEntity;
import com.company.networkmovers.modules.scheduling.repository.SchedulingRepository;
import com.company.networkmovers.modules.scheduling.service.SchedulingService;
import com.company.networkmovers.modules.scheduling.dto.request.SchedulingRequest;
import com.company.networkmovers.modules.scheduling.dto.response.SchedulingResponse;
import com.company.networkmovers.modules.scheduling.mapper.SchedulingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SchedulingServiceImpl implements SchedulingService {

    private final SchedulingRepository repository;
    private final SchedulingMapper mapper;

    public SchedulingServiceImpl(SchedulingRepository repository, SchedulingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SchedulingResponse create(SchedulingRequest request) {
        SchedulingEntity entity = mapper.toEntity(request);
        SchedulingEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SchedulingResponse findById(Long id) {
        SchedulingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scheduling not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SchedulingResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SchedulingResponse update(Long id, SchedulingRequest request) {
        SchedulingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scheduling not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        SchedulingEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        SchedulingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Scheduling not found with id: " + id));
        repository.delete(entity);
    }
}
