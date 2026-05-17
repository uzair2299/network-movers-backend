package com.company.networkmovers.modules.insurance.validator;

import com.company.networkmovers.modules.insurance.dto.request.InsuranceRequest;
import org.springframework.stereotype.Component;

@Component
public class InsuranceValidator {

    public void validate(InsuranceRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
