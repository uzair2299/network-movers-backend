package com.company.networkmovers.modules.promotion.service;

import java.util.UUID;

import com.company.networkmovers.modules.promotion.dto.request.PromotionRequest;
import com.company.networkmovers.modules.promotion.dto.response.PromotionResponse;
import java.util.List;

public interface PromotionService {
    PromotionResponse create(PromotionRequest request);
    PromotionResponse findById(UUID id);
    List<PromotionResponse> findAll();
    PromotionResponse update(UUID id, PromotionRequest request);
    void delete(UUID id);
}
