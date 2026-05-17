package com.company.networkmovers.modules.audit.validator;

import com.company.networkmovers.modules.audit.dto.request.AuditRequest;
import org.springframework.stereotype.Component;

@Component
public class AuditValidator {

    public void validate(AuditRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
