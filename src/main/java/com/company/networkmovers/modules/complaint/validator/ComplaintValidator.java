package com.company.networkmovers.modules.complaint.validator;

import com.company.networkmovers.modules.complaint.dto.request.ComplaintRequest;
import org.springframework.stereotype.Component;

@Component
public class ComplaintValidator {

    public void validate(ComplaintRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
