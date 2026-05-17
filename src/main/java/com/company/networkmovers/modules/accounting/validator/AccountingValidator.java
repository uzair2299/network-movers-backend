package com.company.networkmovers.modules.accounting.validator;

import com.company.networkmovers.modules.accounting.dto.request.AccountingRequest;
import org.springframework.stereotype.Component;

@Component
public class AccountingValidator {

    public void validate(AccountingRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
