package com.company.networkmovers.modules.tracking.validator;

import com.company.networkmovers.modules.tracking.dto.request.TrackingRequest;
import org.springframework.stereotype.Component;

@Component
public class TrackingValidator {

    public void validate(TrackingRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
