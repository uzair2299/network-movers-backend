package com.company.networkmovers.modules.promotion.facade;

import java.util.UUID;

import com.company.networkmovers.modules.promotion.dto.request.PromotionRequest;
import com.company.networkmovers.modules.promotion.dto.response.PromotionResponse;
import com.company.networkmovers.modules.promotion.service.PromotionService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class PromotionFacade {

    private final PromotionService service;

    public PromotionFacade(PromotionService service) {
        this.service = service;
    }

    public PromotionResponse create(PromotionRequest request) {
        return service.create(request);
    }

    public PromotionResponse findById(UUID id) {
        return service.findById(id);
    }

    public List<PromotionResponse> findAll() {
        return service.findAll();
    }

    public PromotionResponse update(UUID id, PromotionRequest request) {
        return service.update(id, request);
    }

    public void delete(UUID id) {
        service.delete(id);
    }
}
