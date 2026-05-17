package com.company.networkmovers.modules.lookup.validator;

import com.company.networkmovers.modules.lookup.dto.request.LookupRequest;
import org.springframework.stereotype.Component;

@Component
public class LookupValidator {

    public void validate(LookupRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
