package com.company.networkmovers.modules.property.controller.mobile;

import com.company.networkmovers.modules.property.service.OccupancyTypeService;
import com.company.networkmovers.shared.controller.AbstractMobileLookupController;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mobile/occupancy-type")
public class MobileOccupancyTypeController extends AbstractMobileLookupController<LookupRequest, LookupResponse> {

    public MobileOccupancyTypeController(OccupancyTypeService service) {
        super(service);
    }
}
