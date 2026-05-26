package com.company.networkmovers.shared.controller;

import com.company.networkmovers.shared.service.GenericLookupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public abstract class AbstractPublicLookupController<REQ, RES> {

    protected final GenericLookupService<REQ, RES> service;

    protected AbstractPublicLookupController(GenericLookupService<REQ, RES> service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get active record by ID for public client", description = "Retrieves details of a specific active lookup record by UUID. Access is public and does not require authentication.")
    public ResponseEntity<RES> getById(
            @Parameter(description = "UUID of the active record", required = true) @PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "List all active records for public client", description = "Retrieves all currently active lookup records. Access is public and does not require authentication.")
    public ResponseEntity<List<RES>> getAllActive() {
        return ResponseEntity.ok(service.getAllActive());
    }
}
