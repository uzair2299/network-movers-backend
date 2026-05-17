package com.company.networkmovers.modules.logistics.service.impl;

import com.company.networkmovers.modules.logistics.entity.LogisticsEntity;
import com.company.networkmovers.modules.logistics.repository.LogisticsRepository;
import com.company.networkmovers.modules.logistics.service.LogisticsService;
import com.company.networkmovers.modules.logistics.dto.request.LogisticsRequest;
import com.company.networkmovers.modules.logistics.dto.response.LogisticsResponse;
import com.company.networkmovers.modules.logistics.mapper.LogisticsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LogisticsServiceImpl implements LogisticsService {

    private final LogisticsRepository repository;
    private final LogisticsMapper mapper;

    public LogisticsServiceImpl(LogisticsRepository repository, LogisticsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public LogisticsResponse create(LogisticsRequest request) {
        LogisticsEntity entity = mapper.toEntity(request);
        LogisticsEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public LogisticsResponse findById(Long id) {
        LogisticsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logistics not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<LogisticsResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public LogisticsResponse update(Long id, LogisticsRequest request) {
        LogisticsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logistics not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        LogisticsEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        LogisticsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Logistics not found with id: " + id));
        repository.delete(entity);
    }
}
