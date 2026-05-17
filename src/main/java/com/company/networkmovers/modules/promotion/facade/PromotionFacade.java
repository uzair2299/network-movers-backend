package com.company.networkmovers.modules.promotion.facade;

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

    public PromotionResponse findById(Long id) {
        return service.findById(id);
    }

    public List<PromotionResponse> findAll() {
        return service.findAll();
    }

    public PromotionResponse update(Long id, PromotionRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
