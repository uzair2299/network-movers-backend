package com.company.networkmovers.modules.dispatcher.validator;

import com.company.networkmovers.modules.dispatcher.dto.request.DispatcherRequest;
import org.springframework.stereotype.Component;

@Component
public class DispatcherValidator {

    public void validate(DispatcherRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
