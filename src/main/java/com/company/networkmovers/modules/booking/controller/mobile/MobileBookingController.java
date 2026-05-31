package com.company.networkmovers.modules.booking.controller.mobile;

import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import com.company.networkmovers.modules.booking.service.BookingService;
import com.company.networkmovers.integration.thirdparty.n8n.N8nWebhookClient;
import com.company.networkmovers.security.context.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    @Operation(summary = "Pageable search of user bookings", description = "Query, filter, paginate, and sort bookings belonging to the currently authenticated user.")
    public ResponseEntity<org.springframework.data.domain.Page<BookingResponse>> getAllForCurrentUser(
            @org.springdoc.core.annotations.ParameterObject com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.getAllByUserId(userId, requestParams));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get booking by ID", description = "Retrieves details of a specific booking belonging to the currently authenticated user.")
    public ResponseEntity<BookingResponse> findByIdForCurrentUser(
            @Parameter(description = "ID of the booking", required = true) @PathVariable Long id) {
        Long userId = getCurrentUserId();
        return ResponseEntity.ok(service.findByIdAndUserId(id, userId));
    }

    @PutMapping("/{id}/status")
    @Operation(summary = "Update booking status", description = "Updates the status of a booking belonging to the current user (e.g. Cancelled) and logs it to the history timeline.")
    public ResponseEntity<BookingResponse> updateStatusForCurrentUser(
            @Parameter(description = "ID of the booking", required = true) @PathVariable Long id,
            @RequestBody com.company.networkmovers.modules.booking.dto.request.UpdateBookingStatusRequest request) {
        Long userId = getCurrentUserId();
        // Verify ownership
        service.findByIdAndUserId(id, userId);
        return ResponseEntity.ok(service.updateStatus(id, request));
    }

    @GetMapping("/{id}/timeline")
    @Operation(summary = "Get booking timeline", description = "Retrieves the full lifecycle history of a specific booking belonging to the current user.")
    public ResponseEntity<List<com.company.networkmovers.modules.booking.dto.response.BookingHistoryResponse>> getBookingTimelineForCurrentUser(
            @Parameter(description = "ID of the booking", required = true) @PathVariable Long id) {
        Long userId = getCurrentUserId();
        // Verify ownership
        service.findByIdAndUserId(id, userId);
        return ResponseEntity.ok(service.getBookingTimeline(id));
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof CustomUserDetails) {
            return ((CustomUserDetails) authentication.getPrincipal()).getId();
        }
        throw new RuntimeException("User not authenticated");
    }
}
