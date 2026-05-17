package com.company.networkmovers.modules.subscription.service.impl;

import com.company.networkmovers.modules.subscription.entity.SubscriptionEntity;
import com.company.networkmovers.modules.subscription.repository.SubscriptionRepository;
import com.company.networkmovers.modules.subscription.service.SubscriptionService;
import com.company.networkmovers.modules.subscription.dto.request.SubscriptionRequest;
import com.company.networkmovers.modules.subscription.dto.response.SubscriptionResponse;
import com.company.networkmovers.modules.subscription.mapper.SubscriptionMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository repository;
    private final SubscriptionMapper mapper;

    public SubscriptionServiceImpl(SubscriptionRepository repository, SubscriptionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public SubscriptionResponse create(SubscriptionRequest request) {
        SubscriptionEntity entity = mapper.toEntity(request);
        SubscriptionEntity saved = repository.save(entity);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionResponse findById(Long id) {
        SubscriptionEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id: " + id));
        return mapper.toResponse(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public SubscriptionResponse update(Long id, SubscriptionRequest request) {
        SubscriptionEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id: " + id));
        entity.setName(request.getName());
        entity.setDescription(request.getDescription());
        SubscriptionEntity updated = repository.save(entity);
        return mapper.toResponse(updated);
    }

    @Override
    public void delete(Long id) {
        SubscriptionEntity entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found with id: " + id));
        repository.delete(entity);
    }
}
