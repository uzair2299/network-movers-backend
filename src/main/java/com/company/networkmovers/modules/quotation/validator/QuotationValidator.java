package com.company.networkmovers.modules.quotation.validator;

import com.company.networkmovers.modules.quotation.dto.request.QuotationRequest;
import org.springframework.stereotype.Component;

@Component
public class QuotationValidator {

    public void validate(QuotationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
