package com.company.networkmovers.modules.review.service.impl;

import com.company.networkmovers.modules.review.entity.ReviewEntity;
import com.company.networkmovers.modules.review.repository.ReviewRepository;
import com.company.networkmovers.modules.review.service.ReviewService;
import com.company.networkmovers.modules.review.dto.request.ReviewRequest;
import com.company.networkmovers.modules.review.dto.response.ReviewResponse;
import com.company.networkmovers.modules.review.mapper.ReviewMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository repository;
    private final ReviewMapper mapper;

    public ReviewServiceImpl(ReviewRepository repository, ReviewMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ReviewResponse create(ReviewRequest request) {
        ReviewEntity entity = mapper.toEntity(request);
        ReviewEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ReviewResponse findById(Long id) {
        ReviewEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewResponse update(Long id, ReviewRequest request) {
        ReviewEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        ReviewEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        ReviewEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));
        repository.delete(entity);
    }
}
