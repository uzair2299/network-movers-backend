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

    @org.springframework.web.bind.annotation.GetMapping("/category/{categoryId}")
    @io.swagger.v3.oas.annotations.Operation(summary = "Get active property types by category", description = "Retrieves a list of active property types belonging to a specific category.")
    public org.springframework.http.ResponseEntity<java.util.List<PropertyTypeResponse>> getActiveByCategoryId(
            @io.swagger.v3.oas.annotations.Parameter(description = "UUID of the category", required = true) @org.springframework.web.bind.annotation.PathVariable java.util.UUID categoryId) {
        return org.springframework.http.ResponseEntity.ok(((PropertyTypeService) service).getActiveByCategoryId(categoryId));
    }
}
