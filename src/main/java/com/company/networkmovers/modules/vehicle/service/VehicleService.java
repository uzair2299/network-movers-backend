package com.company.networkmovers.modules.vehicle.service;

import java.util.UUID;

import com.company.networkmovers.modules.vehicle.dto.request.VehicleRequest;
import com.company.networkmovers.modules.vehicle.dto.response.VehicleResponse;
import java.util.List;

public interface VehicleService {
    VehicleResponse create(VehicleRequest request);
    VehicleResponse findById(UUID id);
    List<VehicleResponse> findAll();
    VehicleResponse update(UUID id, VehicleRequest request);
    void delete(UUID id);
}
