package com.company.networkmovers.modules.navigation.service;

import com.company.networkmovers.modules.navigation.dto.request.MenuItemRequest;
import com.company.networkmovers.modules.navigation.dto.response.MenuItemResponse;
import com.company.networkmovers.modules.navigation.entity.MenuItem;
import com.company.networkmovers.modules.navigation.exception.NavigationException;
import com.company.networkmovers.modules.navigation.repository.MenuItemRepository;
import com.company.networkmovers.security.rbac.RolePermissionRepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NavigationServiceTest {

    @Mock
    private MenuItemRepository menuItemRepository;

    @Mock
    private RolePermissionRepository rolePermissionRepository;

    @Mock
    private EntityManager entityManager;

    private NavigationService navigationService;

    @BeforeEach
    void setUp() {
        navigationService = new NavigationService(menuItemRepository, rolePermissionRepository, entityManager);
    }

    // =========================================================================
    // User-facing navigation tree tests
    // =========================================================================

    @Test
    @DisplayName("getNavigationMenuForUser - returns correctly structured tree")
    void testGetNavigationMenuForUser_Success() {
        UserDetails userDetails = new User("admin", "password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));

        List<String> roles = Collections.singletonList("ROLE_ADMIN");
        List<String> permissions = Arrays.asList("CRM_READ", "HR_READ");

        when(rolePermissionRepository.findPermissionNamesByRoleNames(roles)).thenReturn(permissions);

        MenuItem crmModule = MenuItem.builder()
                .name("CRM").icon("crm-icon").path("/crm")
                .section("SIDEBAR").parent(null).sortOrder(20).active(true).build();
        setId(crmModule, 1L);

        MenuItem leadsMenu = MenuItem.builder()
                .name("Leads").icon("leads-icon").path("/crm/leads")
                .section("SIDEBAR").parent(crmModule).sortOrder(10).active(true).build();
        setId(leadsMenu, 2L);

        MenuItem allLeadsSubmenu = MenuItem.builder()
                .name("All Leads").icon("all-leads-icon").path("/crm/leads/all")
                .section("SIDEBAR").parent(leadsMenu).sortOrder(10).active(true).build();
        setId(allLeadsSubmenu, 3L);

        List<String> expectedAuthorities = Arrays.asList("ROLE_ADMIN", "CRM_READ", "HR_READ");
        when(menuItemRepository.findAllActiveBySectionAndPermissions("SIDEBAR", expectedAuthorities))
                .thenReturn(Arrays.asList(crmModule, leadsMenu, allLeadsSubmenu));

        List<MenuItemResponse> rootMenu = navigationService.getNavigationMenuForUser("SIDEBAR", userDetails);

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
    @DisplayName("getAllNavigationMenusForUser - groups items by section")
    void testGetAllNavigationMenusForUser_GroupedSections() {
        UserDetails userDetails = new User("customer", "password",
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_CUSTOMER")));

        List<String> roles = Collections.singletonList("ROLE_CUSTOMER");
        when(rolePermissionRepository.findPermissionNamesByRoleNames(roles)).thenReturn(Collections.emptyList());

        MenuItem sidebarItem = MenuItem.builder().name("Dashboard").section("SIDEBAR").sortOrder(10).active(true).build();
        setId(sidebarItem, 10L);
        MenuItem topbarItem = MenuItem.builder().name("Support Chat").section("TOPBAR").sortOrder(10).active(true).build();
        setId(topbarItem, 20L);
        MenuItem profileItem = MenuItem.builder().name("Logout").section("PROFILE").sortOrder(10).active(true).build();
        setId(profileItem, 30L);

        List<String> expectedAuthorities = Collections.singletonList("ROLE_CUSTOMER");
        when(menuItemRepository.findAllActiveByPermissions(expectedAuthorities))
                .thenReturn(Arrays.asList(sidebarItem, topbarItem, profileItem));

        Map<String, List<MenuItemResponse>> result = navigationService.getAllNavigationMenusForUser(userDetails);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(1, result.get("SIDEBAR").size());
        assertEquals("Dashboard", result.get("SIDEBAR").get(0).getName());
        assertEquals(1, result.get("TOPBAR").size());
        assertEquals("Support Chat", result.get("TOPBAR").get(0).getName());
        assertEquals(1, result.get("PROFILE").size());
        assertEquals("Logout", result.get("PROFILE").get(0).getName());
    }

    // =========================================================================
    // Admin CRUD tests
    // =========================================================================

    @Test
    @DisplayName("getAllItems - returns flat list of non-deleted items")
    void testGetAllItems_ReturnsFlatList() {
        MenuItem item1 = MenuItem.builder().name("Dashboard").section("SIDEBAR").sortOrder(10).active(true).build();
        setId(item1, 1L);
        MenuItem item2 = MenuItem.builder().name("CRM").section("SIDEBAR").sortOrder(20).active(true).build();
        setId(item2, 2L);

        when(menuItemRepository.findAllByDeletedFalseOrderBySortOrderAsc())
                .thenReturn(Arrays.asList(item1, item2));

        List<MenuItemResponse> result = navigationService.getAllItems();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Dashboard", result.get(0).getName());
        assertEquals("CRM", result.get(1).getName());
    }

    @Test
    @DisplayName("getItemById - returns item when it exists and is not deleted")
    void testGetItemById_Success() {
        MenuItem item = MenuItem.builder().name("Dashboard").section("SIDEBAR").sortOrder(10).active(true).build();
        setId(item, 1L);

        when(menuItemRepository.findById(1L)).thenReturn(Optional.of(item));

        MenuItemResponse result = navigationService.getItemById(1L);

        assertNotNull(result);
        assertEquals("Dashboard", result.getName());
        assertEquals(1L, result.getId());
    }

    @Test
    @DisplayName("getItemById - throws NavigationException when item not found")
    void testGetItemById_NotFound_ThrowsException() {
        when(menuItemRepository.findById(99L)).thenReturn(Optional.empty());

        NavigationException ex = assertThrows(NavigationException.class,
                () -> navigationService.getItemById(99L));

        assertTrue(ex.getMessage().contains("99"));
    }

    @Test
    @DisplayName("createMenuItem - saves and returns new item without parent")
    void testCreateMenuItem_WithoutParent_Success() {
        MenuItemRequest request = MenuItemRequest.builder()
                .name("Dashboard").section("SIDEBAR").sortOrder(10).active(true).build();

        MenuItem saved = MenuItem.builder().name("Dashboard").section("SIDEBAR").sortOrder(10).active(true).build();
        setId(saved, 1L);

        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(saved);

        MenuItemResponse result = navigationService.createMenuItem(request);

        assertNotNull(result);
        assertEquals("Dashboard", result.getName());
        assertEquals("SIDEBAR", result.getSection());
        verify(menuItemRepository, times(1)).save(any(MenuItem.class));
    }

    @Test
    @DisplayName("createMenuItem - throws NavigationException for invalid section")
    void testCreateMenuItem_InvalidSection_ThrowsException() {
        MenuItemRequest request = MenuItemRequest.builder()
                .name("Dashboard").section("INVALID").sortOrder(10).active(true).build();

        NavigationException ex = assertThrows(NavigationException.class,
                () -> navigationService.createMenuItem(request));

        assertTrue(ex.getMessage().contains("INVALID"));
    }

    @Test
    @DisplayName("updateMenuItem - throws NavigationException when item is its own parent")
    void testUpdateMenuItem_SelfParent_ThrowsException() {
        MenuItem existing = MenuItem.builder().name("CRM").section("SIDEBAR").sortOrder(20).active(true).build();
        setId(existing, 5L);

        when(menuItemRepository.findById(5L)).thenReturn(Optional.of(existing));

        MenuItemRequest request = MenuItemRequest.builder()
                .name("CRM").section("SIDEBAR").sortOrder(20).active(true)
                .parentId(5L)   // pointing to itself
                .build();

        NavigationException ex = assertThrows(NavigationException.class,
                () -> navigationService.updateMenuItem(5L, request));

        assertTrue(ex.getMessage().contains("own parent"));
    }

    // =========================================================================
    // Soft delete tests — the KEY constraint
    // =========================================================================

    @Test
    @DisplayName("softDeleteMenuItem - succeeds when item has NO active children")
    void testSoftDeleteMenuItem_NoChildren_Success() {
        MenuItem item = MenuItem.builder().name("Leads").section("SIDEBAR").sortOrder(10).active(true).build();
        setId(item, 10L);

        when(menuItemRepository.findById(10L)).thenReturn(Optional.of(item));
        when(menuItemRepository.existsByParentIdAndDeletedFalse(10L)).thenReturn(false);
        when(menuItemRepository.save(any(MenuItem.class))).thenReturn(item);

        assertDoesNotThrow(() -> navigationService.softDeleteMenuItem(10L));

        verify(menuItemRepository, times(1)).save(any(MenuItem.class));
        assertTrue(item.isDeleted());
        assertFalse(item.isActive());
    }

    @Test
    @DisplayName("softDeleteMenuItem - throws NavigationException when item HAS active children")
    void testSoftDeleteMenuItem_HasChildren_ThrowsException() {
        MenuItem parentItem = MenuItem.builder().name("CRM").section("SIDEBAR").sortOrder(20).active(true).build();
        setId(parentItem, 2L);

        when(menuItemRepository.findById(2L)).thenReturn(Optional.of(parentItem));
        // Simulates that children exist
        when(menuItemRepository.existsByParentIdAndDeletedFalse(2L)).thenReturn(true);

        NavigationException ex = assertThrows(NavigationException.class,
                () -> navigationService.softDeleteMenuItem(2L));

        assertTrue(ex.getMessage().contains("active child menu items"));
        // Must NOT save (no deletion performed)
        verify(menuItemRepository, never()).save(any());
    }

    @Test
    @DisplayName("softDeleteMenuItem - throws NavigationException when item is already deleted")
    void testSoftDeleteMenuItem_AlreadyDeleted_ThrowsException() {
        MenuItem deletedItem = MenuItem.builder().name("Old Item").section("SIDEBAR").sortOrder(5).active(false).build();
        setId(deletedItem, 99L);
        deletedItem.delete(1L);  // mark as deleted

        when(menuItemRepository.findById(99L)).thenReturn(Optional.of(deletedItem));

        NavigationException ex = assertThrows(NavigationException.class,
                () -> navigationService.softDeleteMenuItem(99L));

        assertTrue(ex.getMessage().contains("99"));
    }

    // =========================================================================
    // Helper: reflectively set ID since BaseEntity uses a private Long id
    // =========================================================================
    private void setId(MenuItem item, Long id) {
        try {
            // BaseEntity has 'id' field
            var field = item.getClass().getSuperclass()
                           .getSuperclass()
                           .getSuperclass()
                           .getDeclaredField("id");
            field.setAccessible(true);
            field.set(item, id);
        } catch (Exception e) {
            throw new RuntimeException("Could not set id on MenuItem for test", e);
        }
    }
}
