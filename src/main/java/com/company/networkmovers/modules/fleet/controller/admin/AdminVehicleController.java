package com.company.networkmovers.modules.fleet.controller.admin;

import com.company.networkmovers.modules.fleet.dto.request.VehicleRequest;
import com.company.networkmovers.modules.fleet.dto.response.VehicleResponse;
import com.company.networkmovers.modules.fleet.entity.enums.VehicleStatus;
import com.company.networkmovers.modules.fleet.service.VehicleService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @PatchMapping("/{id}/status")
    @Operation(
        summary = "Update Vehicle Status",
        description = "Updates the status of an existing vehicle (e.g., ACTIVE, IN_SERVICE, UNDER_MAINTENANCE, INACTIVE). Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vehicle status updated successfully"),
        @ApiResponse(responseCode = "400", description = "Validation failed or vehicle not found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized — missing or invalid JWT token"),
        @ApiResponse(responseCode = "403", description = "Forbidden — requires ROLE_ADMIN")
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
        @ApiResponse(responseCode = "200", description = "Paginated list of vehicles returned successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized — missing or invalid JWT token"),
        @ApiResponse(responseCode = "403", description = "Forbidden — requires ROLE_ADMIN")
    })
    public ResponseEntity<Page<VehicleResponse>> getByStatus(
            @Parameter(description = "Operational status to filter vehicles by", required = true)
            @PathVariable VehicleStatus status,
            @Parameter(description = "Pagination parameters") Pageable pageable) {
        return ResponseEntity.ok(vehicleService.searchByStatus(status, pageable));
    }
}
