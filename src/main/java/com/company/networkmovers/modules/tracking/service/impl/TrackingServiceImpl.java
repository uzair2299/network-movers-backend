package com.company.networkmovers.modules.tracking.service.impl;

import com.company.networkmovers.modules.tracking.entity.TrackingEntity;
import com.company.networkmovers.modules.tracking.repository.TrackingRepository;
import com.company.networkmovers.modules.tracking.service.TrackingService;
import com.company.networkmovers.modules.tracking.dto.request.TrackingRequest;
import com.company.networkmovers.modules.tracking.dto.response.TrackingResponse;
import com.company.networkmovers.modules.tracking.mapper.TrackingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TrackingServiceImpl implements TrackingService {

    private final TrackingRepository repository;
    private final TrackingMapper mapper;

    public TrackingServiceImpl(TrackingRepository repository, TrackingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TrackingResponse create(TrackingRequest request) {
        TrackingEntity entity = mapper.toEntity(request);
        TrackingEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TrackingResponse findById(Long id) {
        TrackingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tracking not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrackingResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TrackingResponse update(Long id, TrackingRequest request) {
        TrackingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tracking not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        TrackingEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        TrackingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tracking not found with id: " + id));
        repository.delete(entity);
    }
}
