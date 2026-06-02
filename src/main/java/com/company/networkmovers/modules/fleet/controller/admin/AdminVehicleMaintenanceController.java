package com.company.networkmovers.modules.fleet.controller.admin;

import com.company.networkmovers.modules.fleet.dto.request.VehicleMaintenanceRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleMaintenanceResponse;
import com.company.networkmovers.modules.fleet.service.VehicleMaintenanceService;
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
@RequestMapping("/api/v1/admin/fleet/vehicle-maintenance")
@Tag(name = "Admin Vehicle Maintenance", description = "Admin API for managing Vehicle Maintenance records")
@RequiredArgsConstructor
public class AdminVehicleMaintenanceController {

    private final VehicleMaintenanceService service;

    @PostMapping
    @Operation(summary = "Create a new Vehicle Maintenance Record")
    public ResponseEntity<VehicleMaintenanceResponse> create(@RequestBody VehicleMaintenanceRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Vehicle Maintenance Record")
    public ResponseEntity<VehicleMaintenanceResponse> update(@PathVariable UUID id, @RequestBody VehicleMaintenanceRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Vehicle Maintenance Record by ID")
    public ResponseEntity<VehicleMaintenanceResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/vehicle/{vehicleId}")
    @Operation(summary = "Get all maintenance records for a specific vehicle")
    public ResponseEntity<Page<VehicleMaintenanceResponse>> getByVehicleId(
            @PathVariable UUID vehicleId,
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(service.getByVehicleId(vehicleId, pageable));
    }

    @GetMapping("/upcoming")
    @Operation(summary = "Get all upcoming maintenance due in next N days or reached KM")
    public ResponseEntity<Page<VehicleMaintenanceResponse>> getUpcomingMaintenance(
            @RequestParam(defaultValue = "30") int daysAhead,
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(service.getUpcomingMaintenance(daysAhead, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Vehicle Maintenance Record")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
