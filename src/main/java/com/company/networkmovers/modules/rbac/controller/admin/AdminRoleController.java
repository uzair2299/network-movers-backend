package com.company.networkmovers.modules.rbac.controller.admin;

import com.company.networkmovers.modules.rbac.dto.request.RoleRequest;
import com.company.networkmovers.modules.rbac.dto.response.RoleResponse;
import com.company.networkmovers.modules.rbac.service.RoleService;
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

@RestController("modulesAdminRoleController")
@RequestMapping("/api/v1/admin/rbac/roles")
@Tag(name = "Admin Roles", description = "Admin API for managing User Roles")
public class AdminRoleController extends AbstractLookupController<RoleRequest, RoleResponse> {

    private final RoleService roleService;

    public AdminRoleController(@Qualifier("modulesRoleServiceImpl") RoleService service) {
        super(service);
        this.roleService = service;
    }

    @Override
    @PostMapping
    @Operation(summary = "Create a new Role", description = "Creates a new administrative or application role. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Role created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))),
        @ApiResponse(responseCode = "400", description = "Validation failed", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<RoleResponse> create(@RequestBody RoleRequest request) {
        return new ResponseEntity<>(roleService.create(request), HttpStatus.CREATED);
    }

    @Override
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Role", description = "Updates fields of an existing role by ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Role updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))),
        @ApiResponse(responseCode = "400", description = "Validation failed or role not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<RoleResponse> update(
            @Parameter(description = "ID of the role to update", required = true) @PathVariable UUID id,
            @RequestBody RoleRequest request) {
        return ResponseEntity.ok(roleService.update(id, request));
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "Get Role by ID", description = "Retrieves details of a single role by its unique ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Role details returned successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RoleResponse.class))),
        @ApiResponse(responseCode = "400", description = "Role not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<RoleResponse> getById(
            @Parameter(description = "ID of the role to retrieve", required = true) @PathVariable UUID id) {
        return ResponseEntity.ok(roleService.getById(id));
    }

    @Override
    @GetMapping
    @Operation(summary = "Search Roles with pagination", description = "Returns a paginated list of roles matching optional search filter. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paginated list of roles returned successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Page<RoleResponse>> getAll(
            @org.springdoc.core.annotations.ParameterObject RequestParamDto requestParams) {
        return ResponseEntity.ok(roleService.getAll(requestParams));
    }

    @Override
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Role", description = "Soft-deletes (deactivates) a role by its ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Role deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Role not found", content = @Content),
        @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
        @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the role to delete", required = true) @PathVariable UUID id) {
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
