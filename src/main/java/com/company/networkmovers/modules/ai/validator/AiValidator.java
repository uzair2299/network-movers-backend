package com.company.networkmovers.modules.ai.validator;

import com.company.networkmovers.modules.ai.dto.request.AiRequest;
import org.springframework.stereotype.Component;

@Component
public class AiValidator {

    public void validate(AiRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
