package com.company.networkmovers.modules.property.controller.admin;

import com.company.networkmovers.modules.property.dto.request.PropertyTypeRequest;
import com.company.networkmovers.modules.property.dto.response.PropertyTypeResponse;
import com.company.networkmovers.modules.property.service.PropertyTypeService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/property-type")
public class AdminPropertyTypeController extends AbstractLookupController<PropertyTypeRequest, PropertyTypeResponse> {

    public AdminPropertyTypeController(PropertyTypeService service) {
        super(service);
    }
}
