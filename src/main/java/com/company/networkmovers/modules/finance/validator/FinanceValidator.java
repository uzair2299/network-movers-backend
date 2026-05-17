package com.company.networkmovers.modules.finance.validator;

import com.company.networkmovers.modules.finance.dto.request.FinanceRequest;
import org.springframework.stereotype.Component;

@Component
public class FinanceValidator {

    public void validate(FinanceRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
