package com.company.networkmovers.modules.communication.validator;

import com.company.networkmovers.modules.communication.dto.request.CommunicationRequest;
import org.springframework.stereotype.Component;

@Component
public class CommunicationValidator {

    public void validate(CommunicationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
