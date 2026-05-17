package com.company.networkmovers.modules.realtime.service.impl;

import com.company.networkmovers.modules.realtime.entity.RealtimeEntity;
import com.company.networkmovers.modules.realtime.repository.RealtimeRepository;
import com.company.networkmovers.modules.realtime.service.RealtimeService;
import com.company.networkmovers.modules.realtime.dto.request.RealtimeRequest;
import com.company.networkmovers.modules.realtime.dto.response.RealtimeResponse;
import com.company.networkmovers.modules.realtime.mapper.RealtimeMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RealtimeServiceImpl implements RealtimeService {

    private final RealtimeRepository repository;
    private final RealtimeMapper mapper;

    public RealtimeServiceImpl(RealtimeRepository repository, RealtimeMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RealtimeResponse create(RealtimeRequest request) {
        RealtimeEntity entity = mapper.toEntity(request);
        RealtimeEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public RealtimeResponse findById(Long id) {
        RealtimeEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Realtime not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RealtimeResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RealtimeResponse update(Long id, RealtimeRequest request) {
        RealtimeEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Realtime not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        RealtimeEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        RealtimeEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Realtime not found with id: " + id));
        repository.delete(entity);
    }
}
