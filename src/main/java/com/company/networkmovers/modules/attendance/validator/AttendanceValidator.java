package com.company.networkmovers.modules.attendance.validator;

import com.company.networkmovers.modules.attendance.dto.request.AttendanceRequest;
import org.springframework.stereotype.Component;

@Component
public class AttendanceValidator {

    public void validate(AttendanceRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
