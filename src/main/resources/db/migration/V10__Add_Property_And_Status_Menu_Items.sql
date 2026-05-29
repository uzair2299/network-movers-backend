-- =============================================================================
-- V10__Add_Property_And_Status_Menu_Items.sql
-- Add menu items for property masters and move phases/statuses
-- =============================================================================

-- Add Level 3 Menu Items for Property Masters under Property Types (id: 405)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(451, 'Property Categories', 'grid', '/services/property-types/categories', 'SIDEBAR', 405, 10, NULL, TRUE),
(452, 'Property Sizes', 'maximize', '/services/property-types/sizes', 'SIDEBAR', 405, 20, NULL, TRUE),
(453, 'Floor Types', 'layers', '/services/property-types/floor-types', 'SIDEBAR', 405, 30, NULL, TRUE),
(454, 'Building Access', 'door-open', '/services/property-types/building-access', 'SIDEBAR', 405, 40, NULL, TRUE),
(455, 'Parking Access', 'square', '/services/property-types/parking-access', 'SIDEBAR', 405, 50, NULL, TRUE),
(456, 'Access Restrictions', 'alert-octagon', '/services/property-types/restrictions', 'SIDEBAR', 405, 60, NULL, TRUE);

-- Add Level 3 Menu Items for Move Workflow under Workflow Configuration (id: 1103)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1135, 'Move Phases', 'git-commit', '/admin/workflows/move-phases', 'SIDEBAR', 1103, 50, NULL, TRUE),
(1136, 'Move Statuses', 'activity', '/admin/workflows/move-statuses', 'SIDEBAR', 1103, 60, NULL, TRUE);

-- Final sequence synchronization
SELECT setval('sec_menu_items_id_seq', (SELECT MAX(id) FROM sec_menu_items));
