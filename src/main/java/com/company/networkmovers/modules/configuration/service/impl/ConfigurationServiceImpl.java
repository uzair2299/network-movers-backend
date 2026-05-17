package com.company.networkmovers.modules.configuration.service.impl;

import com.company.networkmovers.modules.configuration.entity.ConfigurationEntity;
import com.company.networkmovers.modules.configuration.repository.ConfigurationRepository;
import com.company.networkmovers.modules.configuration.service.ConfigurationService;
import com.company.networkmovers.modules.configuration.dto.request.ConfigurationRequest;
import com.company.networkmovers.modules.configuration.dto.response.ConfigurationResponse;
import com.company.networkmovers.modules.configuration.mapper.ConfigurationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConfigurationServiceImpl implements ConfigurationService {

    private final ConfigurationRepository repository;
    private final ConfigurationMapper mapper;

    public ConfigurationServiceImpl(ConfigurationRepository repository, ConfigurationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ConfigurationResponse create(ConfigurationRequest request) {
        ConfigurationEntity entity = mapper.toEntity(request);
        ConfigurationEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ConfigurationResponse findById(Long id) {
        ConfigurationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuration not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConfigurationResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ConfigurationResponse update(Long id, ConfigurationRequest request) {
        ConfigurationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuration not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        ConfigurationEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        ConfigurationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Configuration not found with id: " + id));
        repository.delete(entity);
    }
}
