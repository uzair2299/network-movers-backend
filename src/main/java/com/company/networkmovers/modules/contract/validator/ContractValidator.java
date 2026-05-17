package com.company.networkmovers.modules.contract.validator;

import com.company.networkmovers.modules.contract.dto.request.ContractRequest;
import org.springframework.stereotype.Component;

@Component
public class ContractValidator {

    public void validate(ContractRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
