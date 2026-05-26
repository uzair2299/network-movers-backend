package com.company.networkmovers.modules.property.controller.mobile;

import com.company.networkmovers.modules.property.dto.request.PropertyTypeRequest;
import com.company.networkmovers.modules.property.dto.response.PropertyTypeResponse;
import com.company.networkmovers.modules.property.service.PropertyTypeService;
import com.company.networkmovers.shared.controller.AbstractMobileLookupController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/mobile/property-type")
public class MobilePropertyTypeController extends AbstractMobileLookupController<PropertyTypeRequest, PropertyTypeResponse> {

    public MobilePropertyTypeController(PropertyTypeService service) {
        super(service);
    }
}
