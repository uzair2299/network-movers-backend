package com.company.networkmovers.modules.geofence.validator;

import com.company.networkmovers.modules.geofence.dto.request.GeofenceRequest;
import org.springframework.stereotype.Component;

@Component
public class GeofenceValidator {

    public void validate(GeofenceRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
