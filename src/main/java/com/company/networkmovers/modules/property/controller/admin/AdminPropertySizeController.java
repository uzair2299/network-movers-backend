package com.company.networkmovers.modules.property.controller.admin;

import com.company.networkmovers.modules.property.dto.request.PropertySizeRequest;
import com.company.networkmovers.modules.property.dto.response.PropertySizeResponse;
import com.company.networkmovers.modules.property.service.PropertySizeService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/property-size")
public class AdminPropertySizeController extends AbstractLookupController<PropertySizeRequest, PropertySizeResponse> {

    public AdminPropertySizeController(PropertySizeService service) {
        super(service);
    }
}
