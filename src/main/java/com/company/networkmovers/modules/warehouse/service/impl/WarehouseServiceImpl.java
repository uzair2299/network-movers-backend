package com.company.networkmovers.modules.warehouse.service.impl;

import com.company.networkmovers.modules.warehouse.entity.WarehouseEntity;
import com.company.networkmovers.modules.warehouse.repository.WarehouseRepository;
import com.company.networkmovers.modules.warehouse.service.WarehouseService;
import com.company.networkmovers.modules.warehouse.dto.request.WarehouseRequest;
import com.company.networkmovers.modules.warehouse.dto.response.WarehouseResponse;
import com.company.networkmovers.modules.warehouse.mapper.WarehouseMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class WarehouseServiceImpl implements WarehouseService {

    private final WarehouseRepository repository;
    private final WarehouseMapper mapper;

    public WarehouseServiceImpl(WarehouseRepository repository, WarehouseMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public WarehouseResponse create(WarehouseRequest request) {
        WarehouseEntity entity = mapper.toEntity(request);
        WarehouseEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public WarehouseResponse findById(Long id) {
        WarehouseEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<WarehouseResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public WarehouseResponse update(Long id, WarehouseRequest request) {
        WarehouseEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        WarehouseEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        WarehouseEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found with id: " + id));
        repository.delete(entity);
    }
}
