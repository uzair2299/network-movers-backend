package com.company.networkmovers.modules.subscription.facade;

import com.company.networkmovers.modules.subscription.dto.request.SubscriptionRequest;
import com.company.networkmovers.modules.subscription.dto.response.SubscriptionResponse;
import com.company.networkmovers.modules.subscription.service.SubscriptionService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class SubscriptionFacade {

    private final SubscriptionService service;

    public SubscriptionFacade(SubscriptionService service) {
        this.service = service;
    }

    public SubscriptionResponse create(SubscriptionRequest request) {
        return service.create(request);
    }

    public SubscriptionResponse findById(Long id) {
        return service.findById(id);
    }

    public List<SubscriptionResponse> findAll() {
        return service.findAll();
    }

    public SubscriptionResponse update(Long id, SubscriptionRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
