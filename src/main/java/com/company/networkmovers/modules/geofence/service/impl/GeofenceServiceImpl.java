package com.company.networkmovers.modules.geofence.service.impl;

import com.company.networkmovers.modules.geofence.entity.GeofenceEntity;
import com.company.networkmovers.modules.geofence.repository.GeofenceRepository;
import com.company.networkmovers.modules.geofence.service.GeofenceService;
import com.company.networkmovers.modules.geofence.dto.request.GeofenceRequest;
import com.company.networkmovers.modules.geofence.dto.response.GeofenceResponse;
import com.company.networkmovers.modules.geofence.mapper.GeofenceMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GeofenceServiceImpl implements GeofenceService {

    private final GeofenceRepository repository;
    private final GeofenceMapper mapper;

    public GeofenceServiceImpl(GeofenceRepository repository, GeofenceMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public GeofenceResponse create(GeofenceRequest request) {
        GeofenceEntity entity = mapper.toEntity(request);
        GeofenceEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public GeofenceResponse findById(Long id) {
        GeofenceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Geofence not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GeofenceResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public GeofenceResponse update(Long id, GeofenceRequest request) {
        GeofenceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Geofence not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        GeofenceEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        GeofenceEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Geofence not found with id: " + id));
        repository.delete(entity);
    }
}
