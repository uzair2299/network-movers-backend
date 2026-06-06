package com.company.networkmovers.modules.fleet.service;

import com.company.networkmovers.modules.fleet.dto.request.VehicleModelRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleModelResponse;
import com.company.networkmovers.shared.service.GenericLookupService;

public interface VehicleModelService extends GenericLookupService<VehicleModelRequest, VehicleModelResponse> {
}
