-- V8: Add RBAC Menu Items under Administration
-- First, delete old/duplicate menu items that conflict or are being replaced
DELETE FROM sec_menu_items WHERE id IN (
    1112,  -- 'Roles Management' under old User Management (replaced by RBAC)
    1113   -- 'Permissions' under old User Management (replaced by RBAC)
);

-- Insert RBAC parent menu item under Administration (id=11)
-- Using 2000+ ID range to avoid conflicts with V2 seeds (which use up to ~1200)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, active)
VALUES (2001, 'RBAC', 'shield', '/system/rbac', 'SIDEBAR', 11, 25, TRUE);

-- Insert RBAC child items under RBAC parent (id=2001)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, active) VALUES
(2011, 'Modules',          'grid',  '/system/rbac/modules',           'SIDEBAR', 2001, 10, TRUE),
(2012, 'Resources',        'cpu',   '/system/rbac/resources',         'SIDEBAR', 2001, 20, TRUE),
(2013, 'Permissions',      'key',   '/system/rbac/permissions',       'SIDEBAR', 2001, 30, TRUE),
(2014, 'Roles',            'shield','/system/rbac/roles',             'SIDEBAR', 2001, 40, TRUE),
(2015, 'Role Permissions', 'link',  '/system/rbac/role-permissions',  'SIDEBAR', 2001, 45, TRUE),
(2016, 'User Roles',       'users', '/system/rbac/user-roles',        'SIDEBAR', 2001, 50, TRUE);
