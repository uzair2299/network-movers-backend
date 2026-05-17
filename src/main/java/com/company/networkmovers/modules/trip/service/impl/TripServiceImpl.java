package com.company.networkmovers.modules.trip.service.impl;

import com.company.networkmovers.modules.trip.entity.TripEntity;
import com.company.networkmovers.modules.trip.repository.TripRepository;
import com.company.networkmovers.modules.trip.service.TripService;
import com.company.networkmovers.modules.trip.dto.request.TripRequest;
import com.company.networkmovers.modules.trip.dto.response.TripResponse;
import com.company.networkmovers.modules.trip.mapper.TripMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TripServiceImpl implements TripService {

    private final TripRepository repository;
    private final TripMapper mapper;

    public TripServiceImpl(TripRepository repository, TripMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TripResponse create(TripRequest request) {
        TripEntity entity = mapper.toEntity(request);
        TripEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public TripResponse findById(Long id) {
        TripEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TripResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public TripResponse update(Long id, TripRequest request) {
        TripEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        TripEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        TripEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found with id: " + id));
        repository.delete(entity);
    }
}
