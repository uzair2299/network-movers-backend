package com.company.networkmovers.modules.taxation.validator;

import com.company.networkmovers.modules.taxation.dto.request.TaxationRequest;
import org.springframework.stereotype.Component;

@Component
public class TaxationValidator {

    public void validate(TaxationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
