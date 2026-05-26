package com.company.networkmovers.modules.property.controller.mobile;

import com.company.networkmovers.modules.property.service.AccessRestrictionTypeService;
import com.company.networkmovers.shared.controller.AbstractMobileLookupController;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mobile/access-restriction-type")
public class MobileAccessRestrictionTypeController extends AbstractMobileLookupController<LookupRequest, LookupResponse> {

    public MobileAccessRestrictionTypeController(AccessRestrictionTypeService service) {
        super(service);
    }
}
