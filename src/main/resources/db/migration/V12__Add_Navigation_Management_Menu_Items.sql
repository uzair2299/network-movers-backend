-- =============================================================================
-- V12__Add_Navigation_Management_Menu_Items.sql
-- Add Navigation Management as a self-managed menu section under Administration
-- so that administrators can manage the menu structure from within the app itself.
-- =============================================================================

-- Level 2: Navigation Management under Administration (parent_id = 11)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1110, 'Navigation Management', 'menu', '/admin/navigation', 'SIDEBAR', 11, 100, NULL, TRUE);

-- Level 3: Children under Navigation Management (parent_id = 1110)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1171, 'Menu Items',    'list',     '/admin/navigation/items',    'SIDEBAR', 1110, 10, NULL, TRUE),
(1172, 'Sidebar Menus', 'sidebar',  '/admin/navigation/sidebar',  'SIDEBAR', 1110, 20, NULL, TRUE),
(1173, 'Topbar Menus',  'bar-chart-2', '/admin/navigation/topbar', 'SIDEBAR', 1110, 30, NULL, TRUE),
(1174, 'Profile Menus', 'user',     '/admin/navigation/profile',  'SIDEBAR', 1110, 40, NULL, TRUE);

-- Synchronize the sequence
SELECT setval('sec_menu_items_id_seq', (SELECT MAX(id) FROM sec_menu_items));
