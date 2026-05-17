package com.company.networkmovers.modules.analytics.service.impl;

import com.company.networkmovers.modules.analytics.entity.AnalyticsEntity;
import com.company.networkmovers.modules.analytics.repository.AnalyticsRepository;
import com.company.networkmovers.modules.analytics.service.AnalyticsService;
import com.company.networkmovers.modules.analytics.dto.request.AnalyticsRequest;
import com.company.networkmovers.modules.analytics.dto.response.AnalyticsResponse;
import com.company.networkmovers.modules.analytics.mapper.AnalyticsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnalyticsServiceImpl implements AnalyticsService {

    private final AnalyticsRepository repository;
    private final AnalyticsMapper mapper;

    public AnalyticsServiceImpl(AnalyticsRepository repository, AnalyticsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AnalyticsResponse create(AnalyticsRequest request) {
        AnalyticsEntity entity = mapper.toEntity(request);
        AnalyticsEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AnalyticsResponse findById(Long id) {
        AnalyticsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Analytics not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AnalyticsResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AnalyticsResponse update(Long id, AnalyticsRequest request) {
        AnalyticsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Analytics not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        AnalyticsEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        AnalyticsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Analytics not found with id: " + id));
        repository.delete(entity);
    }
}
