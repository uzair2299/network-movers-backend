package com.company.networkmovers.modules.mover.validator;

import com.company.networkmovers.modules.mover.dto.request.MoverRequest;
import org.springframework.stereotype.Component;

@Component
public class MoverValidator {

    public void validate(MoverRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
