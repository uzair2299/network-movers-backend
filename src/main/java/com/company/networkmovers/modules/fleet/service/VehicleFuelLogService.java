package com.company.networkmovers.modules.fleet.service;

import com.company.networkmovers.modules.fleet.dto.request.VehicleFuelLogRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleFuelLogResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VehicleFuelLogService {

    VehicleFuelLogResponse create(VehicleFuelLogRequest request);
    
    VehicleFuelLogResponse update(UUID id, VehicleFuelLogRequest request);

    VehicleFuelLogResponse getById(UUID id);

    Page<VehicleFuelLogResponse> getByVehicleId(UUID vehicleId, Pageable pageable);

    void delete(UUID id);
}
