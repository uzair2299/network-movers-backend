package com.company.networkmovers.modules.property.controller.open;

import com.company.networkmovers.modules.property.dto.request.PropertySizeRequest;
import com.company.networkmovers.modules.property.dto.response.PropertySizeResponse;
import com.company.networkmovers.modules.property.service.PropertySizeService;
import com.company.networkmovers.shared.controller.AbstractPublicLookupController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/property-size")
public class PublicPropertySizeController extends AbstractPublicLookupController<PropertySizeRequest, PropertySizeResponse> {

    public PublicPropertySizeController(PropertySizeService service) {
        super(service);
    }
}
