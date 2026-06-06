package com.company.networkmovers.modules.fleet.controller.admin;

import com.company.networkmovers.modules.fleet.dto.request.VehicleModelRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleModelResponse;
import com.company.networkmovers.modules.fleet.service.VehicleModelService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/fleet/vehicle-models")
@Tag(name = "Admin Vehicle Models", description = "Admin API for managing Vehicle Models")
public class AdminVehicleModelController extends AbstractLookupController<VehicleModelRequest, VehicleModelResponse> {

    public AdminVehicleModelController(VehicleModelService service) {
        super(service);
    }
}
