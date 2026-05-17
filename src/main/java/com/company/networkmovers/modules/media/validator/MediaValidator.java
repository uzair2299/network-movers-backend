package com.company.networkmovers.modules.media.validator;

import com.company.networkmovers.modules.media.dto.request.MediaRequest;
import org.springframework.stereotype.Component;

@Component
public class MediaValidator {

    public void validate(MediaRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
