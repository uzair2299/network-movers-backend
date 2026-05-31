package com.company.networkmovers.modules.identity.controller.admin;

import com.company.networkmovers.modules.identity.dto.request.AdminUserRequest;
import com.company.networkmovers.modules.identity.dto.response.AdminUserResponse;
import com.company.networkmovers.modules.identity.service.AdminUserService;
import com.company.networkmovers.security.util.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/users")
@Tag(name = "Admin Users", description = "Endpoints for handling users from the admin application")
public class AdminUserController {

    private final AdminUserService adminUserService;

    public AdminUserController(AdminUserService adminUserService) {
        this.adminUserService = adminUserService;
    }

    @PostMapping
    @Operation(summary = "Create master data record", description = "Creates a new master data lookup entry. Requires administrative privileges.")
    public ResponseEntity<AdminUserResponse> create(@RequestBody AdminUserRequest request) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        return ResponseEntity.ok(adminUserService.create(request, currentUserId));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get master data record by ID", description = "Retrieves details of a specific master data record by its ID. Access restricted to admin users.")
    public ResponseEntity<AdminUserResponse> findById(
            @Parameter(description = "ID of the record", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(adminUserService.findById(id));
    }

    @GetMapping("/active")
    @Operation(summary = "List all active records", description = "Retrieves all currently active master data lookup records for administrative review.")
    public ResponseEntity<List<AdminUserResponse>> getAllActive() {
        return ResponseEntity.ok(adminUserService.getAllActive());
    }

    @GetMapping
    @Operation(summary = "Pageable search of records", description = "Query, filter, paginate, and sort lookup records with dynamic search.")
    public ResponseEntity<org.springframework.data.domain.Page<AdminUserResponse>> getAll(
            @org.springdoc.core.annotations.ParameterObject com.company.networkmovers.shared.dto.RequestParamDto requestParams) {
        return ResponseEntity.ok(adminUserService.getAll(requestParams));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update master data record", description = "Updates an existing master data entry by its ID. Requires administrative privileges.")
    public ResponseEntity<AdminUserResponse> update(
            @Parameter(description = "ID of the record to update", required = true) @PathVariable Long id, 
            @RequestBody AdminUserRequest request) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        return ResponseEntity.ok(adminUserService.update(id, request, currentUserId));
    }

    @PutMapping("/{id}/activate")
    @Operation(summary = "Toggle user activation status", description = "Toggles the enabled status of the user.")
    public ResponseEntity<AdminUserResponse> toggleActive(
            @Parameter(description = "ID of the record", required = true) @PathVariable Long id) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        return ResponseEntity.ok(adminUserService.toggleActive(id, currentUserId));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Soft delete master data record", description = "Soft deletes (deactivates) a master data record by its ID. Requires administrative privileges.")
    public ResponseEntity<Void> softDelete(
            @Parameter(description = "ID of the record to delete", required = true) @PathVariable Long id) {
        Long currentUserId = SecurityUtils.getCurrentUserId();
        adminUserService.softDelete(id, currentUserId);
        return ResponseEntity.noContent().build();
    }
}
