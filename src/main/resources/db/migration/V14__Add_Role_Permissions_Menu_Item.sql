-- V14: Add Role Permissions menu item under RBAC
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, active)
VALUES (1156, 'Role Permissions', 'link', '/system/rbac/role-permissions', 'SIDEBAR', 1115, 45, TRUE);
