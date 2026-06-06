package com.company.networkmovers.modules.fleet.controller.admin;

import com.company.networkmovers.modules.fleet.dto.request.VehicleRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleResponse;
import com.company.networkmovers.modules.fleet.entity.enums.VehicleStatus;
import com.company.networkmovers.modules.fleet.service.VehicleService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import com.company.networkmovers.shared.dto.RequestParamDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("fleetAdminVehicleController")
@RequestMapping("/api/v1/admin/fleet/vehicles")
@Tag(name = "Admin Vehicles", description = "Admin API for managing Vehicles")
public class AdminVehicleController extends AbstractLookupController<VehicleRequest, VehicleResponse> {

    private final VehicleService vehicleService;

    public AdminVehicleController(VehicleService service) {
        super(service);
        this.vehicleService = service;
    }

    @Override
    @PostMapping
    @Operation(
        summary = "Create a new Vehicle",
        description = "Creates a new vehicle and associates it with a vehicle model. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "201",
            description = "Vehicle created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VehicleResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Validation failed or invalid reference model ID", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<VehicleResponse> create(@RequestBody VehicleRequest request) {
        return new ResponseEntity<>(vehicleService.create(request), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(
        summary = "Update an existing Vehicle",
        description = "Updates all fields of an existing vehicle by its ID. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Vehicle updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VehicleResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Validation failed or vehicle not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<VehicleResponse> update(
            @Parameter(description = "ID of the vehicle to update", required = true) @PathVariable UUID id,
            @RequestBody VehicleRequest request) {
        return ResponseEntity.ok(vehicleService.update(id, request));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(
        summary = "Get Vehicle by ID",
        description = "Retrieves details of a single vehicle by its unique ID. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Vehicle details returned successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VehicleResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Vehicle not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<VehicleResponse> getById(
            @Parameter(description = "ID of the vehicle to retrieve", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(vehicleService.getById(id));
    }

    @Override
    @GetMapping
    @Operation(
        summary = "Search Vehicles with pagination",
        description = "Returns a paginated list of vehicles matching the optional search filter query. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Paginated list of vehicles returned successfully"
        ),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Page<VehicleResponse>> getAll(
            @org.springdoc.core.annotations.ParameterObject RequestParamDto requestParams) {
        return ResponseEntity.ok(vehicleService.getAll(requestParams));
    }

    @PatchMapping("/{id}/status")
    @Operation(
        summary = "Update Vehicle Status",
        description = "Updates the status of an existing vehicle (e.g., AVAILABLE, ASSIGNED, IN_TRANSIT, MAINTENANCE, OUT_OF_SERVICE, RETIRED). Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Vehicle status updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VehicleResponse.class))
        ),
        @ApiResponse(responseCode = "400", description = "Validation failed or vehicle not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<VehicleResponse> updateStatus(
            @Parameter(description = "ID of the vehicle to update status for", required = true)
            @PathVariable UUID id,
            @Parameter(description = "New status value for the vehicle", required = true)
            @RequestParam VehicleStatus status) {
        return ResponseEntity.ok(vehicleService.updateStatus(id, status));
    }

    @GetMapping("/status/{status}")
    @Operation(
        summary = "Get Vehicles by Status",
        description = "Returns a paginated list of vehicles filtered by their operational status. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Paginated list of vehicles returned successfully"
        ),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Page<VehicleResponse>> getByStatus(
            @Parameter(description = "Operational status to filter vehicles by", required = true)
            @PathVariable VehicleStatus status,
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(vehicleService.searchByStatus(status, pageable));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(
        summary = "Delete Vehicle",
        description = "Soft-deletes (deactivates) a vehicle by its ID. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Vehicle deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Vehicle not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the vehicle to delete", required = true)
            @PathVariable UUID id) {
        vehicleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
