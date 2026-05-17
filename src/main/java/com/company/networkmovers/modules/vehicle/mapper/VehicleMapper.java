package com.company.networkmovers.modules.vehicle.mapper;

import com.company.networkmovers.modules.vehicle.entity.VehicleEntity;
import com.company.networkmovers.modules.vehicle.dto.request.VehicleRequest;
import com.company.networkmovers.modules.vehicle.dto.response.VehicleResponse;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public VehicleEntity toEntity(VehicleRequest request) {
        if (request == null) return null;
        return VehicleEntity.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();
    }

    public VehicleResponse toResponse(VehicleEntity entity) {
        if (entity == null) return null;
        return VehicleResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .createdBy(entity.getCreatedBy())
                .build();
    }
}
