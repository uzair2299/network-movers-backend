package com.company.networkmovers.modules.property.controller.open;

import com.company.networkmovers.modules.property.dto.request.PropertyTypeRequest;
import com.company.networkmovers.modules.property.dto.response.PropertyTypeResponse;
import com.company.networkmovers.modules.property.service.PropertyTypeService;
import com.company.networkmovers.shared.controller.AbstractPublicLookupController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/property-type")
public class PublicPropertyTypeController extends AbstractPublicLookupController<PropertyTypeRequest, PropertyTypeResponse> {

    public PublicPropertyTypeController(PropertyTypeService service) {
        super(service);
    }
}
