package com.company.networkmovers.modules.property.controller.admin;

import com.company.networkmovers.modules.property.service.FloorTypeService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/floor-type")
public class AdminFloorTypeController extends AbstractLookupController<LookupRequest, LookupResponse> {

    public AdminFloorTypeController(FloorTypeService service) {
        super(service);
    }
}
