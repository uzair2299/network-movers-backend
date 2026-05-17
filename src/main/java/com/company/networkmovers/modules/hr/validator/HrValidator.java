package com.company.networkmovers.modules.hr.validator;

import com.company.networkmovers.modules.hr.dto.request.HrRequest;
import org.springframework.stereotype.Component;

@Component
public class HrValidator {

    public void validate(HrRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
