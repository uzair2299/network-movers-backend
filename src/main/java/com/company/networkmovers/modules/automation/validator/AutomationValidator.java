package com.company.networkmovers.modules.automation.validator;

import com.company.networkmovers.modules.automation.dto.request.AutomationRequest;
import org.springframework.stereotype.Component;

@Component
public class AutomationValidator {

    public void validate(AutomationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
