package com.company.networkmovers.modules.optimization.validator;

import com.company.networkmovers.modules.optimization.dto.request.OptimizationRequest;
import org.springframework.stereotype.Component;

@Component
public class OptimizationValidator {

    public void validate(OptimizationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
