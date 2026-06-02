package com.company.networkmovers.modules.fleet.controller.admin;

import com.company.networkmovers.modules.fleet.dto.request.VehicleDocumentRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleDocumentResponse;
import com.company.networkmovers.modules.fleet.service.VehicleDocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/fleet/vehicle-documents")
@Tag(name = "Admin Vehicle Documents", description = "Admin API for managing Vehicle Documents")
@RequiredArgsConstructor
public class AdminVehicleDocumentController {

    private final VehicleDocumentService service;

    @PostMapping
    @Operation(summary = "Create a new Vehicle Document")
    public ResponseEntity<VehicleDocumentResponse> create(@RequestBody VehicleDocumentRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Vehicle Document")
    public ResponseEntity<VehicleDocumentResponse> update(@PathVariable UUID id, @RequestBody VehicleDocumentRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Vehicle Document by ID")
    public ResponseEntity<VehicleDocumentResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/vehicle/{vehicleId}")
    @Operation(summary = "Get all documents for a specific vehicle")
    public ResponseEntity<Page<VehicleDocumentResponse>> getByVehicleId(
            @PathVariable UUID vehicleId,
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(service.getByVehicleId(vehicleId, pageable));
    }

    @GetMapping("/expiring")
    @Operation(summary = "Get all vehicle documents expiring in the next N days")
    public ResponseEntity<Page<VehicleDocumentResponse>> getExpiringDocuments(
            @RequestParam(defaultValue = "30") int daysAhead,
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(service.getExpiringDocuments(daysAhead, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Vehicle Document")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
