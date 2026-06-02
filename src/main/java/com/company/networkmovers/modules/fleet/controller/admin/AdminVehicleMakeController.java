package com.company.networkmovers.modules.fleet.controller.admin;

import com.company.networkmovers.modules.fleet.dto.request.VehicleMakeRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleMakeResponse;
import com.company.networkmovers.modules.fleet.entity.VehicleMake;
import com.company.networkmovers.modules.fleet.service.VehicleMakeService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/fleet/vehicle-makes")
@Tag(name = "Admin Vehicle Makes", description = "Admin API for managing Vehicle Makes")
public class AdminVehicleMakeController extends AbstractLookupController<VehicleMakeRequest, VehicleMakeResponse> {

    public AdminVehicleMakeController(VehicleMakeService service) {
        super(service);
    }
}
