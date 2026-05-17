package com.company.networkmovers.modules.location.mapper;

import com.company.networkmovers.modules.location.entity.LocationEntity;
import com.company.networkmovers.modules.location.dto.request.LocationRequest;
import com.company.networkmovers.modules.location.dto.response.LocationResponse;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper {

    public LocationEntity toEntity(LocationRequest request) {
        if (request == null) return null;
        return LocationEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public LocationResponse toResponse(LocationEntity entity) {
        if (entity == null) return null;
        return LocationResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
