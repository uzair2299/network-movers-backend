package com.company.networkmovers.modules.coupon.validator;

import com.company.networkmovers.modules.coupon.dto.request.CouponRequest;
import org.springframework.stereotype.Component;

@Component
public class CouponValidator {

    public void validate(CouponRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
