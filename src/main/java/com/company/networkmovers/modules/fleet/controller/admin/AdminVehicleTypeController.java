package com.company.networkmovers.modules.fleet.controller.admin;

import com.company.networkmovers.modules.fleet.dto.request.VehicleTypeRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleTypeResponse;
import com.company.networkmovers.modules.fleet.entity.VehicleType;
import com.company.networkmovers.modules.fleet.service.VehicleTypeService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/fleet/vehicle-types")
@Tag(name = "Admin Vehicle Types", description = "Admin API for managing Vehicle Types")
public class AdminVehicleTypeController extends AbstractLookupController<VehicleTypeRequest, VehicleTypeResponse> {

    public AdminVehicleTypeController(VehicleTypeService service) {
        super(service);
    }
}
