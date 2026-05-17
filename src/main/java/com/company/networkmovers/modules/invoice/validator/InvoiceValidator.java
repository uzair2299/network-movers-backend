package com.company.networkmovers.modules.invoice.validator;

import com.company.networkmovers.modules.invoice.dto.request.InvoiceRequest;
import org.springframework.stereotype.Component;

@Component
public class InvoiceValidator {

    public void validate(InvoiceRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
