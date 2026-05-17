package com.company.networkmovers.modules.estimate.validator;

import com.company.networkmovers.modules.estimate.dto.request.EstimateRequest;
import org.springframework.stereotype.Component;

@Component
public class EstimateValidator {

    public void validate(EstimateRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
