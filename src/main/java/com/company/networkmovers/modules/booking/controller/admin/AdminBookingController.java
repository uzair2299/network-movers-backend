package com.company.networkmovers.modules.booking.controller.admin;

import com.company.networkmovers.modules.booking.dto.request.BookingRequest;
import com.company.networkmovers.modules.booking.dto.response.BookingResponse;
import com.company.networkmovers.modules.booking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/booking")
@Tag(name = "Admin Bookings", description = "Endpoints for handling bookings from the admin application")
public class AdminBookingController {

    private final BookingService service;

    public AdminBookingController(BookingService service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create master data record", description = "Creates a new master data lookup entry. Requires administrative privileges.")
    public ResponseEntity<BookingResponse> create(@RequestBody BookingRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get master data record by ID", description = "Retrieves details of a specific master data record by its ID. Access restricted to admin users.")
    public ResponseEntity<BookingResponse> findById(
            @Parameter(description = "ID of the record", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @GetMapping("/active")
    @Operation(summary = "Pageable search of active records", description = "Retrieves paginated active master data lookup records for administrative review.")
    public ResponseEntity<org.springframework.data.domain.Page<BookingResponse>> getAllActive(
            @org.springdoc.core.annotations.ParameterObject com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAllActive(requestParams));
    }

    @GetMapping
    @Operation(summary = "Pageable search of records", description = "Query, filter, paginate, and sort lookup records with dynamic search.")
    public ResponseEntity<org.springframework.data.domain.Page<BookingResponse>> getAll(
            @org.springdoc.core.annotations.ParameterObject com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update master data record", description = "Updates an existing master data entry by its ID. Requires administrative privileges.")
    public ResponseEntity<BookingResponse> update(
            @Parameter(description = "ID of the record to update", required = true) @PathVariable Long id, 
            @RequestBody BookingRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Soft delete master data record", description = "Soft deletes (deactivates) a master data record by its ID. Requires administrative privileges.")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the record to delete", required = true) @PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
