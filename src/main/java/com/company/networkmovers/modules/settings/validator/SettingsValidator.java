package com.company.networkmovers.modules.settings.validator;

import com.company.networkmovers.modules.settings.dto.request.SettingsRequest;
import org.springframework.stereotype.Component;

@Component
public class SettingsValidator {

    public void validate(SettingsRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
