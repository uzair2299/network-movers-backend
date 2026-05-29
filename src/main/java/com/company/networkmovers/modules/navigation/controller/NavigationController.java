package com.company.networkmovers.modules.navigation.controller;

import com.company.networkmovers.modules.navigation.dto.request.MenuItemRequest;
import com.company.networkmovers.modules.navigation.dto.response.MenuItemResponse;
import com.company.networkmovers.modules.navigation.service.NavigationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/navigation")
public class NavigationController {

    private final NavigationService navigationService;

    public NavigationController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    // =========================================================================
    // USER-FACING: Returns permission-filtered navigation tree for the logged-in user
    // =========================================================================

    /**
     * GET /api/v1/navigation
     * GET /api/v1/navigation?section=SIDEBAR
     *
     * Returns the full navigation tree (all sections) or a single section tree
     * filtered by the current user's roles and permissions.
     */
    @GetMapping
    public ResponseEntity<?> getUserNavigation(
            @RequestParam(value = "section", required = false) String section,
            @AuthenticationPrincipal UserDetails userDetails) {

        if (section != null) {
            List<MenuItemResponse> menu = navigationService.getNavigationMenuForUser(
                    section.toUpperCase(), userDetails);
            return ResponseEntity.ok(menu);
        }
        Map<String, List<MenuItemResponse>> menus = navigationService.getAllNavigationMenusForUser(userDetails);
        return ResponseEntity.ok(menus);
    }

    // =========================================================================
    // ADMIN CRUD: Manage menu items
    // =========================================================================

    /**
     * GET /api/v1/navigation/items
     * Returns all non-deleted menu items as a flat list for admin management.
     */
    @GetMapping("/items")
    public ResponseEntity<List<MenuItemResponse>> getAllItems() {
        return ResponseEntity.ok(navigationService.getAllItems());
    }

    /**
     * GET /api/v1/navigation/items/{id}
     * Returns a single non-deleted menu item by ID.
     */
    @GetMapping("/items/{id}")
    public ResponseEntity<MenuItemResponse> getItemById(@PathVariable Long id) {
        return ResponseEntity.ok(navigationService.getItemById(id));
    }

    /**
     * POST /api/v1/navigation/items
     * Creates a new menu item.
     */
    @PostMapping("/items")
    public ResponseEntity<MenuItemResponse> createMenuItem(@Valid @RequestBody MenuItemRequest request) {
        MenuItemResponse created = navigationService.createMenuItem(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * PUT /api/v1/navigation/items/{id}
     * Updates an existing menu item's fields.
     */
    @PutMapping("/items/{id}")
    public ResponseEntity<MenuItemResponse> updateMenuItem(
            @PathVariable Long id,
            @Valid @RequestBody MenuItemRequest request) {
        return ResponseEntity.ok(navigationService.updateMenuItem(id, request));
    }

    /**
     * DELETE /api/v1/navigation/items/{id}
     * Soft-deletes a menu item.
     * Returns 400 Bad Request if the item has active child submenus.
     * Returns 204 No Content on success.
     */
    @DeleteMapping("/items/{id}")
    public ResponseEntity<Void> deleteMenuItem(@PathVariable Long id) {
        navigationService.softDeleteMenuItem(id);
        return ResponseEntity.noContent().build();
    }
}
