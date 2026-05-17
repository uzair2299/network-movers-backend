package com.company.networkmovers.modules.subscription.mapper;

import com.company.networkmovers.modules.subscription.entity.SubscriptionEntity;
import com.company.networkmovers.modules.subscription.dto.request.SubscriptionRequest;
import com.company.networkmovers.modules.subscription.dto.response.SubscriptionResponse;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionMapper {

    public SubscriptionEntity toEntity(SubscriptionRequest request) {
        if (request == null) return null;
        return SubscriptionEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public SubscriptionResponse toResponse(SubscriptionEntity entity) {
        if (entity == null) return null;
        return SubscriptionResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
