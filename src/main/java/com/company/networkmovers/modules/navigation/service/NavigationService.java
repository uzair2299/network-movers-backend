package com.company.networkmovers.modules.navigation.service;

import com.company.networkmovers.modules.navigation.dto.request.MenuItemRequest;
import com.company.networkmovers.modules.navigation.dto.response.MenuItemResponse;
import com.company.networkmovers.modules.navigation.entity.MenuItem;
import com.company.networkmovers.modules.navigation.exception.NavigationException;
import com.company.networkmovers.modules.navigation.repository.MenuItemRepository;
import com.company.networkmovers.security.context.CustomUserDetails;
import com.company.networkmovers.security.rbac.Permission;
import com.company.networkmovers.security.rbac.RolePermissionRepository;
import jakarta.persistence.EntityManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NavigationService {

    private final MenuItemRepository menuItemRepository;
    private final RolePermissionRepository rolePermissionRepository;
    private final EntityManager entityManager;

    public NavigationService(MenuItemRepository menuItemRepository,
                             RolePermissionRepository rolePermissionRepository,
                             EntityManager entityManager) {
        this.menuItemRepository = menuItemRepository;
        this.rolePermissionRepository = rolePermissionRepository;
        this.entityManager = entityManager;
    }

    // =========================================================================
    // USER-FACING: Returns the active, filtered navigation tree
    // =========================================================================

    @Transactional(readOnly = true)
    public List<MenuItemResponse> getNavigationMenuForUser(String section, UserDetails userDetails) {
        List<String> userAuthorities = getUserAuthorities(userDetails);
        List<MenuItem> allowedItems = menuItemRepository.findAllActiveBySectionAndPermissions(section, userAuthorities);
        return buildTree(allowedItems);
    }

    @Transactional(readOnly = true)
    public Map<String, List<MenuItemResponse>> getAllNavigationMenusForUser(UserDetails userDetails) {
        List<String> userAuthorities = getUserAuthorities(userDetails);
        List<MenuItem> allowedItems = menuItemRepository.findAllActiveByPermissions(userAuthorities);

        Map<String, List<MenuItem>> itemsBySection = allowedItems.stream()
                .collect(Collectors.groupingBy(MenuItem::getSection));

        Map<String, List<MenuItemResponse>> result = new HashMap<>();
        result.put("SIDEBAR", buildTree(itemsBySection.getOrDefault("SIDEBAR", Collections.emptyList())));
        result.put("TOPBAR", buildTree(itemsBySection.getOrDefault("TOPBAR", Collections.emptyList())));
        result.put("PROFILE", buildTree(itemsBySection.getOrDefault("PROFILE", Collections.emptyList())));
        return result;
    }

    // =========================================================================
    // ADMIN CRUD: Manage menu items
    // =========================================================================

    @Transactional(readOnly = true)
    public List<MenuItemResponse> getAllItems() {
        return menuItemRepository.findAllByDeletedFalseOrderBySortOrderAsc()
                .stream()
                .map(this::toFlatResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public MenuItemResponse getItemById(Long id) {
        MenuItem item = findActiveById(id);
        return toFlatResponse(item);
    }

    @Transactional
    public MenuItemResponse createMenuItem(MenuItemRequest request) {
        String section = request.getSection().toUpperCase();
        validateSection(section);

        MenuItem.MenuItemBuilder<?, ?> builder = MenuItem.builder()
                .name(request.getName())
                .icon(request.getIcon())
                .path(request.getPath())
                .section(section)
                .sortOrder(request.getSortOrder())
                .active(request.isActive());

        // Resolve optional parent reference
        if (request.getParentId() != null) {
            MenuItem parent = findActiveById(request.getParentId());
            builder.parent(parent);
        }

        // Resolve optional permission reference
        if (request.getPermissionId() != null) {
            builder.permission(entityManager.getReference(Permission.class, request.getPermissionId()));
        }

        MenuItem saved = menuItemRepository.save(builder.build());
        return toFlatResponse(saved);
    }

    @Transactional
    public MenuItemResponse updateMenuItem(Long id, MenuItemRequest request) {
        MenuItem item = findActiveById(id);

        String section = request.getSection().toUpperCase();
        validateSection(section);

        item.setName(request.getName());
        item.setIcon(request.getIcon());
        item.setPath(request.getPath());
        item.setSection(section);
        item.setSortOrder(request.getSortOrder());
        item.setActive(request.isActive());

        // Resolve optional parent reference
        if (request.getParentId() != null) {
            if (request.getParentId().equals(id)) {
                throw new NavigationException("A menu item cannot be its own parent.");
            }
            MenuItem parent = findActiveById(request.getParentId());
            item.setParent(parent);
        } else {
            item.setParent(null);
        }

        // Resolve optional permission reference
        if (request.getPermissionId() != null) {
            item.setPermission(entityManager.getReference(Permission.class, request.getPermissionId()));
        } else {
            item.setPermission(null);
        }

        MenuItem updated = menuItemRepository.save(item);
        return toFlatResponse(updated);
    }

    /**
     * Soft-deletes a menu item.
     * Enforces the constraint: a parent item cannot be deleted if it has active child submenus.
     */
    @Transactional
    public void softDeleteMenuItem(Long id) {
        MenuItem item = findActiveById(id);

        // *** KEY CONSTRAINT: Block deletion if active children exist ***
        if (menuItemRepository.existsByParentIdAndDeletedFalse(id)) {
            throw new NavigationException(
                    "Cannot delete menu item '" + item.getName() + "' because it has active child menu items. " +
                    "Please delete or reassign all child items first."
            );
        }

        Long currentUserId = getCurrentUserId();
        item.delete(currentUserId);   // sets deleted=true, deletedAt, deletedBy from BaseSoftDeleteEntity
        item.setActive(false);
        menuItemRepository.save(item);
    }

    // =========================================================================
    // Private helpers
    // =========================================================================

    private MenuItem findActiveById(Long id) {
        return menuItemRepository.findById(id)
                .filter(m -> !m.isDeleted())
                .orElseThrow(() -> new NavigationException("Menu item not found with id: " + id));
    }

    private List<String> getUserAuthorities(UserDetails userDetails) {
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (roles.isEmpty()) {
            return Collections.singletonList("ROLE_ANONYMOUS");
        }

        List<String> permissions = rolePermissionRepository.findPermissionNamesByRoleNames(roles);

        List<String> allAuthorities = new ArrayList<>(roles);
        allAuthorities.addAll(permissions);
        return allAuthorities;
    }

    private List<MenuItemResponse> buildTree(List<MenuItem> allowedItems) {
        Map<Long, MenuItemResponse> responseMap = new LinkedHashMap<>();
        for (MenuItem item : allowedItems) {
            responseMap.put(item.getId(), MenuItemResponse.builder()
                    .id(item.getId())
                    .name(item.getName())
                    .icon(item.getIcon())
                    .path(item.getPath())
                    .section(item.getSection())
                    .sortOrder(item.getSortOrder())
                    .active(item.isActive())
                    .children(new ArrayList<>())
                    .build());
        }

        List<MenuItemResponse> rootMenu = new ArrayList<>();

        for (MenuItem item : allowedItems) {
            MenuItemResponse responseNode = responseMap.get(item.getId());
            if (item.getParent() != null) {
                MenuItemResponse parentNode = responseMap.get(item.getParent().getId());
                if (parentNode != null) {
                    parentNode.getChildren().add(responseNode);
                }
            } else {
                rootMenu.add(responseNode);
            }
        }

        rootMenu.sort(Comparator.comparingInt(MenuItemResponse::getSortOrder));
        for (MenuItemResponse node : responseMap.values()) {
            node.getChildren().sort(Comparator.comparingInt(MenuItemResponse::getSortOrder));
        }

        return rootMenu;
    }

    private MenuItemResponse toFlatResponse(MenuItem item) {
        return MenuItemResponse.builder()
                .id(item.getId())
                .name(item.getName())
                .icon(item.getIcon())
                .path(item.getPath())
                .section(item.getSection())
                .parentId(item.getParent() != null ? item.getParent().getId() : null)
                .permissionId(item.getPermission() != null ? item.getPermission().getId() : null)
                .sortOrder(item.getSortOrder())
                .active(item.isActive())
                .children(Collections.emptyList())
                .build();
    }

    private void validateSection(String section) {
        if (!Set.of("SIDEBAR", "TOPBAR", "PROFILE").contains(section)) {
            throw new NavigationException("Invalid section '" + section + "'. Must be one of: SIDEBAR, TOPBAR, PROFILE.");
        }
    }

    private Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()
                && authentication.getPrincipal() instanceof CustomUserDetails) {
            return ((CustomUserDetails) authentication.getPrincipal()).getId();
        }
        return null;
    }
}
