package com.company.networkmovers.modules.promotion.mapper;

import com.company.networkmovers.modules.promotion.entity.PromotionEntity;
import com.company.networkmovers.modules.promotion.dto.request.PromotionRequest;
import com.company.networkmovers.modules.promotion.dto.response.PromotionResponse;
import org.springframework.stereotype.Component;

@Component
public class PromotionMapper {

    public PromotionEntity toEntity(PromotionRequest request) {
        if (request == null) return null;
        return PromotionEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public PromotionResponse toResponse(PromotionEntity entity) {
        if (entity == null) return null;
        return PromotionResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
