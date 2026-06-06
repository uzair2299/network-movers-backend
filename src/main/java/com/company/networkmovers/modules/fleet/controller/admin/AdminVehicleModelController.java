package com.company.networkmovers.modules.fleet.controller.admin;

import com.company.networkmovers.modules.fleet.dto.request.VehicleModelRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleModelResponse;
import com.company.networkmovers.modules.fleet.service.VehicleModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(
        summary = "Create a new Vehicle Model",
        description = "Creates a new vehicle model with properties like capacity, dimensions, and associates it with a vehicle make and type. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Vehicle model created successfully"),
        @ApiResponse(responseCode = "400", description = "Validation failed or invalid reference make/type IDs"),
        @ApiResponse(responseCode = "401", description = "Unauthorized — missing or invalid JWT token"),
        @ApiResponse(responseCode = "403", description = "Forbidden — requires ROLE_ADMIN")
    })
    public ResponseEntity<VehicleModelResponse> create(@RequestBody VehicleModelRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(
        summary = "Update an existing Vehicle Model",
        description = "Updates all fields of an existing vehicle model. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vehicle model updated successfully"),
        @ApiResponse(responseCode = "400", description = "Validation failed or vehicle model not found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized — missing or invalid JWT token"),
        @ApiResponse(responseCode = "403", description = "Forbidden — requires ROLE_ADMIN")
    })
    public ResponseEntity<VehicleModelResponse> update(
            @Parameter(description = "ID of the vehicle model to update", required = true)
            @PathVariable UUID id,
            @RequestBody VehicleModelRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @GetMapping("/{id}")
    @Operation(
        summary = "Get Vehicle Model by ID",
        description = "Retrieves details of a single vehicle model by its unique ID. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vehicle model found and returned successfully"),
        @ApiResponse(responseCode = "400", description = "Vehicle model not found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized — missing or invalid JWT token"),
        @ApiResponse(responseCode = "403", description = "Forbidden — requires ROLE_ADMIN")
    })
    public ResponseEntity<VehicleModelResponse> getById(
            @Parameter(description = "ID of the vehicle model to retrieve", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    @Operation(
        summary = "Search Vehicle Models with pagination",
        description = "Returns a paginated list of vehicle models matching the optional search query on name, code, or make. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paginated list of vehicle models returned successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized — missing or invalid JWT token"),
        @ApiResponse(responseCode = "403", description = "Forbidden — requires ROLE_ADMIN")
    })
    public ResponseEntity<Page<VehicleModelResponse>> search(
            @Parameter(description = "Search query for model code, name, or make") @RequestParam(required = false) String query,
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(service.search(query, pageable));
    }

    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete Vehicle Model",
        description = "Soft-deletes/deactivates a vehicle model by its ID. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Vehicle model deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Vehicle model not found or has active associations"),
        @ApiResponse(responseCode = "401", description = "Unauthorized — missing or invalid JWT token"),
        @ApiResponse(responseCode = "403", description = "Forbidden — requires ROLE_ADMIN")
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the vehicle model to delete", required = true)
            @PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
