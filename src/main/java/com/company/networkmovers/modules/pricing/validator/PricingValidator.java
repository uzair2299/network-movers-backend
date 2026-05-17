package com.company.networkmovers.modules.pricing.validator;

import com.company.networkmovers.modules.pricing.dto.request.PricingRequest;
import org.springframework.stereotype.Component;

@Component
public class PricingValidator {

    public void validate(PricingRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
