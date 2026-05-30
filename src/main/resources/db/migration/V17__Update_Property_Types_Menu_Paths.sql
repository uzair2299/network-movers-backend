-- =============================================================================
-- V17__Update_Property_Types_Menu_Paths.sql
-- Update paths for Property Types and its children to use /system prefix
-- =============================================================================

-- Update parent Property Types
UPDATE sec_menu_items SET path = '/system/property-types' WHERE id = 405;

-- Update children Property Masters
UPDATE sec_menu_items SET path = '/system/property-types/categories' WHERE id = 451;
UPDATE sec_menu_items SET path = '/system/property-types/sizes' WHERE id = 452;
UPDATE sec_menu_items SET path = '/system/property-types/floor-types' WHERE id = 453;
UPDATE sec_menu_items SET path = '/system/property-types/building-access' WHERE id = 454;
UPDATE sec_menu_items SET path = '/system/property-types/parking-access' WHERE id = 455;
UPDATE sec_menu_items SET path = '/system/property-types/restrictions' WHERE id = 456;
