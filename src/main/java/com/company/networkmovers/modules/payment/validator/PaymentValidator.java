package com.company.networkmovers.modules.payment.validator;

import com.company.networkmovers.modules.payment.dto.request.PaymentRequest;
import org.springframework.stereotype.Component;

@Component
public class PaymentValidator {

    public void validate(PaymentRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
