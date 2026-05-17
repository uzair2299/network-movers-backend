package com.company.networkmovers.modules.approval.validator;

import com.company.networkmovers.modules.approval.dto.request.ApprovalRequest;
import org.springframework.stereotype.Component;

@Component
public class ApprovalValidator {

    public void validate(ApprovalRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
