package com.company.networkmovers.modules.property.controller.admin;

import com.company.networkmovers.modules.property.service.AccessRestrictionTypeService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/access-restriction-type")
public class AdminAccessRestrictionTypeController extends AbstractLookupController<LookupRequest, LookupResponse> {

    public AdminAccessRestrictionTypeController(AccessRestrictionTypeService service) {
        super(service);
    }
}
