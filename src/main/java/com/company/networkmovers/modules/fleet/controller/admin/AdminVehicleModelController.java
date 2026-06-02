package com.company.networkmovers.modules.fleet.controller.admin;

import com.company.networkmovers.modules.fleet.dto.request.VehicleModelRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleModelResponse;
import com.company.networkmovers.modules.fleet.service.VehicleModelService;
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
@RequestMapping("/api/v1/admin/fleet/vehicle-models")
@Tag(name = "Admin Vehicle Models", description = "Admin API for managing Vehicle Models")
@RequiredArgsConstructor
public class AdminVehicleModelController {

    private final VehicleModelService service;

    @PostMapping
    @Operation(summary = "Create a new Vehicle Model")
    public ResponseEntity<VehicleModelResponse> create(@RequestBody VehicleModelRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Vehicle Model")
    public ResponseEntity<VehicleModelResponse> update(@PathVariable UUID id, @RequestBody VehicleModelRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Vehicle Model by ID")
    public ResponseEntity<VehicleModelResponse> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(summary = "Search Vehicle Models with pagination")
    public ResponseEntity<Page<VehicleModelResponse>> search(
            @Parameter(description = "Search query for model code, name, or make") @RequestParam(required = false) String query,
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(service.search(query, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Vehicle Model")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
