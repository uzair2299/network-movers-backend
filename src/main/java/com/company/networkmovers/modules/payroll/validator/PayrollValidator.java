package com.company.networkmovers.modules.payroll.validator;

import com.company.networkmovers.modules.payroll.dto.request.PayrollRequest;
import org.springframework.stereotype.Component;

@Component
public class PayrollValidator {

    public void validate(PayrollRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
