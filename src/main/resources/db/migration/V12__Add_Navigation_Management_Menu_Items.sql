-- =============================================================================
-- V12__Add_Navigation_Management_Menu_Items.sql
-- Add Navigation Management as a Level 2 entry under Administration (id = 11)
-- =============================================================================

-- Level 2: Navigation Management under Administration (parent_id = 11)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1110, 'Navigation Management', 'menu', '/admin/navigation', 'SIDEBAR', 11, 100, NULL, TRUE);

-- Synchronize the sequence
SELECT setval('sec_menu_items_id_seq', (SELECT MAX(id) FROM sec_menu_items));
