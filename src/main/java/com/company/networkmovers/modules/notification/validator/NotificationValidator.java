package com.company.networkmovers.modules.notification.validator;

import com.company.networkmovers.modules.notification.dto.request.NotificationRequest;
import org.springframework.stereotype.Component;

@Component
public class NotificationValidator {

    public void validate(NotificationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
