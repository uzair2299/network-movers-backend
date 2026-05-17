package com.company.networkmovers.modules.dashboard.validator;

import com.company.networkmovers.modules.dashboard.dto.request.DashboardRequest;
import org.springframework.stereotype.Component;

@Component
public class DashboardValidator {

    public void validate(DashboardRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
