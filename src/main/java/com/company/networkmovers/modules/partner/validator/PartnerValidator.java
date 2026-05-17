package com.company.networkmovers.modules.partner.validator;

import com.company.networkmovers.modules.partner.dto.request.PartnerRequest;
import org.springframework.stereotype.Component;

@Component
public class PartnerValidator {

    public void validate(PartnerRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
