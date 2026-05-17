package com.company.networkmovers.modules.recommendation.mapper;

import com.company.networkmovers.modules.recommendation.entity.RecommendationEntity;
import com.company.networkmovers.modules.recommendation.dto.request.RecommendationRequest;
import com.company.networkmovers.modules.recommendation.dto.response.RecommendationResponse;
import org.springframework.stereotype.Component;

@Component
public class RecommendationMapper {

    public RecommendationEntity toEntity(RecommendationRequest request) {
        if (request == null) return null;
        return RecommendationEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public RecommendationResponse toResponse(RecommendationEntity entity) {
        if (entity == null) return null;
        return RecommendationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
