package com.company.networkmovers.modules.property.controller.open;

import com.company.networkmovers.modules.property.service.FloorTypeService;
import com.company.networkmovers.shared.controller.AbstractPublicLookupController;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/floor-type")
public class PublicFloorTypeController extends AbstractPublicLookupController<LookupRequest, LookupResponse> {

    public PublicFloorTypeController(FloorTypeService service) {
        super(service);
    }
}
