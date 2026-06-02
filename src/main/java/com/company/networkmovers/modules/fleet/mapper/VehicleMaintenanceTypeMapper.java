package com.company.networkmovers.modules.fleet.mapper;

import com.company.networkmovers.modules.fleet.entity.VehicleMaintenanceType;
import com.company.networkmovers.modules.fleet.dto.request.VehicleMaintenanceTypeRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleMaintenanceTypeResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class VehicleMaintenanceTypeMapper implements GenericMapper<VehicleMaintenanceType, VehicleMaintenanceTypeRequest, VehicleMaintenanceTypeResponse> {

    @Override
    public VehicleMaintenanceType toEntity(VehicleMaintenanceTypeRequest request) {
        if (request == null) return null;
        return VehicleMaintenanceType.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .description(request.getDescription())
                .build();
    }

    @Override
    public VehicleMaintenanceTypeResponse toResponse(VehicleMaintenanceType entity) {
        if (entity == null) return null;
        return VehicleMaintenanceTypeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .active(entity.isActive())
                .description(entity.getDescription())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
