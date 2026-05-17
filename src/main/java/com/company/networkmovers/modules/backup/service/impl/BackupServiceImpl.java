package com.company.networkmovers.modules.backup.service.impl;

import com.company.networkmovers.modules.backup.entity.BackupEntity;
import com.company.networkmovers.modules.backup.repository.BackupRepository;
import com.company.networkmovers.modules.backup.service.BackupService;
import com.company.networkmovers.modules.backup.dto.request.BackupRequest;
import com.company.networkmovers.modules.backup.dto.response.BackupResponse;
import com.company.networkmovers.modules.backup.mapper.BackupMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BackupServiceImpl implements BackupService {

    private final BackupRepository repository;
    private final BackupMapper mapper;

    public BackupServiceImpl(BackupRepository repository, BackupMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public BackupResponse create(BackupRequest request) {
        BackupEntity entity = mapper.toEntity(request);
        BackupEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public BackupResponse findById(Long id) {
        BackupEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Backup not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BackupResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BackupResponse update(Long id, BackupRequest request) {
        BackupEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Backup not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        BackupEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        BackupEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Backup not found with id: " + id));
        repository.delete(entity);
    }
}
