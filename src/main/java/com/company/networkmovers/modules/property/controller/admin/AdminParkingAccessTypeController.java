package com.company.networkmovers.modules.property.controller.admin;

import com.company.networkmovers.modules.property.service.ParkingAccessTypeService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/parking-access-type")
public class AdminParkingAccessTypeController extends AbstractLookupController<LookupRequest, LookupResponse> {

    public AdminParkingAccessTypeController(ParkingAccessTypeService service) {
        super(service);
    }
}
