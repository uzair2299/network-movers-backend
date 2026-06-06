package com.company.networkmovers.modules.fleet.service;

import com.company.networkmovers.modules.fleet.dto.request.VehicleRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleResponse;
import com.company.networkmovers.modules.fleet.entity.enums.VehicleStatus;
import com.company.networkmovers.shared.service.GenericLookupService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface VehicleService extends GenericLookupService<VehicleRequest, VehicleResponse> {

    VehicleResponse updateStatus(UUID id, VehicleStatus status);

    Page<VehicleResponse> searchByStatus(VehicleStatus status, Pageable pageable);
}
