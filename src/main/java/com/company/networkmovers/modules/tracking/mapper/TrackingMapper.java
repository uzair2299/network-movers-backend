package com.company.networkmovers.modules.tracking.mapper;

import com.company.networkmovers.modules.tracking.entity.TrackingEntity;
import com.company.networkmovers.modules.tracking.dto.request.TrackingRequest;
import com.company.networkmovers.modules.tracking.dto.response.TrackingResponse;
import org.springframework.stereotype.Component;

@Component
public class TrackingMapper {

    public TrackingEntity toEntity(TrackingRequest request) {
        if (request == null) return null;
        return TrackingEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public TrackingResponse toResponse(TrackingEntity entity) {
        if (entity == null) return null;
        return TrackingResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
