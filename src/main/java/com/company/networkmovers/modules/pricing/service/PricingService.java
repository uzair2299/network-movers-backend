package com.company.networkmovers.modules.pricing.service;

import com.company.networkmovers.modules.pricing.dto.request.PricingRequest;
import com.company.networkmovers.modules.pricing.dto.response.PricingResponse;
import java.util.List;

public interface PricingService {
    PricingResponse create(PricingRequest request);
    PricingResponse findById(Long id);
    List<PricingResponse> findAll();
    PricingResponse update(Long id, PricingRequest request);
    void delete(Long id);
}
