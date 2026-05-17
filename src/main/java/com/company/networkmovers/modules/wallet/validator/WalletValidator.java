package com.company.networkmovers.modules.wallet.validator;

import com.company.networkmovers.modules.wallet.dto.request.WalletRequest;
import org.springframework.stereotype.Component;

@Component
public class WalletValidator {

    public void validate(WalletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
