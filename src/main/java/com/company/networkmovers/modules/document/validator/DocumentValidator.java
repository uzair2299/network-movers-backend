package com.company.networkmovers.modules.document.validator;

import com.company.networkmovers.modules.document.dto.request.DocumentRequest;
import org.springframework.stereotype.Component;

@Component
public class DocumentValidator {

    public void validate(DocumentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
