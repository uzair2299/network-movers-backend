package com.company.networkmovers.modules.rbac.controller.admin;

import com.company.networkmovers.modules.navigation.dto.request.MenuItemRequest;
import com.company.networkmovers.modules.navigation.dto.response.MenuItemResponse;
import com.company.networkmovers.modules.navigation.service.NavigationService;
import com.company.networkmovers.shared.dto.RequestParamDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController("modulesAdminModuleController")
@RequestMapping("/api/v1/admin/rbac/modules")
@Tag(name = "Admin Modules", description = "Admin API for managing system modules / menu items")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class AdminModuleController {

    private final NavigationService navigationService;

    public AdminModuleController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @PostMapping
    @Operation(summary = "Create a new Module/MenuItem", description = "Creates a new system menu item (module). Section must be SIDEBAR, TOPBAR, or PROFILE. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Module created successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuItemResponse.class))),
        @ApiResponse(responseCode = "400", description = "Validation failed or invalid section", content = @Content)
    })
    public ResponseEntity<MenuItemResponse> create(@Valid @RequestBody MenuItemRequest request) {
        MenuItemResponse created = navigationService.createMenuItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Module/MenuItem", description = "Updates fields of an existing menu item (module) by its ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Module updated successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuItemResponse.class))),
        @ApiResponse(responseCode = "400", description = "Validation failed, item not found, invalid section, or self-referencing parent", content = @Content)
    })
    public ResponseEntity<MenuItemResponse> update(
            @Parameter(description = "ID of the menu item to update", required = true) @PathVariable Long id,
            @Valid @RequestBody MenuItemRequest request) {
        return ResponseEntity.ok(navigationService.updateMenuItem(id, request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Module/MenuItem by ID", description = "Retrieves details of a single menu item (module) by its ID. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Module details returned successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MenuItemResponse.class))),
        @ApiResponse(responseCode = "400", description = "Module not found", content = @Content)
    })
    public ResponseEntity<MenuItemResponse> getById(
            @Parameter(description = "ID of the menu item to retrieve", required = true) @PathVariable Long id) {
        return ResponseEntity.ok(navigationService.getItemById(id));
    }

    @GetMapping
    @Operation(summary = "Search Modules/MenuItems with pagination", description = "Returns a paginated list of menu items matching the optional search filter. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Paginated list of modules returned successfully")
    })
    public ResponseEntity<Page<MenuItemResponse>> getAll(
            @org.springdoc.core.annotations.ParameterObject RequestParamDto requestParams) {
        return ResponseEntity.ok(navigationService.getAll(requestParams));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Module/MenuItem", description = "Soft-deletes a menu item. Returns 400 if the item has active child submenus. Requires ROLE_ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Module deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Cannot delete — item has active child submenus", content = @Content)
    })
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID of the menu item to soft-delete", required = true) @PathVariable Long id) {
        navigationService.softDeleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
