package com.company.networkmovers.modules.scheduling.validator;

import com.company.networkmovers.modules.scheduling.dto.request.SchedulingRequest;
import org.springframework.stereotype.Component;

@Component
public class SchedulingValidator {

    public void validate(SchedulingRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
