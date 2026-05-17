package com.company.networkmovers.modules.vehicle.validator;

import com.company.networkmovers.modules.vehicle.dto.request.VehicleRequest;
import org.springframework.stereotype.Component;

@Component
public class VehicleValidator {

    public void validate(VehicleRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
