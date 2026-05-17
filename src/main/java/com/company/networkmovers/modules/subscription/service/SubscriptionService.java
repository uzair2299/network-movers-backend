package com.company.networkmovers.modules.subscription.service;

import com.company.networkmovers.modules.subscription.dto.request.SubscriptionRequest;
import com.company.networkmovers.modules.subscription.dto.response.SubscriptionResponse;
import java.util.List;

public interface SubscriptionService {
    SubscriptionResponse create(SubscriptionRequest request);
    SubscriptionResponse findById(Long id);
    List<SubscriptionResponse> findAll();
    SubscriptionResponse update(Long id, SubscriptionRequest request);
    void delete(Long id);
}
