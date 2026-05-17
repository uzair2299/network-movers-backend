package com.company.networkmovers.modules.fleet.validator;

import com.company.networkmovers.modules.fleet.dto.request.FleetRequest;
import org.springframework.stereotype.Component;

@Component
public class FleetValidator {

    public void validate(FleetRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
