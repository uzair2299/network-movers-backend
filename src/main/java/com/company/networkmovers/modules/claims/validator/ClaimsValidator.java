package com.company.networkmovers.modules.claims.validator;

import com.company.networkmovers.modules.claims.dto.request.ClaimsRequest;
import org.springframework.stereotype.Component;

@Component
public class ClaimsValidator {

    public void validate(ClaimsRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
