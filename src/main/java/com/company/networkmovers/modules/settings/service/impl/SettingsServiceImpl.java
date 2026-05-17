package com.company.networkmovers.modules.settings.service.impl;

import com.company.networkmovers.modules.settings.entity.SettingsEntity;
import com.company.networkmovers.modules.settings.repository.SettingsRepository;
import com.company.networkmovers.modules.settings.service.SettingsService;
import com.company.networkmovers.modules.settings.dto.request.SettingsRequest;
import com.company.networkmovers.modules.settings.dto.response.SettingsResponse;
import com.company.networkmovers.modules.settings.mapper.SettingsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SettingsServiceImpl implements SettingsService {

    private final SettingsRepository repository;
    private final SettingsMapper mapper;

    public SettingsServiceImpl(SettingsRepository repository, SettingsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SettingsResponse create(SettingsRequest request) {
        SettingsEntity entity = mapper.toEntity(request);
        SettingsEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SettingsResponse findById(Long id) {
        SettingsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Settings not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SettingsResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SettingsResponse update(Long id, SettingsRequest request) {
        SettingsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Settings not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        SettingsEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        SettingsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Settings not found with id: " + id));
        repository.delete(entity);
    }
}
