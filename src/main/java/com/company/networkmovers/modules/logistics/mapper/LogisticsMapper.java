package com.company.networkmovers.modules.logistics.mapper;

import com.company.networkmovers.modules.logistics.entity.LogisticsEntity;
import com.company.networkmovers.modules.logistics.dto.request.LogisticsRequest;
import com.company.networkmovers.modules.logistics.dto.response.LogisticsResponse;
import org.springframework.stereotype.Component;

@Component
public class LogisticsMapper {

    public LogisticsEntity toEntity(LogisticsRequest request) {
        if (request == null) return null;
        return LogisticsEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public LogisticsResponse toResponse(LogisticsEntity entity) {
        if (entity == null) return null;
        return LogisticsResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
