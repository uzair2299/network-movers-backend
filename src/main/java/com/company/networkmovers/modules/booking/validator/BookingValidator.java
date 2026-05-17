package com.company.networkmovers.modules.booking.validator;

import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import org.springframework.stereotype.Component;

@Component
public class BookingValidator {

    public void validate(BookingRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getName() == null || request.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }
}
