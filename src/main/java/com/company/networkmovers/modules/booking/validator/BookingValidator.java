package com.company.networkmovers.modules.booking.validator;

import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import org.springframework.stereotype.Component;

@Component
public class BookingValidator {

    public void validate(BookingRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request cannot be null");
        }
        if (request.getRouteDetails() == null) {
            throw new IllegalArgumentException("Route details cannot be null");
        }
        if (request.getScheduling() == null) {
            throw new IllegalArgumentException("Scheduling details cannot be null");
        }
        if (request.getMoveSpecifications() == null) {
            throw new IllegalArgumentException("Move specifications cannot be null");
        }
        if (request.getAccessDetails() == null) {
            throw new IllegalArgumentException("Access details cannot be null");
        }
    }
}
