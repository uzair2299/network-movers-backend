package com.company.networkmovers.modules.admin.validator;

import com.company.networkmovers.modules.admin.dto.request.AdminRequest;
import org.springframework.stereotype.Component;

@Component
public class AdminValidator {

    public void validate(AdminRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
