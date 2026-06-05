package com.company.networkmovers.modules.pricing.service;

import java.util.UUID;

import com.company.networkmovers.modules.pricing.dto.request.PricingRequest;
import com.company.networkmovers.modules.pricing.dto.response.PricingResponse;
import java.util.List;

public interface PricingService {
    PricingResponse create(PricingRequest request);
    PricingResponse findById(UUID id);
    List<PricingResponse> findAll();
    PricingResponse update(UUID id, PricingRequest request);
    void delete(UUID id);
}
