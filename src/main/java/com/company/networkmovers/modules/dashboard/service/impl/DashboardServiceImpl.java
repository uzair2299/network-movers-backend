package com.company.networkmovers.modules.dashboard.service.impl;

import com.company.networkmovers.modules.dashboard.entity.DashboardEntity;
import com.company.networkmovers.modules.dashboard.repository.DashboardRepository;
import com.company.networkmovers.modules.dashboard.service.DashboardService;
import com.company.networkmovers.modules.dashboard.dto.request.DashboardRequest;
import com.company.networkmovers.modules.dashboard.dto.response.DashboardResponse;
import com.company.networkmovers.modules.dashboard.mapper.DashboardMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DashboardServiceImpl implements DashboardService {

    private final DashboardRepository repository;
    private final DashboardMapper mapper;

    public DashboardServiceImpl(DashboardRepository repository, DashboardMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DashboardResponse create(DashboardRequest request) {
        DashboardEntity entity = mapper.toEntity(request);
        DashboardEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public DashboardResponse findById(Long id) {
        DashboardEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dashboard not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DashboardResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DashboardResponse update(Long id, DashboardRequest request) {
        DashboardEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dashboard not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        DashboardEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        DashboardEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dashboard not found with id: " + id));
        repository.delete(entity);
    }
}
