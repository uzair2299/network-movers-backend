package com.company.networkmovers.modules.promotion.service;

import com.company.networkmovers.modules.promotion.dto.request.PromotionRequest;
import com.company.networkmovers.modules.promotion.dto.response.PromotionResponse;
import java.util.List;

public interface PromotionService {
    PromotionResponse create(PromotionRequest request);
    PromotionResponse findById(Long id);
    List<PromotionResponse> findAll();
    PromotionResponse update(Long id, PromotionRequest request);
    void delete(Long id);
}
