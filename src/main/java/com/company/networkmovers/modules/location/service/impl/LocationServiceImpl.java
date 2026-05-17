package com.company.networkmovers.modules.location.service.impl;

import com.company.networkmovers.modules.location.entity.LocationEntity;
import com.company.networkmovers.modules.location.repository.LocationRepository;
import com.company.networkmovers.modules.location.service.LocationService;
import com.company.networkmovers.modules.location.dto.request.LocationRequest;
import com.company.networkmovers.modules.location.dto.response.LocationResponse;
import com.company.networkmovers.modules.location.mapper.LocationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationServiceImpl implements LocationService {

    private final LocationRepository repository;
    private final LocationMapper mapper;

    public LocationServiceImpl(LocationRepository repository, LocationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public LocationResponse create(LocationRequest request) {
        LocationEntity entity = mapper.toEntity(request);
        LocationEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public LocationResponse findById(Long id) {
        LocationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LocationResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LocationResponse update(Long id, LocationRequest request) {
        LocationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        LocationEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        LocationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found with id: " + id));
        repository.delete(entity);
    }
}
