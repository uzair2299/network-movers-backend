package com.company.networkmovers.modules.movingitem.validator;

import com.company.networkmovers.modules.movingitem.dto.request.MovingitemRequest;
import org.springframework.stereotype.Component;

@Component
public class MovingitemValidator {

    public void validate(MovingitemRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
