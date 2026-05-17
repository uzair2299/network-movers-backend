package com.company.networkmovers.modules.recommendation.service.impl;

import com.company.networkmovers.modules.recommendation.entity.RecommendationEntity;
import com.company.networkmovers.modules.recommendation.repository.RecommendationRepository;
import com.company.networkmovers.modules.recommendation.service.RecommendationService;
import com.company.networkmovers.modules.recommendation.dto.request.RecommendationRequest;
import com.company.networkmovers.modules.recommendation.dto.response.RecommendationResponse;
import com.company.networkmovers.modules.recommendation.mapper.RecommendationMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecommendationServiceImpl implements RecommendationService {

    private final RecommendationRepository repository;
    private final RecommendationMapper mapper;

    public RecommendationServiceImpl(RecommendationRepository repository, RecommendationMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RecommendationResponse create(RecommendationRequest request) {
        RecommendationEntity entity = mapper.toEntity(request);
        RecommendationEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public RecommendationResponse findById(Long id) {
        RecommendationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recommendation not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecommendationResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RecommendationResponse update(Long id, RecommendationRequest request) {
        RecommendationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recommendation not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        RecommendationEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        RecommendationEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recommendation not found with id: " + id));
        repository.delete(entity);
    }
}
