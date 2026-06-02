package com.company.networkmovers.modules.fleet.controller.admin;

import com.company.networkmovers.modules.fleet.dto.request.VehicleRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleResponse;
import com.company.networkmovers.modules.fleet.entity.enums.VehicleStatus;
import com.company.networkmovers.modules.fleet.service.VehicleService;
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

@RestController("fleetAdminVehicleController")
@RequestMapping("/api/v1/admin/fleet/vehicles")
@Tag(name = "Admin Vehicles", description = "Admin API for managing Vehicles")
@RequiredArgsConstructor
public class AdminVehicleController {

    private final VehicleService service;

    @PostMapping
    @Operation(summary = "Create a new Vehicle")
    public ResponseEntity<VehicleResponse> create(@RequestBody VehicleRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Vehicle")
    public ResponseEntity<VehicleResponse> update(@PathVariable UUID id, @RequestBody VehicleRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @PatchMapping("/{id}/status")
    @Operation(summary = "Update Vehicle Status")
    public ResponseEntity<VehicleResponse> updateStatus(@PathVariable UUID id, @RequestParam VehicleStatus status) {
        return ResponseEntity.ok(service.updateStatus(id, status));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Vehicle by ID")
    public ResponseEntity<VehicleResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search Vehicles with pagination")
    public ResponseEntity<Page<VehicleResponse>> search(
            @Parameter(description = "Search query for registration, code, or model name") @RequestParam(required = false) String query,
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(service.search(query, pageable));
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get Vehicles by Status")
    public ResponseEntity<Page<VehicleResponse>> getByStatus(
            @PathVariable VehicleStatus status,
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(service.searchByStatus(status, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Vehicle")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
