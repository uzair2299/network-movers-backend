package com.company.networkmovers.modules.maps.service.impl;

import com.company.networkmovers.modules.maps.entity.MapsEntity;
import com.company.networkmovers.modules.maps.repository.MapsRepository;
import com.company.networkmovers.modules.maps.service.MapsService;
import com.company.networkmovers.modules.maps.dto.request.MapsRequest;
import com.company.networkmovers.modules.maps.dto.response.MapsResponse;
import com.company.networkmovers.modules.maps.mapper.MapsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MapsServiceImpl implements MapsService {

    private final MapsRepository repository;
    private final MapsMapper mapper;

    public MapsServiceImpl(MapsRepository repository, MapsMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public MapsResponse create(MapsRequest request) {
        MapsEntity entity = mapper.toEntity(request);
        MapsEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public MapsResponse findById(Long id) {
        MapsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maps not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MapsResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public MapsResponse update(Long id, MapsRequest request) {
        MapsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maps not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        MapsEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        MapsEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maps not found with id: " + id));
        repository.delete(entity);
    }
}
