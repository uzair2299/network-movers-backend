package com.company.networkmovers.modules.route.validator;

import com.company.networkmovers.modules.route.dto.request.RouteRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {

    public void validate(RouteRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
