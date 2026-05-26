package com.company.networkmovers.modules.property.controller.mobile;

import com.company.networkmovers.modules.property.service.BuildingAccessTypeService;
import com.company.networkmovers.shared.controller.AbstractMobileLookupController;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mobile/building-access-type")
public class MobileBuildingAccessTypeController extends AbstractMobileLookupController<LookupRequest, LookupResponse> {

    public MobileBuildingAccessTypeController(BuildingAccessTypeService service) {
        super(service);
    }
}
