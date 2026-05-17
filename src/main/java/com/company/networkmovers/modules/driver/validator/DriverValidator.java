package com.company.networkmovers.modules.driver.validator;

import com.company.networkmovers.modules.driver.dto.request.DriverRequest;
import org.springframework.stereotype.Component;

@Component
public class DriverValidator {

    public void validate(DriverRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
