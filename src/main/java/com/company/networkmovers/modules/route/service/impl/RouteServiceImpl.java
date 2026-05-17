package com.company.networkmovers.modules.route.service.impl;

import com.company.networkmovers.modules.route.entity.RouteEntity;
import com.company.networkmovers.modules.route.repository.RouteRepository;
import com.company.networkmovers.modules.route.service.RouteService;
import com.company.networkmovers.modules.route.dto.request.RouteRequest;
import com.company.networkmovers.modules.route.dto.response.RouteResponse;
import com.company.networkmovers.modules.route.mapper.RouteMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RouteServiceImpl implements RouteService {

    private final RouteRepository repository;
    private final RouteMapper mapper;

    public RouteServiceImpl(RouteRepository repository, RouteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RouteResponse create(RouteRequest request) {
        RouteEntity entity = mapper.toEntity(request);
        RouteEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public RouteResponse findById(Long id) {
        RouteEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RouteResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RouteResponse update(Long id, RouteRequest request) {
        RouteEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        RouteEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        RouteEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found with id: " + id));
        repository.delete(entity);
    }
}
