package com.company.networkmovers.modules.fleet.controller.admin;

import com.company.networkmovers.modules.fleet.dto.request.VehicleMaintenanceTypeRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleMaintenanceTypeResponse;
import com.company.networkmovers.modules.fleet.entity.VehicleMaintenanceType;
import com.company.networkmovers.modules.fleet.service.VehicleMaintenanceTypeService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/fleet/vehicle-maintenance-types")
@Tag(name = "Admin Vehicle Maintenance Types", description = "Admin API for managing Vehicle Maintenance Types")
public class AdminVehicleMaintenanceTypeController extends AbstractLookupController<VehicleMaintenanceTypeRequest, VehicleMaintenanceTypeResponse> {

    public AdminVehicleMaintenanceTypeController(VehicleMaintenanceTypeService service) {
        super(service);
    }
}
