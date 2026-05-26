package com.company.networkmovers.shared.controller;

import com.company.networkmovers.shared.service.GenericLookupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public abstract class AbstractLookupController<REQ, RES> {

    protected final GenericLookupService<REQ, RES> service;

    protected AbstractLookupController(GenericLookupService<REQ, RES> service) {
        this.service = service;
    }

    @PostMapping
    @Operation(summary = "Create master data record", description = "Creates a new master data lookup entry. Requires administrative privileges.")
    public ResponseEntity<RES> create(@RequestBody REQ request) {
        return ResponseEntity.ok(service.create(request));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update master data record", description = "Updates an existing master data entry by its UUID. Requires administrative privileges.")
    public ResponseEntity<RES> update(
            @Parameter(description = "UUID of the record to update", required = true) @PathVariable UUID id,
            @RequestBody REQ request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get master data record by ID", description = "Retrieves details of a specific master data record by its UUID. Access restricted to admin users.")
    public ResponseEntity<RES> getById(
            @Parameter(description = "UUID of the record", required = true) @PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/active")
    @Operation(summary = "List all active records", description = "Retrieves all currently active master data lookup records for administrative review.")
    public ResponseEntity<List<RES>> getAllActive() {
        return ResponseEntity.ok(service.getAllActive());
    }

    @GetMapping
    @Operation(summary = "Pageable search of records", description = "Query, filter, paginate, and sort lookup records with dynamic search.")
    public ResponseEntity<org.springframework.data.domain.Page<RES>> getAll(
            @org.springdoc.core.annotations.ParameterObject com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        return ResponseEntity.ok(service.getAll(requestParams));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Soft delete master data record", description = "Soft deletes (deactivates) a master data record by its UUID. Requires administrative privileges.")
    public ResponseEntity<Void> delete(
            @Parameter(description = "UUID of the record to delete", required = true) @PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
