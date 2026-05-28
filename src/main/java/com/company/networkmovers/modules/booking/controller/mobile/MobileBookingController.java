package com.company.networkmovers.modules.booking.controller.mobile;

import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import com.company.networkmovers.modules.booking.service.BookingService;
import com.company.networkmovers.integration.thirdparty.n8n.N8nWebhookClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mobile/booking")
@Tag(name = "Mobile Booking", description = "Endpoints for handling bookings from the mobile application")
public class MobileBookingController {

    private final BookingService service;
    private final N8nWebhookClient n8nWebhookClient;

    public MobileBookingController(BookingService service, N8nWebhookClient n8nWebhookClient) {
        this.service = service;
        this.n8nWebhookClient = n8nWebhookClient;
    }

    @PostMapping
    @Operation(summary = "Create a booking for mobile app", description = "Creates a new booking, triggers the n8n webhook notification, and returns the saved booking details.")
    public ResponseEntity<BookingResponse> create(@RequestBody BookingRequest request) {
        BookingResponse response = service.create(request);
        n8nWebhookClient.triggerWebhook();
        return ResponseEntity.ok(response);
    }
}
