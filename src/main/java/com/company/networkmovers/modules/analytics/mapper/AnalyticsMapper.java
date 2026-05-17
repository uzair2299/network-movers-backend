package com.company.networkmovers.modules.analytics.mapper;

import com.company.networkmovers.modules.analytics.entity.AnalyticsEntity;
import com.company.networkmovers.modules.analytics.dto.request.AnalyticsRequest;
import com.company.networkmovers.modules.analytics.dto.response.AnalyticsResponse;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsMapper {

    public AnalyticsEntity toEntity(AnalyticsRequest request) {
        if (request == null) return null;
        return AnalyticsEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public AnalyticsResponse toResponse(AnalyticsEntity entity) {
        if (entity == null) return null;
        return AnalyticsResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
