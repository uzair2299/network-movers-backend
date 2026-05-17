package com.company.networkmovers.modules.vehicle.service.impl;

import com.company.networkmovers.modules.vehicle.entity.VehicleEntity;
import com.company.networkmovers.modules.vehicle.repository.VehicleRepository;
import com.company.networkmovers.modules.vehicle.service.VehicleService;
import com.company.networkmovers.modules.vehicle.dto.request.VehicleRequest;
import com.company.networkmovers.modules.vehicle.dto.response.VehicleResponse;
import com.company.networkmovers.modules.vehicle.mapper.VehicleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;
    private final VehicleMapper mapper;

    public VehicleServiceImpl(VehicleRepository repository, VehicleMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public VehicleResponse create(VehicleRequest request) {
        VehicleEntity entity = mapper.toEntity(request);
        VehicleEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public VehicleResponse findById(Long id) {
        VehicleEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VehicleResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public VehicleResponse update(Long id, VehicleRequest request) {
        VehicleEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        VehicleEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        VehicleEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found with id: " + id));
        repository.delete(entity);
    }
}
