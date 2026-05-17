package com.company.networkmovers.modules.identity.validator;

import com.company.networkmovers.modules.identity.dto.request.IdentityRequest;
import org.springframework.stereotype.Component;

@Component
public class IdentityValidator {

    public void validate(IdentityRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
