package com.company.networkmovers.modules.rating.service.impl;

import com.company.networkmovers.modules.rating.entity.RatingEntity;
import com.company.networkmovers.modules.rating.repository.RatingRepository;
import com.company.networkmovers.modules.rating.service.RatingService;
import com.company.networkmovers.modules.rating.dto.request.RatingRequest;
import com.company.networkmovers.modules.rating.dto.response.RatingResponse;
import com.company.networkmovers.modules.rating.mapper.RatingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RatingServiceImpl implements RatingService {

    private final RatingRepository repository;
    private final RatingMapper mapper;

    public RatingServiceImpl(RatingRepository repository, RatingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RatingResponse create(RatingRequest request) {
        RatingEntity entity = mapper.toEntity(request);
        RatingEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public RatingResponse findById(Long id) {
        RatingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RatingResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RatingResponse update(Long id, RatingRequest request) {
        RatingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        RatingEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        RatingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rating not found with id: " + id));
        repository.delete(entity);
    }
}
