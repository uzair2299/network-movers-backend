package com.company.networkmovers.modules.fleet.mapper;

import com.company.networkmovers.modules.fleet.entity.VehicleMake;
import com.company.networkmovers.modules.fleet.dto.request.VehicleMakeRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleMakeResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class VehicleMakeMapper implements GenericMapper<VehicleMake, VehicleMakeRequest, VehicleMakeResponse> {

    @Override
    public VehicleMake toEntity(VehicleMakeRequest request) {
        if (request == null) return null;
        return VehicleMake.builder()
                .name(request.getName())
                .code(request.getCode())
                .active(request.isActive())
                .country(request.getCountry())
                .build();
    }

    @Override
    public VehicleMakeResponse toResponse(VehicleMake entity) {
        if (entity == null) return null;
        return VehicleMakeResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .code(entity.getCode())
                .active(entity.isActive())
                .country(entity.getCountry())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
