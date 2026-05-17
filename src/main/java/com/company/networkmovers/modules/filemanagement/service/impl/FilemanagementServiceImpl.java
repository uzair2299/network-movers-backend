package com.company.networkmovers.modules.filemanagement.service.impl;

import com.company.networkmovers.modules.filemanagement.entity.FilemanagementEntity;
import com.company.networkmovers.modules.filemanagement.repository.FilemanagementRepository;
import com.company.networkmovers.modules.filemanagement.service.FilemanagementService;
import com.company.networkmovers.modules.filemanagement.dto.request.FilemanagementRequest;
import com.company.networkmovers.modules.filemanagement.dto.response.FilemanagementResponse;
import com.company.networkmovers.modules.filemanagement.mapper.FilemanagementMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FilemanagementServiceImpl implements FilemanagementService {

    private final FilemanagementRepository repository;
    private final FilemanagementMapper mapper;

    public FilemanagementServiceImpl(FilemanagementRepository repository, FilemanagementMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public FilemanagementResponse create(FilemanagementRequest request) {
        FilemanagementEntity entity = mapper.toEntity(request);
        FilemanagementEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public FilemanagementResponse findById(Long id) {
        FilemanagementEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filemanagement not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FilemanagementResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public FilemanagementResponse update(Long id, FilemanagementRequest request) {
        FilemanagementEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filemanagement not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        FilemanagementEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        FilemanagementEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Filemanagement not found with id: " + id));
        repository.delete(entity);
    }
}
