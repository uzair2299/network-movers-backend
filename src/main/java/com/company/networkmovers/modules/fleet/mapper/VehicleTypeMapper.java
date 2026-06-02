package com.company.networkmovers.modules.fleet.mapper;

import com.company.networkmovers.modules.fleet.entity.VehicleType;
import com.company.networkmovers.modules.fleet.dto.request.VehicleTypeRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleTypeResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class VehicleTypeMapper implements GenericMapper<VehicleType, VehicleTypeRequest, VehicleTypeResponse> {

    @Override
    public VehicleType toEntity(VehicleTypeRequest request) {
        if (request == null) return null;
        return VehicleType.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .description(request.getDescription())
                .build();
    }

    @Override
    public VehicleTypeResponse toResponse(VehicleType entity) {
        if (entity == null) return null;
        return VehicleTypeResponse.builder()
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
