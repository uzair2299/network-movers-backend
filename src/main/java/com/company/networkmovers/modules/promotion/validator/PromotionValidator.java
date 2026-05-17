package com.company.networkmovers.modules.promotion.validator;

import com.company.networkmovers.modules.promotion.dto.request.PromotionRequest;
import org.springframework.stereotype.Component;

@Component
public class PromotionValidator {

    public void validate(PromotionRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
