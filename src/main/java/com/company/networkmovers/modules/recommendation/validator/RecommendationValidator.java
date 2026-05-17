package com.company.networkmovers.modules.recommendation.validator;

import com.company.networkmovers.modules.recommendation.dto.request.RecommendationRequest;
import org.springframework.stereotype.Component;

@Component
public class RecommendationValidator {

    public void validate(RecommendationRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
