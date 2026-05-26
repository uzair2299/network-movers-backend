package com.company.networkmovers.modules.property.controller.open;

import com.company.networkmovers.modules.property.service.AccessRestrictionTypeService;
import com.company.networkmovers.shared.controller.AbstractPublicLookupController;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/access-restriction-type")
public class PublicAccessRestrictionTypeController extends AbstractPublicLookupController<LookupRequest, LookupResponse> {

    public PublicAccessRestrictionTypeController(AccessRestrictionTypeService service) {
        super(service);
    }
}
