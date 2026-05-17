package com.company.networkmovers.modules.configuration.validator;

import com.company.networkmovers.modules.configuration.dto.request.ConfigurationRequest;
import org.springframework.stereotype.Component;

@Component
public class ConfigurationValidator {

    public void validate(ConfigurationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
