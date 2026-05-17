package com.company.networkmovers.modules.trip.validator;

import com.company.networkmovers.modules.trip.dto.request.TripRequest;
import org.springframework.stereotype.Component;

@Component
public class TripValidator {

    public void validate(TripRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
