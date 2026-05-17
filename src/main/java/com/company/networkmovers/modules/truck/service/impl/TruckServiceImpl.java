package com.company.networkmovers.modules.truck.service.impl;

import com.company.networkmovers.modules.truck.entity.TruckEntity;
import com.company.networkmovers.modules.truck.repository.TruckRepository;
import com.company.networkmovers.modules.truck.service.TruckService;
import com.company.networkmovers.modules.truck.dto.request.TruckRequest;
import com.company.networkmovers.modules.truck.dto.response.TruckResponse;
import com.company.networkmovers.modules.truck.mapper.TruckMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TruckServiceImpl implements TruckService {

    private final TruckRepository repository;
    private final TruckMapper mapper;

    public TruckServiceImpl(TruckRepository repository, TruckMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TruckResponse create(TruckRequest request) {
        TruckEntity entity = mapper.toEntity(request);
        TruckEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TruckResponse findById(Long id) {
        TruckEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Truck not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TruckResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TruckResponse update(Long id, TruckRequest request) {
        TruckEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Truck not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        TruckEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        TruckEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Truck not found with id: " + id));
        repository.delete(entity);
    }
}
