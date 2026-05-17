package com.company.networkmovers.modules.fleet.service.impl;

import com.company.networkmovers.modules.fleet.entity.FleetEntity;
import com.company.networkmovers.modules.fleet.repository.FleetRepository;
import com.company.networkmovers.modules.fleet.service.FleetService;
import com.company.networkmovers.modules.fleet.dto.request.FleetRequest;
import com.company.networkmovers.modules.fleet.dto.response.FleetResponse;
import com.company.networkmovers.modules.fleet.mapper.FleetMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FleetServiceImpl implements FleetService {

    private final FleetRepository repository;
    private final FleetMapper mapper;

    public FleetServiceImpl(FleetRepository repository, FleetMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public FleetResponse create(FleetRequest request) {
        FleetEntity entity = mapper.toEntity(request);
        FleetEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public FleetResponse findById(Long id) {
        FleetEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fleet not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FleetResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FleetResponse update(Long id, FleetRequest request) {
        FleetEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fleet not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        FleetEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        FleetEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fleet not found with id: " + id));
        repository.delete(entity);
    }
}
