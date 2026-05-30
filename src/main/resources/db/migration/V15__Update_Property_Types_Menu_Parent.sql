-- =============================================================================
-- V15__Update_Property_Types_Menu_Parent.sql
-- Update Property Types menu item parent
-- =============================================================================

-- Update parent from 4 to 11 for Property Types (id: 405)
UPDATE sec_menu_items SET parent_id = 11 WHERE id = 405;
