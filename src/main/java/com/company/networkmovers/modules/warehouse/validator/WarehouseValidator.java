package com.company.networkmovers.modules.warehouse.validator;

import com.company.networkmovers.modules.warehouse.dto.request.WarehouseRequest;
import org.springframework.stereotype.Component;

@Component
public class WarehouseValidator {

    public void validate(WarehouseRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
