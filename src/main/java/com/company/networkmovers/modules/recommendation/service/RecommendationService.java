package com.company.networkmovers.modules.recommendation.service;

import java.util.UUID;

import com.company.networkmovers.modules.recommendation.dto.request.RecommendationRequest;
import com.company.networkmovers.modules.recommendation.dto.response.RecommendationResponse;
import java.util.List;

public interface RecommendationService {
    RecommendationResponse create(RecommendationRequest request);
    RecommendationResponse findById(UUID id);
    List<RecommendationResponse> findAll();
    RecommendationResponse update(UUID id, RecommendationRequest request);
    void delete(UUID id);
}
