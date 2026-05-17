package com.company.networkmovers.modules.maps.validator;

import com.company.networkmovers.modules.maps.dto.request.MapsRequest;
import org.springframework.stereotype.Component;

@Component
public class MapsValidator {

    public void validate(MapsRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
