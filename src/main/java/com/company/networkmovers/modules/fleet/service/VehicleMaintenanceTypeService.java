package com.company.networkmovers.modules.fleet.service;

import com.company.networkmovers.modules.fleet.dto.request.VehicleMaintenanceTypeRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleMaintenanceTypeResponse;
import com.company.networkmovers.modules.fleet.entity.VehicleMaintenanceType;
import com.company.networkmovers.shared.service.GenericLookupService;

public interface VehicleMaintenanceTypeService extends GenericLookupService<VehicleMaintenanceTypeRequest, VehicleMaintenanceTypeResponse> {
}
