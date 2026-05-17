package com.company.networkmovers.modules.truck.validator;

import com.company.networkmovers.modules.truck.dto.request.TruckRequest;
import org.springframework.stereotype.Component;

@Component
public class TruckValidator {

    public void validate(TruckRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
