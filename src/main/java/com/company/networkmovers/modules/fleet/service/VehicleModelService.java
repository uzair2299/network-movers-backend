package com.company.networkmovers.modules.fleet.service;

import com.company.networkmovers.modules.fleet.dto.request.VehicleModelRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleModelResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VehicleModelService {

    VehicleModelResponse create(VehicleModelRequest request);

    VehicleModelResponse update(UUID id, VehicleModelRequest request);

    VehicleModelResponse getById(UUID id);

    Page<VehicleModelResponse> search(String query, Pageable pageable);

    void delete(UUID id);
}
