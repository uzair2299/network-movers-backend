package com.company.networkmovers.modules.review.validator;

import com.company.networkmovers.modules.review.dto.request.ReviewRequest;
import org.springframework.stereotype.Component;

@Component
public class ReviewValidator {

    public void validate(ReviewRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
