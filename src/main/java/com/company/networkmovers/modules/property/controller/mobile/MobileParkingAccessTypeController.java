package com.company.networkmovers.modules.property.controller.mobile;

import com.company.networkmovers.modules.property.service.ParkingAccessTypeService;
import com.company.networkmovers.shared.controller.AbstractMobileLookupController;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mobile/parking-access-type")
public class MobileParkingAccessTypeController extends AbstractMobileLookupController<LookupRequest, LookupResponse> {

    public MobileParkingAccessTypeController(ParkingAccessTypeService service) {
        super(service);
    }
}
