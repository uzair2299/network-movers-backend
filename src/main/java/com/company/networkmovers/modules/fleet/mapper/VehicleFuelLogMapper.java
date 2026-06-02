package com.company.networkmovers.modules.fleet.mapper;

import com.company.networkmovers.modules.fleet.entity.VehicleFuelLog;
import com.company.networkmovers.modules.fleet.dto.request.VehicleFuelLogRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleFuelLogResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import org.springframework.stereotype.Component;

@Component
public class VehicleFuelLogMapper implements GenericMapper<VehicleFuelLog, VehicleFuelLogRequest, VehicleFuelLogResponse> {

    @Override
    public VehicleFuelLog toEntity(VehicleFuelLogRequest request) {
        if (request == null) return null;
        return VehicleFuelLog.builder()
                .fuelDate(request.getFuelDate())
                .fuelQuantityLiters(request.getFuelQuantityLiters())
                .costAmount(request.getCostAmount())
                .odometerKm(request.getOdometerKm())
                .fuelStation(request.getFuelStation())
                .remarks(request.getRemarks())
                .build();
    }

    @Override
    public VehicleFuelLogResponse toResponse(VehicleFuelLog entity) {
        if (entity == null) return null;
        return VehicleFuelLogResponse.builder()
                .id(entity.getId())
                .vehicleId(entity.getVehicle().getId())
                .fuelDate(entity.getFuelDate())
                .fuelQuantityLiters(entity.getFuelQuantityLiters())
                .costAmount(entity.getCostAmount())
                .odometerKm(entity.getOdometerKm())
                .fuelStation(entity.getFuelStation())
                .remarks(entity.getRemarks())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
