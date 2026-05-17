package com.company.networkmovers.modules.location.validator;

import com.company.networkmovers.modules.location.dto.request.LocationRequest;
import org.springframework.stereotype.Component;

@Component
public class LocationValidator {

    public void validate(LocationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
