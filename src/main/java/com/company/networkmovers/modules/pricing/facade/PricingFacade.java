package com.company.networkmovers.modules.pricing.facade;

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

    public PricingResponse findById(Long id) {
        return service.findById(id);
    }

    public List<PricingResponse> findAll() {
        return service.findAll();
    }

    public PricingResponse update(Long id, PricingRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
