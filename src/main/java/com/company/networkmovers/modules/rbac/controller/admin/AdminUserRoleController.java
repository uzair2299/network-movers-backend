package com.company.networkmovers.modules.rbac.controller.admin;

import com.company.networkmovers.modules.rbac.dto.request.BulkUserRoleRequest;
import com.company.networkmovers.modules.rbac.dto.request.UserRoleRequest;
import com.company.networkmovers.modules.rbac.dto.response.UserRoleResponse;
import com.company.networkmovers.modules.rbac.service.UserRoleService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController("modulesAdminUserRoleController")
@RequestMapping("/api/v1/admin/rbac/user-roles")
@Tag(name = "Admin User Roles", description = "Endpoints for managing assignments of roles to users")
public class AdminUserRoleController {

    private final UserRoleService userRoleService;

    public AdminUserRoleController(@Qualifier("modulesUserRoleServiceImpl") UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping
    @Operation(summary = "Assign role to user", description = "Creates a new mapping between a user and a role. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Role assigned successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserRoleResponse.class))),
        @ApiResponse(responseCode = "400", description = "Role or User not found", content = @Content)
    })
    public ResponseEntity<UserRoleResponse> assignRole(@RequestBody UserRoleRequest request) {
        return ResponseEntity.ok(userRoleService.assignRole(request));
    }

    @PostMapping("/bulk")
    @Operation(summary = "Assign multiple roles to a user", description = "Creates mappings for multiple roles to a single user in bulk. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Roles assigned successfully")
    })
    public ResponseEntity<List<UserRoleResponse>> assignRolesBulk(@RequestBody BulkUserRoleRequest request) {
        return ResponseEntity.ok(userRoleService.assignRolesBulk(request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Unassign role", description = "Removes a specific user-role mapping by its UUID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Role unassigned successfully")
    })
    public ResponseEntity<Void> unassignRole(
            @Parameter(description = "UUID of the user-role mapping to delete", required = true) @PathVariable UUID id) {
        userRoleService.unassignRole(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/bulk")
    @Operation(summary = "Unassign multiple roles", description = "Removes multiple user-role mappings by their UUIDs. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Roles unassigned successfully")
    })
    public ResponseEntity<Void> unassignRolesBulk(
            @Parameter(description = "List of UUIDs of mappings to delete", required = true) @RequestBody List<UUID> ids) {
        userRoleService.unassignRolesBulk(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get roles for user", description = "Retrieves all roles currently assigned to a specific user. Requires ROLE_ADMIN.")
    public ResponseEntity<List<UserRoleResponse>> getRolesForUser(
            @Parameter(description = "ID of the user", required = true) @PathVariable UUID userId) {
        return ResponseEntity.ok(userRoleService.getRolesForUser(userId));
    }

    @GetMapping("/role/{roleId}")
    @Operation(summary = "Get users for role", description = "Retrieves all users mapped to a specific role. Requires ROLE_ADMIN.")
    public ResponseEntity<List<UserRoleResponse>> getUsersForRole(
            @Parameter(description = "UUID of the role", required = true) @PathVariable UUID roleId) {
        return ResponseEntity.ok(userRoleService.getUsersForRole(roleId));
    }

    @GetMapping
    @Operation(summary = "Search User Roles with pagination", description = "Query, filter, paginate, and sort user-role mapping records. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paginated list of user roles returned successfully")
    })
    public ResponseEntity<Page<UserRoleResponse>> getAll(
            @org.springdoc.core.annotations.ParameterObject RequestParamDto requestParams) {
        return ResponseEntity.ok(userRoleService.getAll(requestParams));
    }
}
