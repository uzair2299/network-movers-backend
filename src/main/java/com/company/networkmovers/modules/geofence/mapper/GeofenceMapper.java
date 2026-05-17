package com.company.networkmovers.modules.geofence.mapper;

import com.company.networkmovers.modules.geofence.entity.GeofenceEntity;
import com.company.networkmovers.modules.geofence.dto.request.GeofenceRequest;
import com.company.networkmovers.modules.geofence.dto.response.GeofenceResponse;
import org.springframework.stereotype.Component;

@Component
public class GeofenceMapper {

    public GeofenceEntity toEntity(GeofenceRequest request) {
        if (request == null) return null;
        return GeofenceEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public GeofenceResponse toResponse(GeofenceEntity entity) {
        if (entity == null) return null;
        return GeofenceResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
