package com.company.networkmovers.modules.navigation.service;

import com.company.networkmovers.modules.navigation.dto.response.MenuItemResponse;
import com.company.networkmovers.modules.navigation.entity.MenuItem;
import com.company.networkmovers.modules.navigation.repository.MenuItemRepository;
import com.company.networkmovers.security.rbac.RolePermissionRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class NavigationService {

    private final MenuItemRepository menuItemRepository;
    private final RolePermissionRepository rolePermissionRepository;

    public NavigationService(MenuItemRepository menuItemRepository, RolePermissionRepository rolePermissionRepository) {
        this.menuItemRepository = menuItemRepository;
        this.rolePermissionRepository = rolePermissionRepository;
    }

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
    
    private List<String> getUserAuthorities(UserDetails userDetails) {
        // 1. Get user roles from Spring Security (e.g. ROLE_ADMIN)
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        if (roles.isEmpty()) {
            return Collections.singletonList("ROLE_ANONYMOUS");
        }

        // 2. Fetch permissions associated with these roles (e.g. CRM_READ)
        List<String> permissions = rolePermissionRepository.findPermissionNamesByRoleNames(roles);

        // 3. Combine roles and permissions to allow checking by either
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
                    .sortOrder(item.getSortOrder())
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
}
