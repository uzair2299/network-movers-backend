package com.company.networkmovers.modules.inventory.validator;

import com.company.networkmovers.modules.inventory.dto.request.InventoryRequest;
import org.springframework.stereotype.Component;

@Component
public class InventoryValidator {

    public void validate(InventoryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
