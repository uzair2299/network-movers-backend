package com.company.networkmovers.modules.promotion.service.impl;

import com.company.networkmovers.modules.promotion.entity.PromotionEntity;
import com.company.networkmovers.modules.promotion.repository.PromotionRepository;
import com.company.networkmovers.modules.promotion.service.PromotionService;
import com.company.networkmovers.modules.promotion.dto.request.PromotionRequest;
import com.company.networkmovers.modules.promotion.dto.response.PromotionResponse;
import com.company.networkmovers.modules.promotion.mapper.PromotionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository repository;
    private final PromotionMapper mapper;

    public PromotionServiceImpl(PromotionRepository repository, PromotionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PromotionResponse create(PromotionRequest request) {
        PromotionEntity entity = mapper.toEntity(request);
        PromotionEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PromotionResponse findById(Long id) {
        PromotionEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PromotionResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PromotionResponse update(Long id, PromotionRequest request) {
        PromotionEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        PromotionEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        PromotionEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion not found with id: " + id));
        repository.delete(entity);
    }
}
