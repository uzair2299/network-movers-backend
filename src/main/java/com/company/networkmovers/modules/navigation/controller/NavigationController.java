package com.company.networkmovers.modules.navigation.controller;

import com.company.networkmovers.modules.navigation.dto.request.MenuItemRequest;
import com.company.networkmovers.modules.navigation.dto.response.MenuItemResponse;
import com.company.networkmovers.modules.navigation.service.NavigationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/navigation")
@Tag(name = "Navigation", description = "Endpoints for dynamic navigation menu — user-facing tree and admin CRUD management")
public class NavigationController {

    private final NavigationService navigationService;

    public NavigationController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    // =========================================================================
    // USER-FACING: Returns permission-filtered navigation tree for the logged-in user
    // =========================================================================

    @GetMapping
    @Operation(
        summary = "Get navigation menu for the authenticated user",
        description = "Returns the full navigation tree (all sections: SIDEBAR, TOPBAR, PROFILE) or a single section " +
                      "filtered by the current user's roles and permissions. Pass ?section=SIDEBAR to get one section."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Navigation tree returned successfully",
            content = @Content(mediaType = "application/json",
                schema = @Schema(description = "Map of section (SIDEBAR/TOPBAR/PROFILE) to list of MenuItemResponse, or a single list when ?section= is provided"))),
        @ApiResponse(responseCode = "401", description = "Unauthorized — missing or invalid JWT token", content = @Content)
    })
    public ResponseEntity<Object> getUserNavigation(
            @Parameter(description = "Optional section filter: SIDEBAR, TOPBAR, or PROFILE")
            @RequestParam(value = "section", required = false) String section,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (section != null) {
            List<MenuItemResponse> menu = navigationService.getNavigationMenuForUser(
                    section.toUpperCase(), userDetails);
            return ResponseEntity.ok((Object) menu);
        }
        Map<String, List<MenuItemResponse>> menus = navigationService.getAllNavigationMenusForUser(userDetails);
        return ResponseEntity.ok((Object) menus);
    }

    // =========================================================================
    // ADMIN CRUD: Manage menu items — requires ROLE_ADMIN
    // =========================================================================

    @GetMapping("/items")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
        summary = "List all menu items (Admin)",
        description = "Returns all non-deleted menu items as a flat list. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "List returned successfully"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden — requires ROLE_ADMIN")
    })
    public ResponseEntity<List<MenuItemResponse>> getAllItems() {
        return ResponseEntity.ok(navigationService.getAllItems());
    }

    @GetMapping("/items/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
        summary = "Get a single menu item by ID (Admin)",
        description = "Returns one non-deleted menu item by its ID. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Menu item found"),
        @ApiResponse(responseCode = "400", description = "Menu item not found"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden — requires ROLE_ADMIN")
    })
    public ResponseEntity<MenuItemResponse> getItemById(
            @Parameter(description = "ID of the menu item", required = true)
            @PathVariable Long id) {
        return ResponseEntity.ok(navigationService.getItemById(id));
    }

    @PostMapping("/items")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
        summary = "Create a new menu item (Admin)",
        description = "Creates a new menu item. Section must be SIDEBAR, TOPBAR, or PROFILE. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Menu item created successfully"),
        @ApiResponse(responseCode = "400", description = "Validation failed or invalid section"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden — requires ROLE_ADMIN")
    })
    public ResponseEntity<MenuItemResponse> createMenuItem(
            @Valid @RequestBody MenuItemRequest request) {
        MenuItemResponse created = navigationService.createMenuItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/items/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
        summary = "Update an existing menu item (Admin)",
        description = "Updates all fields of a menu item. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Menu item updated successfully"),
        @ApiResponse(responseCode = "400", description = "Validation failed, item not found, invalid section, or self-referencing parent"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden — requires ROLE_ADMIN")
    })
    public ResponseEntity<MenuItemResponse> updateMenuItem(
            @Parameter(description = "ID of the menu item to update", required = true)
            @PathVariable Long id,
            @Valid @RequestBody MenuItemRequest request) {
        return ResponseEntity.ok(navigationService.updateMenuItem(id, request));
    }

    @DeleteMapping("/items/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Operation(
        summary = "Soft-delete a menu item (Admin)",
        description = "Soft-deletes a menu item. Returns 400 if the item has active child submenus — " +
                      "all children must be deleted or reassigned first. Requires ROLE_ADMIN."
    )
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Menu item deleted successfully"),
        @ApiResponse(responseCode = "400", description = "Cannot delete — item has active child submenus"),
        @ApiResponse(responseCode = "401", description = "Unauthorized"),
        @ApiResponse(responseCode = "403", description = "Forbidden — requires ROLE_ADMIN")
    })
    public ResponseEntity<Void> deleteMenuItem(
            @Parameter(description = "ID of the menu item to soft-delete", required = true)
            @PathVariable Long id) {
        navigationService.softDeleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
