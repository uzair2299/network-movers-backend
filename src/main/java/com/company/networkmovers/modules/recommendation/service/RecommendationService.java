package com.company.networkmovers.modules.recommendation.service;

import com.company.networkmovers.modules.recommendation.dto.request.RecommendationRequest;
import com.company.networkmovers.modules.recommendation.dto.response.RecommendationResponse;
import java.util.List;

public interface RecommendationService {
    RecommendationResponse create(RecommendationRequest request);
    RecommendationResponse findById(Long id);
    List<RecommendationResponse> findAll();
    RecommendationResponse update(Long id, RecommendationRequest request);
    void delete(Long id);
}
