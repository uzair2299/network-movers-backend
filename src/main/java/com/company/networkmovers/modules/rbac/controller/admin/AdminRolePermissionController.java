package com.company.networkmovers.modules.rbac.controller.admin;

import com.company.networkmovers.modules.rbac.dto.request.BulkRolePermissionRequest;
import com.company.networkmovers.modules.rbac.dto.request.RolePermissionRequest;
import com.company.networkmovers.modules.rbac.dto.response.RolePermissionResponse;
import com.company.networkmovers.modules.rbac.service.RolePermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("modulesAdminRolePermissionController")
@RequestMapping("/api/v1/admin/rbac/role-permissions")
@Tag(name = "Admin Role Permissions", description = "Endpoints for managing assignments of permissions to roles")
public class AdminRolePermissionController {

    private final RolePermissionService rolePermissionService;

    public AdminRolePermissionController(
            @Qualifier("modulesRolePermissionServiceImpl") RolePermissionService rolePermissionService) {
        this.rolePermissionService = rolePermissionService;
    }

    @PostMapping
    @Operation(summary = "Assign permission to role",
               description = "Creates a new mapping between a role and a permission. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Permission assigned successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RolePermissionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Role or Permission not found, or already assigned", content = @Content)
    })
    public ResponseEntity<RolePermissionResponse> assign(@RequestBody RolePermissionRequest request) {
        return ResponseEntity.ok(rolePermissionService.assign(request));
    }

    @PostMapping("/bulk")
    @Operation(summary = "Assign permissions to role in bulk",
               description = "Synchronizes the permissions assigned to a role. Permissions not included in the payload will be soft-deleted. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Permissions synchronized successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = RolePermissionResponse.class))),
        @ApiResponse(responseCode = "400", description = "Role or Permission IDs invalid", content = @Content)
    })
    public ResponseEntity<List<RolePermissionResponse>> assignBulk(@RequestBody BulkRolePermissionRequest request) {
        return ResponseEntity.ok(rolePermissionService.assignBulk(request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Revoke permission from role",
               description = "Soft-deletes a role-permission mapping by its UUID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Permission revoked successfully")
    })
    public ResponseEntity<Void> revoke(
            @Parameter(description = "UUID of the role-permission mapping to revoke", required = true)
            @PathVariable UUID id) {
        rolePermissionService.revoke(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a role-permission assignment by ID")
    public ResponseEntity<RolePermissionResponse> getById(
            @Parameter(description = "UUID of the role-permission mapping", required = true)
            @PathVariable UUID id) {
        return ResponseEntity.ok(rolePermissionService.getById(id));
    }

    @GetMapping
    @Operation(summary = "List all role-permission assignments with pagination",
               description = "Returns a paginated list of all active role-permission assignments. Requires ROLE_ADMIN.")
    public ResponseEntity<Page<RolePermissionResponse>> getAll(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort) {
        String[] sortParts = sort.split(",");
        Sort.Direction direction = sortParts.length > 1 && "desc".equalsIgnoreCase(sortParts[1])
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortParts[0]));
        return ResponseEntity.ok(rolePermissionService.getAll(pageable));
    }

    @GetMapping("/role/{roleId}")
    @Operation(summary = "Get all permissions assigned to a role")
    public ResponseEntity<List<RolePermissionResponse>> getByRoleId(
            @Parameter(description = "UUID of the role", required = true) @PathVariable UUID roleId) {
        return ResponseEntity.ok(rolePermissionService.getByRoleId(roleId));
    }

    @GetMapping("/permission/{permissionId}")
    @Operation(summary = "Get all roles that have a specific permission")
    public ResponseEntity<List<RolePermissionResponse>> getByPermissionId(
            @Parameter(description = "UUID of the permission", required = true) @PathVariable UUID permissionId) {
        return ResponseEntity.ok(rolePermissionService.getByPermissionId(permissionId));
    }
}
