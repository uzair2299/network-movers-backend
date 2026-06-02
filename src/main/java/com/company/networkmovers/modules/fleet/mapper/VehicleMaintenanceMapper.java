package com.company.networkmovers.modules.fleet.mapper;

import com.company.networkmovers.modules.fleet.entity.VehicleMaintenance;
import com.company.networkmovers.modules.fleet.dto.request.VehicleMaintenanceRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleMaintenanceResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class VehicleMaintenanceMapper implements GenericMapper<VehicleMaintenance, VehicleMaintenanceRequest, VehicleMaintenanceResponse> {

    private final VehicleMaintenanceTypeMapper vehicleMaintenanceTypeMapper;

    @Override
    public VehicleMaintenance toEntity(VehicleMaintenanceRequest request) {
        if (request == null) return null;
        return VehicleMaintenance.builder()
                .maintenanceDate(request.getMaintenanceDate())
                .odometerKm(request.getOdometerKm())
                .cost(request.getCost())
                .vendorName(request.getVendorName())
                .nextServiceDate(request.getNextServiceDate())
                .nextServiceKm(request.getNextServiceKm())
                .remarks(request.getRemarks())
                .build();
    }

    @Override
    public VehicleMaintenanceResponse toResponse(VehicleMaintenance entity) {
        if (entity == null) return null;
        return VehicleMaintenanceResponse.builder()
                .id(entity.getId())
                .vehicleId(entity.getVehicle().getId())
                .maintenanceDate(entity.getMaintenanceDate())
                .odometerKm(entity.getOdometerKm())
                .cost(entity.getCost())
                .vendorName(entity.getVendorName())
                .nextServiceDate(entity.getNextServiceDate())
                .nextServiceKm(entity.getNextServiceKm())
                .remarks(entity.getRemarks())
                .maintenanceType(vehicleMaintenanceTypeMapper.toResponse(entity.getMaintenanceType()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
