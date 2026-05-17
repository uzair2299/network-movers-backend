package com.company.networkmovers.modules.vendor.validator;

import com.company.networkmovers.modules.vendor.dto.request.VendorRequest;
import org.springframework.stereotype.Component;

@Component
public class VendorValidator {

    public void validate(VendorRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
