package com.company.networkmovers.modules.pricing.facade;

import java.util.UUID;

import com.company.networkmovers.modules.pricing.dto.request.PricingRequest;
import com.company.networkmovers.modules.pricing.dto.response.PricingResponse;
import com.company.networkmovers.modules.pricing.service.PricingService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PricingFacade {

    private final PricingService service;

    public PricingFacade(PricingService service) {
        this.service = service;
    }

    public PricingResponse create(PricingRequest request) {
        return service.create(request);
    }

    public PricingResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<PricingResponse> findAll() {
        return service.findAll();
    }

    public PricingResponse update(UUID id, PricingRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
