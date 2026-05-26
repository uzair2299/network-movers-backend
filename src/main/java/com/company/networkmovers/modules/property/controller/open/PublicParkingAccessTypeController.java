package com.company.networkmovers.modules.property.controller.open;

import com.company.networkmovers.modules.property.service.ParkingAccessTypeService;
import com.company.networkmovers.shared.controller.AbstractPublicLookupController;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/parking-access-type")
public class PublicParkingAccessTypeController extends AbstractPublicLookupController<LookupRequest, LookupResponse> {

    public PublicParkingAccessTypeController(ParkingAccessTypeService service) {
        super(service);
    }
}
