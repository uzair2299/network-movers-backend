package com.company.networkmovers.modules.fleet.service;

import com.company.networkmovers.modules.fleet.dto.request.VehicleMakeRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleMakeResponse;
import com.company.networkmovers.modules.fleet.entity.VehicleMake;
import com.company.networkmovers.shared.service.GenericLookupService;

public interface VehicleMakeService extends GenericLookupService<VehicleMakeRequest, VehicleMakeResponse> {
}
