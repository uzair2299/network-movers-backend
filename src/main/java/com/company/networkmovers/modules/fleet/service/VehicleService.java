package com.company.networkmovers.modules.fleet.service;

import com.company.networkmovers.modules.fleet.dto.request.VehicleRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleResponse;
import com.company.networkmovers.modules.fleet.entity.enums.VehicleStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VehicleService {

    VehicleResponse create(VehicleRequest request);

    VehicleResponse update(UUID id, VehicleRequest request);
    
    VehicleResponse updateStatus(UUID id, VehicleStatus status);

    VehicleResponse getById(UUID id);

    Page<VehicleResponse> search(String query, Pageable pageable);
    
    Page<VehicleResponse> searchByStatus(VehicleStatus status, Pageable pageable);

    void delete(UUID id);
}
