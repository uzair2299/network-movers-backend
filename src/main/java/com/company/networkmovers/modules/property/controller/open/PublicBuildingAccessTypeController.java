package com.company.networkmovers.modules.property.controller.open;

import com.company.networkmovers.modules.property.service.BuildingAccessTypeService;
import com.company.networkmovers.shared.controller.AbstractPublicLookupController;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/building-access-type")
public class PublicBuildingAccessTypeController extends AbstractPublicLookupController<LookupRequest, LookupResponse> {

    public PublicBuildingAccessTypeController(BuildingAccessTypeService service) {
        super(service);
    }
}
