package com.company.networkmovers.modules.mover.service.impl;

import com.company.networkmovers.modules.mover.entity.MoverEntity;
import com.company.networkmovers.modules.mover.repository.MoverRepository;
import com.company.networkmovers.modules.mover.service.MoverService;
import com.company.networkmovers.modules.mover.dto.request.MoverRequest;
import com.company.networkmovers.modules.mover.dto.response.MoverResponse;
import com.company.networkmovers.modules.mover.mapper.MoverMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MoverServiceImpl implements MoverService {

    private final MoverRepository repository;
    private final MoverMapper mapper;

    public MoverServiceImpl(MoverRepository repository, MoverMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MoverResponse create(MoverRequest request) {
        MoverEntity entity = mapper.toEntity(request);
        MoverEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MoverResponse findById(Long id) {
        MoverEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mover not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MoverResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MoverResponse update(Long id, MoverRequest request) {
        MoverEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mover not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        MoverEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        MoverEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mover not found with id: " + id));
        repository.delete(entity);
    }
}
