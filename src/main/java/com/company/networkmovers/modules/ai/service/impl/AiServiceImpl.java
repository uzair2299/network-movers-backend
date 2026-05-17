package com.company.networkmovers.modules.ai.service.impl;

import com.company.networkmovers.modules.ai.entity.AiEntity;
import com.company.networkmovers.modules.ai.repository.AiRepository;
import com.company.networkmovers.modules.ai.service.AiService;
import com.company.networkmovers.modules.ai.dto.request.AiRequest;
import com.company.networkmovers.modules.ai.dto.response.AiResponse;
import com.company.networkmovers.modules.ai.mapper.AiMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AiServiceImpl implements AiService {

    private final AiRepository repository;
    private final AiMapper mapper;

    public AiServiceImpl(AiRepository repository, AiMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AiResponse create(AiRequest request) {
        AiEntity entity = mapper.toEntity(request);
        AiEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public AiResponse findById(Long id) {
        AiEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ai not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AiResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AiResponse update(Long id, AiRequest request) {
        AiEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ai not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        AiEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        AiEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ai not found with id: " + id));
        repository.delete(entity);
    }
}
