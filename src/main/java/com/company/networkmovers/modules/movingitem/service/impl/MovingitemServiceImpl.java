package com.company.networkmovers.modules.movingitem.service.impl;

import com.company.networkmovers.modules.movingitem.entity.MovingitemEntity;
import com.company.networkmovers.modules.movingitem.repository.MovingitemRepository;
import com.company.networkmovers.modules.movingitem.service.MovingitemService;
import com.company.networkmovers.modules.movingitem.dto.request.MovingitemRequest;
import com.company.networkmovers.modules.movingitem.dto.response.MovingitemResponse;
import com.company.networkmovers.modules.movingitem.mapper.MovingitemMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovingitemServiceImpl implements MovingitemService {

    private final MovingitemRepository repository;
    private final MovingitemMapper mapper;

    public MovingitemServiceImpl(MovingitemRepository repository, MovingitemMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MovingitemResponse create(MovingitemRequest request) {
        MovingitemEntity entity = mapper.toEntity(request);
        MovingitemEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MovingitemResponse findById(Long id) {
        MovingitemEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movingitem not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovingitemResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MovingitemResponse update(Long id, MovingitemRequest request) {
        MovingitemEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movingitem not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        MovingitemEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        MovingitemEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movingitem not found with id: " + id));
        repository.delete(entity);
    }
}
