package com.company.networkmovers.modules.fleet.mapper;

import com.company.networkmovers.modules.fleet.entity.Vehicle;
import com.company.networkmovers.modules.fleet.dto.request.VehicleRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleResponse;
import com.company.networkmovers.shared.mapper.GenericMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("fleetVehicleMapper")
@RequiredArgsConstructor
public class VehicleMapper implements GenericMapper<Vehicle, VehicleRequest, VehicleResponse> {

    private final VehicleModelMapper vehicleModelMapper;

    @Override
    public Vehicle toEntity(VehicleRequest request) {
        if (request == null) return null;
        return Vehicle.builder()
                .vehicleCode(request.getVehicleCode())
                .registrationNo(request.getRegistrationNo())
                .manufactureYear(request.getManufactureYear())
                .ownershipType(request.getOwnershipType())
                .status(request.getStatus())
                .currentOdometerKm(request.getCurrentOdometerKm())
                .insuranceExpiryDate(request.getInsuranceExpiryDate())
                .fitnessExpiryDate(request.getFitnessExpiryDate())
                .acquisitionDate(request.getAcquisitionDate())
                .active(request.isActive())
                .remarks(request.getRemarks())
                .build();
    }

    @Override
    public VehicleResponse toResponse(Vehicle entity) {
        if (entity == null) return null;
        return VehicleResponse.builder()
                .id(entity.getId())
                .vehicleCode(entity.getVehicleCode())
                .registrationNo(entity.getRegistrationNo())
                .manufactureYear(entity.getManufactureYear())
                .ownershipType(entity.getOwnershipType())
                .status(entity.getStatus())
                .currentOdometerKm(entity.getCurrentOdometerKm())
                .insuranceExpiryDate(entity.getInsuranceExpiryDate())
                .fitnessExpiryDate(entity.getFitnessExpiryDate())
                .acquisitionDate(entity.getAcquisitionDate())
                .active(entity.isActive())
                .remarks(entity.getRemarks())
                .vehicleModel(vehicleModelMapper.toResponse(entity.getVehicleModel()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
