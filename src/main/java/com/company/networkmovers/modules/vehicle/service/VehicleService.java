package com.company.networkmovers.modules.vehicle.service;

import com.company.networkmovers.modules.vehicle.dto.request.VehicleRequest;
import com.company.networkmovers.modules.vehicle.dto.response.VehicleResponse;
import java.util.List;

public interface VehicleService {
    VehicleResponse create(VehicleRequest request);
    VehicleResponse findById(Long id);
    List<VehicleResponse> findAll();
    VehicleResponse update(Long id, VehicleRequest request);
    void delete(Long id);
}
