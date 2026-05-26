package com.company.networkmovers.modules.property.controller.open;

import com.company.networkmovers.modules.property.service.PropertyCategoryService;
import com.company.networkmovers.shared.controller.AbstractPublicLookupController;
import com.company.networkmovers.shared.dto.LookupRequest;
import com.company.networkmovers.shared.dto.LookupResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/property-category")
public class PublicPropertyCategoryController extends AbstractPublicLookupController<LookupRequest, LookupResponse> {

    public PublicPropertyCategoryController(PropertyCategoryService service) {
        super(service);
    }
}
