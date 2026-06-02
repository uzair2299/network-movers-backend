package com.company.networkmovers.modules.fleet.controller.admin;

import com.company.networkmovers.modules.fleet.dto.request.VehicleFuelLogRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleFuelLogResponse;
import com.company.networkmovers.modules.fleet.service.VehicleFuelLogService;
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
@RequestMapping("/api/v1/admin/fleet/vehicle-fuel-logs")
@Tag(name = "Admin Vehicle Fuel Logs", description = "Admin API for managing Vehicle Fuel Logs")
@RequiredArgsConstructor
public class AdminVehicleFuelLogController {

    private final VehicleFuelLogService service;

    @PostMapping
    @Operation(summary = "Create a new Vehicle Fuel Log")
    public ResponseEntity<VehicleFuelLogResponse> create(@RequestBody VehicleFuelLogRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Vehicle Fuel Log")
    public ResponseEntity<VehicleFuelLogResponse> update(@PathVariable UUID id, @RequestBody VehicleFuelLogRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Vehicle Fuel Log by ID")
    public ResponseEntity<VehicleFuelLogResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/vehicle/{vehicleId}")
    @Operation(summary = "Get all fuel logs for a specific vehicle")
    public ResponseEntity<Page<VehicleFuelLogResponse>> getByVehicleId(
            @PathVariable UUID vehicleId,
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(service.getByVehicleId(vehicleId, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Vehicle Fuel Log")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
