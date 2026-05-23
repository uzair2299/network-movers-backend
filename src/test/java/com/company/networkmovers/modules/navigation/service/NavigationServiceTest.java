package com.company.networkmovers.modules.navigation.service;

import com.company.networkmovers.modules.navigation.dto.response.MenuItemResponse;
import com.company.networkmovers.modules.navigation.entity.MenuItem;
import com.company.networkmovers.modules.navigation.repository.MenuItemRepository;
import com.company.networkmovers.security.rbac.Permission;
import com.company.networkmovers.security.rbac.RolePermissionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NavigationServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private RolePermissionRepository rolePermissionRepository;

    private NavigationService navigationService;

    @BeforeEach
    void setUp() {
        navigationService = new NavigationService(menuItemRepository, rolePermissionRepository);
    }

    @Test
    void testGetNavigationMenuForUser_Success() {
        // Arrange
        UserDetails userDetails = new User("admin", "password", 
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
        
        List<String> roles = Collections.singletonList("ROLE_ADMIN");
        List<String> permissions = Arrays.asList("CRM_READ", "HR_READ");
        
        when(rolePermissionRepository.findPermissionNamesByRoleNames(roles)).thenReturn(permissions);

        MenuItem crmModule = MenuItem.builder()
                .id(1L)
                .name("CRM")
                .icon("crm-icon")
                .path("/crm")
                .section("SIDEBAR")
                .parent(null)
                .sortOrder(20)
                .active(true)
                .build();

        MenuItem leadsMenu = MenuItem.builder()
                .id(2L)
                .name("Leads")
                .icon("leads-icon")
                .path("/crm/leads")
                .section("SIDEBAR")
                .parent(crmModule)
                .sortOrder(10)
                .active(true)
                .build();

        MenuItem allLeadsSubmenu = MenuItem.builder()
                .id(3L)
                .name("All Leads")
                .icon("all-leads-icon")
                .path("/crm/leads/all")
                .section("SIDEBAR")
                .parent(leadsMenu)
                .sortOrder(10)
                .active(true)
                .build();

        List<String> expectedAuthorities = Arrays.asList("ROLE_ADMIN", "CRM_READ", "HR_READ");
        when(menuItemRepository.findAllActiveBySectionAndPermissions("SIDEBAR", expectedAuthorities))
                .thenReturn(Arrays.asList(crmModule, leadsMenu, allLeadsSubmenu));

        // Act
        List<MenuItemResponse> rootMenu = navigationService.getNavigationMenuForUser("SIDEBAR", userDetails);

        // Assert
        assertNotNull(rootMenu);
        assertEquals(1, rootMenu.size());
        
        MenuItemResponse crmNode = rootMenu.get(0);
        assertEquals("CRM", crmNode.getName());
        assertEquals(1, crmNode.getChildren().size());
        
        MenuItemResponse leadsNode = crmNode.getChildren().get(0);
        assertEquals("Leads", leadsNode.getName());
        assertEquals(1, leadsNode.getChildren().size());
        
        MenuItemResponse allLeadsNode = leadsNode.getChildren().get(0);
        assertEquals("All Leads", allLeadsNode.getName());
        assertTrue(allLeadsNode.getChildren().isEmpty());
    }

    @Test
    void testGetAllNavigationMenusForUser_GroupedSections() {
        // Arrange
        UserDetails userDetails = new User("customer", "password", 
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
                
        List<String> roles = Collections.singletonList("ROLE_CUSTOMER");
        when(rolePermissionRepository.findPermissionNamesByRoleNames(roles)).thenReturn(Collections.emptyList());

        MenuItem sidebarItem = MenuItem.builder()
                .id(10L)
                .name("Dashboard")
                .section("SIDEBAR")
                .sortOrder(10)
                .active(true)
                .build();

        MenuItem topbarItem = MenuItem.builder()
                .id(20L)
                .name("Support Chat")
                .section("TOPBAR")
                .sortOrder(10)
                .active(true)
                .build();

        MenuItem profileItem = MenuItem.builder()
                .id(30L)
                .name("Logout")
                .section("PROFILE")
                .sortOrder(10)
                .active(true)
                .build();

        List<String> expectedAuthorities = Collections.singletonList("ROLE_CUSTOMER");
        when(menuItemRepository.findAllActiveByPermissions(expectedAuthorities))
                .thenReturn(Arrays.asList(sidebarItem, topbarItem, profileItem));

        // Act
        Map<String, List<MenuItemResponse>> result = navigationService.getAllNavigationMenusForUser(userDetails);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.size());
        assertTrue(result.containsKey("SIDEBAR"));
        assertTrue(result.containsKey("TOPBAR"));
        assertTrue(result.containsKey("PROFILE"));

        assertEquals(1, result.get("SIDEBAR").size());
        assertEquals("Dashboard", result.get("SIDEBAR").get(0).getName());

        assertEquals(1, result.get("TOPBAR").size());
        assertEquals("Support Chat", result.get("TOPBAR").get(0).getName());

        assertEquals(1, result.get("PROFILE").size());
        assertEquals("Logout", result.get("PROFILE").get(0).getName());
    }
}
