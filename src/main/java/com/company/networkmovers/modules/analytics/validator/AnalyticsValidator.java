package com.company.networkmovers.modules.analytics.validator;

import com.company.networkmovers.modules.analytics.dto.request.AnalyticsRequest;
import org.springframework.stereotype.Component;

@Component
public class AnalyticsValidator {

    public void validate(AnalyticsRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
