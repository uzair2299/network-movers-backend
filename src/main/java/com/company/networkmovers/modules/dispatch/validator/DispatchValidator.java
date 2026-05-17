package com.company.networkmovers.modules.dispatch.validator;

import com.company.networkmovers.modules.dispatch.dto.request.DispatchRequest;
import org.springframework.stereotype.Component;

@Component
public class DispatchValidator {

    public void validate(DispatchRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
