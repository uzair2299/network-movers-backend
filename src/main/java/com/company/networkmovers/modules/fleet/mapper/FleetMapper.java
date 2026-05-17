package com.company.networkmovers.modules.fleet.mapper;

import com.company.networkmovers.modules.fleet.entity.FleetEntity;
import com.company.networkmovers.modules.fleet.dto.request.FleetRequest;
import com.company.networkmovers.modules.fleet.dto.response.FleetResponse;
import org.springframework.stereotype.Component;

@Component
public class FleetMapper {

    public FleetEntity toEntity(FleetRequest request) {
        if (request == null) return null;
        return FleetEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public FleetResponse toResponse(FleetEntity entity) {
        if (entity == null) return null;
        return FleetResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
