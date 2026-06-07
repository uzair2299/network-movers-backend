-- Remove old role and permission menu items under User Management to avoid duplication
DELETE FROM sec_menu_items WHERE id IN (1112, 1113);

-- Insert RBAC parent menu item under Administration (id=11)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, active) 
VALUES (1115, 'RBAC', 'shield', '/system/rbac', 'SIDEBAR', 11, 25, TRUE);

-- Insert child items under RBAC (id=1115)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, active) VALUES
(1151, 'Modules', 'grid', '/system/rbac/modules', 'SIDEBAR', 1115, 10, TRUE),
(1152, 'Resources', 'cpu', '/system/rbac/resources', 'SIDEBAR', 1115, 20, TRUE),
(1153, 'Permissions', 'key', '/system/rbac/permissions', 'SIDEBAR', 1115, 30, TRUE),
(1154, 'Roles', 'shield', '/system/rbac/roles', 'SIDEBAR', 1115, 40, TRUE),
(1155, 'User Roles', 'users', '/system/rbac/user-roles', 'SIDEBAR', 1115, 50, TRUE);
