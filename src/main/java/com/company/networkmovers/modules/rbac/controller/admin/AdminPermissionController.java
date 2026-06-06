package com.company.networkmovers.modules.rbac.controller.admin;

import com.company.networkmovers.modules.rbac.dto.request.PermissionRequest;
import com.company.networkmovers.modules.rbac.dto.response.PermissionResponse;
import com.company.networkmovers.modules.rbac.service.PermissionService;
import com.company.networkmovers.shared.controller.AbstractLookupController;
import com.company.networkmovers.shared.dto.RequestParamDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController("modulesAdminPermissionController")
@RequestMapping("/api/v1/admin/rbac/permissions")
@Tag(name = "Admin Permissions", description = "Admin API for managing Permissions")
public class AdminPermissionController extends AbstractLookupController<PermissionRequest, PermissionResponse> {

    private final PermissionService permissionService;

    public AdminPermissionController(@Qualifier("modulesPermissionServiceImpl") PermissionService service) {
        super(service);
        this.permissionService = service;
    }

    @Override
    @PostMapping
    @Operation(summary = "Create a new Permission", description = "Creates a new system permission. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Permission created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PermissionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<PermissionResponse> create(@RequestBody PermissionRequest request) {
        return new ResponseEntity<>(permissionService.create(request), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Permission", description = "Updates fields of an existing permission by ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Permission updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PermissionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Validation failed or permission not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<PermissionResponse> update(
            @Parameter(description = "ID of the permission to update", required = true) @PathVariable UUID id,
            @RequestBody PermissionRequest request) {
        return ResponseEntity.ok(permissionService.update(id, request));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get Permission by ID", description = "Retrieves details of a single permission by its unique ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Permission details returned successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = PermissionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Permission not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<PermissionResponse> getById(
            @Parameter(description = "ID of the permission to retrieve", required = true) @PathVariable UUID id) {
        return ResponseEntity.ok(permissionService.getById(id));
    }

    @Override
    @GetMapping
    @Operation(summary = "Search Permissions with pagination", description = "Returns a paginated list of permissions matching optional search filter. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paginated list of permissions returned successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Page<PermissionResponse>> getAll(
            @org.springdoc.core.annotations.ParameterObject RequestParamDto requestParams) {
        return ResponseEntity.ok(permissionService.getAll(requestParams));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Permission", description = "Soft-deletes (deactivates) a permission by its ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Permission deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Permission not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the permission to delete", required = true) @PathVariable UUID id) {
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
