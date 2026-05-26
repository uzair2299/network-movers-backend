package com.company.networkmovers.modules.property.controller.mobile;

import com.company.networkmovers.modules.property.dto.request.PropertySizeRequest;
import com.company.networkmovers.modules.property.dto.response.PropertySizeResponse;
import com.company.networkmovers.modules.property.service.PropertySizeService;
import com.company.networkmovers.shared.controller.AbstractMobileLookupController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mobile/property-size")
public class MobilePropertySizeController extends AbstractMobileLookupController<PropertySizeRequest, PropertySizeResponse> {

    public MobilePropertySizeController(PropertySizeService service) {
        super(service);
    }
}
