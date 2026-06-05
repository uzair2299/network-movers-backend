package com.company.networkmovers.modules.subscription.service;

import java.util.UUID;

import com.company.networkmovers.modules.subscription.dto.request.SubscriptionRequest;
import com.company.networkmovers.modules.subscription.dto.response.SubscriptionResponse;
import java.util.List;

public interface SubscriptionService {
    SubscriptionResponse create(SubscriptionRequest request);
    SubscriptionResponse findById(UUID id);
    List<SubscriptionResponse> findAll();
    SubscriptionResponse update(UUID id, SubscriptionRequest request);
    void delete(UUID id);
}
