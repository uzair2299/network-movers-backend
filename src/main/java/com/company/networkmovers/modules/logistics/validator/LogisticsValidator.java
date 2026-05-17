package com.company.networkmovers.modules.logistics.validator;

import com.company.networkmovers.modules.logistics.dto.request.LogisticsRequest;
import org.springframework.stereotype.Component;

@Component
public class LogisticsValidator {

    public void validate(LogisticsRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
