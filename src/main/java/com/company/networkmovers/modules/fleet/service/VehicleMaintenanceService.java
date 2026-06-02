package com.company.networkmovers.modules.fleet.service;

import com.company.networkmovers.modules.fleet.dto.request.VehicleMaintenanceRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleMaintenanceResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VehicleMaintenanceService {

    VehicleMaintenanceResponse create(VehicleMaintenanceRequest request);
    
    VehicleMaintenanceResponse update(UUID id, VehicleMaintenanceRequest request);

    VehicleMaintenanceResponse getById(UUID id);

    Page<VehicleMaintenanceResponse> getByVehicleId(UUID vehicleId, Pageable pageable);
    
    Page<VehicleMaintenanceResponse> getUpcomingMaintenance(int daysAhead, Pageable pageable);

    void delete(UUID id);
}
