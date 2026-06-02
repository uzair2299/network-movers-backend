package com.company.networkmovers.modules.fleet.mapper;

import com.company.networkmovers.modules.fleet.entity.VehicleModel;
import com.company.networkmovers.modules.fleet.dto.request.VehicleModelRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleModelResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleModelMapper implements GenericMapper<VehicleModel, VehicleModelRequest, VehicleModelResponse> {

    private final VehicleMakeMapper vehicleMakeMapper;
    private final VehicleTypeMapper vehicleTypeMapper;

    @Override
    public VehicleModel toEntity(VehicleModelRequest request) {
        if (request == null) return null;
        return VehicleModel.builder()
                .code(request.getCode())
                .name(request.getName())
                .active(request.isActive())
                .capacityKg(request.getCapacityKg())
                .capacityM3(request.getCapacityM3())
                .lengthM(request.getLengthM())
                .widthM(request.getWidthM())
                .heightM(request.getHeightM())
                .build();
    }

    @Override
    public VehicleModelResponse toResponse(VehicleModel entity) {
        if (entity == null) return null;
        return VehicleModelResponse.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .name(entity.getName())
                .active(entity.isActive())
                .capacityKg(entity.getCapacityKg())
                .capacityM3(entity.getCapacityM3())
                .lengthM(entity.getLengthM())
                .widthM(entity.getWidthM())
                .heightM(entity.getHeightM())
                .make(vehicleMakeMapper.toResponse(entity.getMake()))
                .vehicleType(vehicleTypeMapper.toResponse(entity.getVehicleType()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
