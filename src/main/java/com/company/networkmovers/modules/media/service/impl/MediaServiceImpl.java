package com.company.networkmovers.modules.media.service.impl;

import com.company.networkmovers.modules.media.entity.MediaEntity;
import com.company.networkmovers.modules.media.repository.MediaRepository;
import com.company.networkmovers.modules.media.service.MediaService;
import com.company.networkmovers.modules.media.dto.request.MediaRequest;
import com.company.networkmovers.modules.media.dto.response.MediaResponse;
import com.company.networkmovers.modules.media.mapper.MediaMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MediaServiceImpl implements MediaService {

    private final MediaRepository repository;
    private final MediaMapper mapper;

    public MediaServiceImpl(MediaRepository repository, MediaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MediaResponse create(MediaRequest request) {
        MediaEntity entity = mapper.toEntity(request);
        MediaEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MediaResponse findById(Long id) {
        MediaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MediaResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MediaResponse update(Long id, MediaRequest request) {
        MediaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        MediaEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        MediaEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Media not found with id: " + id));
        repository.delete(entity);
    }
}
