-- V15: Fix RBAC menu items - remove conflicting entries seeded by V2, insert correct items
-- V8 already inserted IDs 1115, 1151-1155, but these conflict with V2 Document/Integration seeds.
-- This migration removes both the V8-inserted items AND the conflicting V2 items,
-- then re-inserts the RBAC menu under safe 2000+ IDs.

-- -----------------------------------------------------------------------
-- 1. Remove RBAC items that V8 may have inserted using conflicting IDs
-- -----------------------------------------------------------------------
DELETE FROM sec_menu_items WHERE id IN (
    1155,  -- 'User Roles' inserted by V8 (may conflict)
    1154,  -- 'Roles' inserted by V8 (may conflict)
    1153,  -- 'Permissions' inserted by V8 — conflicts with V2 'Storage Settings' (id=1153)
    1152,  -- 'Resources' inserted by V8 — conflicts with V2 'Templates' (id=1152)
    1151,  -- 'Modules' inserted by V8 — conflicts with V2 'Document Types Management' (id=1151)
    1115   -- 'RBAC' parent inserted by V8
);

-- -----------------------------------------------------------------------
-- 2. Re-insert RBAC parent and all children under safe 2000+ IDs
-- -----------------------------------------------------------------------
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, active)
VALUES (2001, 'RBAC', 'shield', '/system/rbac', 'SIDEBAR', 11, 25, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, active) VALUES
(2011, 'Modules',          'grid',   '/system/rbac/modules',          'SIDEBAR', 2001, 10, TRUE),
(2012, 'Resources',        'cpu',    '/system/rbac/resources',        'SIDEBAR', 2001, 20, TRUE),
(2013, 'Permissions',      'key',    '/system/rbac/permissions',      'SIDEBAR', 2001, 30, TRUE),
(2014, 'Roles',            'shield', '/system/rbac/roles',            'SIDEBAR', 2001, 40, TRUE),
(2015, 'Role Permissions', 'link',   '/system/rbac/role-permissions', 'SIDEBAR', 2001, 45, TRUE),
(2016, 'User Roles',       'users',  '/system/rbac/user-roles',       'SIDEBAR', 2001, 50, TRUE)
ON CONFLICT (id) DO NOTHING;
