package com.company.networkmovers.modules.pricing.service.impl;

import com.company.networkmovers.modules.pricing.entity.PricingEntity;
import com.company.networkmovers.modules.pricing.repository.PricingRepository;
import com.company.networkmovers.modules.pricing.service.PricingService;
import com.company.networkmovers.modules.pricing.dto.request.PricingRequest;
import com.company.networkmovers.modules.pricing.dto.response.PricingResponse;
import com.company.networkmovers.modules.pricing.mapper.PricingMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PricingServiceImpl implements PricingService {

    private final PricingRepository repository;
    private final PricingMapper mapper;

    public PricingServiceImpl(PricingRepository repository, PricingMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public PricingResponse create(PricingRequest request) {
        PricingEntity entity = mapper.toEntity(request);
        PricingEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public PricingResponse findById(Long id) {
        PricingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pricing not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PricingResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public PricingResponse update(Long id, PricingRequest request) {
        PricingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pricing not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        PricingEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        PricingEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pricing not found with id: " + id));
        repository.delete(entity);
    }
}
