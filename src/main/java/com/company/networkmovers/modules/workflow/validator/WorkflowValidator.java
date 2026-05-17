package com.company.networkmovers.modules.workflow.validator;

import com.company.networkmovers.modules.workflow.dto.request.WorkflowRequest;
import org.springframework.stereotype.Component;

@Component
public class WorkflowValidator {

    public void validate(WorkflowRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
