package com.company.networkmovers.modules.fleet.service;

import com.company.networkmovers.modules.fleet.dto.request.VehicleTypeRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleTypeResponse;
import com.company.networkmovers.modules.fleet.entity.VehicleType;
import com.company.networkmovers.shared.service.GenericLookupService;

public interface VehicleTypeService extends GenericLookupService<VehicleTypeRequest, VehicleTypeResponse> {
}
