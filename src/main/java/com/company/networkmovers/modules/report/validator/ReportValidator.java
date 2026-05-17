package com.company.networkmovers.modules.report.validator;

import com.company.networkmovers.modules.report.dto.request.ReportRequest;
import org.springframework.stereotype.Component;

@Component
public class ReportValidator {

    public void validate(ReportRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
