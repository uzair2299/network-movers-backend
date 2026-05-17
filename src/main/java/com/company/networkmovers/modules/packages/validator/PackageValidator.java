package com.company.networkmovers.modules.packages.validator;

import com.company.networkmovers.modules.packages.dto.request.PackageRequest;
import org.springframework.stereotype.Component;

@Component
public class PackageValidator {

    public void validate(PackageRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
