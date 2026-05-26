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
    @Operation(summary = "List all active records for public client with pagination", description = "Query, filter, paginate, and sort active lookup records anonymously.")
    public ResponseEntity<org.springframework.data.domain.Page<RES>> getAll(
            @org.springdoc.core.annotations.ParameterObject com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }
}
