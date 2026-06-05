package com.company.networkmovers.modules.booking.controller.open;

import java.util.UUID; // Pluralized subpackage to prevent name conflicts

import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import com.company.networkmovers.modules.booking.service.BookingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/booking")
public class PublicBookingController {

    private final BookingService service;

    public PublicBookingController(BookingService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookingResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<org.springframework.data.domain.Page<BookingResponse>> getAllActive(
            @org.springdoc.core.annotations.ParameterObject com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAllActive(requestParams));
    }
}

