package com.company.networkmovers.modules.leave.validator;

import com.company.networkmovers.modules.leave.dto.request.LeaveRequest;
import org.springframework.stereotype.Component;

@Component
public class LeaveValidator {

    public void validate(LeaveRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
