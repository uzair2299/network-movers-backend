package com.company.networkmovers.modules.pricing.mapper;

import com.company.networkmovers.modules.pricing.entity.PricingEntity;
import com.company.networkmovers.modules.pricing.dto.request.PricingRequest;
import com.company.networkmovers.modules.pricing.dto.response.PricingResponse;
import org.springframework.stereotype.Component;

@Component
public class PricingMapper {

    public PricingEntity toEntity(PricingRequest request) {
        if (request == null) return null;
        return PricingEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public PricingResponse toResponse(PricingEntity entity) {
        if (entity == null) return null;
        return PricingResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
