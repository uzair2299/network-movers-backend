package com.company.networkmovers.modules.realtime.validator;

import com.company.networkmovers.modules.realtime.dto.request.RealtimeRequest;
import org.springframework.stereotype.Component;

@Component
public class RealtimeValidator {

    public void validate(RealtimeRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
