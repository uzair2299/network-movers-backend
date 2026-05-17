package com.company.networkmovers.modules.fraud.validator;

import com.company.networkmovers.modules.fraud.dto.request.FraudRequest;
import org.springframework.stereotype.Component;

@Component
public class FraudValidator {

    public void validate(FraudRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
