package com.company.networkmovers.modules.recommendation.facade;

import com.company.networkmovers.modules.recommendation.dto.request.RecommendationRequest;
import com.company.networkmovers.modules.recommendation.dto.response.RecommendationResponse;
import com.company.networkmovers.modules.recommendation.service.RecommendationService;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class RecommendationFacade {

    private final RecommendationService service;

    public RecommendationFacade(RecommendationService service) {
        this.service = service;
    }

    public RecommendationResponse create(RecommendationRequest request) {
        return service.create(request);
    }

    public RecommendationResponse findById(Long id) {
        return service.findById(id);
    }

    public List<RecommendationResponse> findAll() {
        return service.findAll();
    }

    public RecommendationResponse update(Long id, RecommendationRequest request) {
        return service.update(id, request);
    }

    public void delete(Long id) {
        service.delete(id);
    }
}
