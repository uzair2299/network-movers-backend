package com.company.networkmovers.modules.support.validator;

import com.company.networkmovers.modules.support.dto.request.SupportRequest;
import org.springframework.stereotype.Component;

@Component
public class SupportValidator {

    public void validate(SupportRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
