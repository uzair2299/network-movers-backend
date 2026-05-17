package com.company.networkmovers.modules.customer.validator;

import com.company.networkmovers.modules.customer.dto.request.CustomerRequest;
import org.springframework.stereotype.Component;

@Component
public class CustomerValidator {

    public void validate(CustomerRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
