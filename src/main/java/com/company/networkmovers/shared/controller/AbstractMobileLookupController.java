package com.company.networkmovers.shared.controller;

import com.company.networkmovers.shared.service.GenericLookupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public abstract class AbstractMobileLookupController<REQ, RES> {

    protected final GenericLookupService<REQ, RES> service;

    protected AbstractMobileLookupController(GenericLookupService<REQ, RES> service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get active record by ID for mobile app", description = "Retrieves details of an active lookup record by UUID. Access requires a valid mobile user JWT session.")
    public ResponseEntity<RES> getById(
            @Parameter(description = "UUID of the active record", required = true) @PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "List all active records for mobile app with pagination", description = "Query, filter, paginate, and sort active lookup records. Access requires a valid mobile user JWT session.")
    public ResponseEntity<org.springframework.data.domain.Page<RES>> getAll(com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }
}
