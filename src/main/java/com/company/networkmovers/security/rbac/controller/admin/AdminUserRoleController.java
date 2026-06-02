package com.company.networkmovers.security.rbac.controller.admin;

import com.company.networkmovers.security.rbac.dto.request.BulkUserRoleRequest;
import com.company.networkmovers.security.rbac.dto.request.UserRoleRequest;
import com.company.networkmovers.security.rbac.dto.response.UserRoleResponse;
import com.company.networkmovers.security.rbac.service.UserRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/user-roles")
@Tag(name = "Admin User Roles", description = "Endpoints for managing assignments of roles to users")
public class AdminUserRoleController {

    private final UserRoleService userRoleService;

    public AdminUserRoleController(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping
    @Operation(summary = "Assign role to user", description = "Creates a new mapping between a user and a role.")
    public ResponseEntity<UserRoleResponse> assignRole(@RequestBody UserRoleRequest request) {
        return ResponseEntity.ok(userRoleService.assignRole(request));
    }

    @PostMapping("/bulk")
    @Operation(summary = "Assign multiple roles to a user", description = "Creates mappings for multiple roles to a single user in bulk.")
    public ResponseEntity<List<UserRoleResponse>> assignRolesBulk(@RequestBody BulkUserRoleRequest request) {
        return ResponseEntity.ok(userRoleService.assignRolesBulk(request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Unassign role", description = "Removes a specific user-role mapping by its UUID.")
    public ResponseEntity<Void> unassignRole(
            @Parameter(description = "UUID of the user-role mapping to delete", required = true) @PathVariable UUID id) {
        userRoleService.unassignRole(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/bulk")
    @Operation(summary = "Unassign multiple roles", description = "Removes multiple user-role mappings by their UUIDs.")
    public ResponseEntity<Void> unassignRolesBulk(
            @Parameter(description = "List of UUIDs of mappings to delete", required = true) @RequestBody List<UUID> ids) {
        userRoleService.unassignRolesBulk(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get roles for user", description = "Retrieves all roles currently assigned to a specific user.")
    public ResponseEntity<List<UserRoleResponse>> getRolesForUser(
            @Parameter(description = "ID of the user", required = true) @PathVariable Long userId) {
        return ResponseEntity.ok(userRoleService.getRolesForUser(userId));
    }

    @GetMapping("/role/{roleId}")
    @Operation(summary = "Get users for role", description = "Retrieves all users mapped to a specific role.")
    public ResponseEntity<List<UserRoleResponse>> getUsersForRole(
            @Parameter(description = "UUID of the role", required = true) @PathVariable UUID roleId) {
        return ResponseEntity.ok(userRoleService.getUsersForRole(roleId));
    }

    @GetMapping
    @Operation(summary = "Pageable search of records", description = "Query, filter, paginate, and sort user-role mapping records.")
    public ResponseEntity<org.springframework.data.domain.Page<UserRoleResponse>> getAll(
            @org.springdoc.core.annotations.ParameterObject com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        return ResponseEntity.ok(userRoleService.getAll(requestParams));
    }
}
