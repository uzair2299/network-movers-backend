-- =============================================================================
-- V4__Add_Vehicle_Types_And_Makes_Menu.sql
-- Insert or update Vehicle Types and Vehicle Makes sub-menus under Fleet Management
-- =============================================================================

DO $$
DECLARE
    parent_id_var BIGINT;
BEGIN
    -- Find the parent menu item ID for 'Fleet Management'
    SELECT id INTO parent_id_var FROM sec_menu_items WHERE name = 'Fleet Management' OR id = 501 LIMIT 1;
    
    IF parent_id_var IS NOT NULL THEN
        -- 1. Vehicle Types (/fleet/vehicle-types)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Vehicle Types' OR path = '/fleet/vehicle-types')) THEN
            UPDATE sec_menu_items 
            SET path = '/fleet/vehicle-types', name = 'Vehicle Types' 
            WHERE parent_id = parent_id_var AND (name = 'Vehicle Types' OR path = '/fleet/vehicle-types');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Vehicle Types', 'truck', '/fleet/vehicle-types', 'SIDEBAR', parent_id_var, 110, TRUE);
        END IF;

        -- 2. Vehicle Makes (/fleet/vehicle-makes)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Vehicle Makes' OR path = '/fleet/vehicle-makes')) THEN
            UPDATE sec_menu_items 
            SET path = '/fleet/vehicle-makes', name = 'Vehicle Makes' 
            WHERE parent_id = parent_id_var AND (name = 'Vehicle Makes' OR path = '/fleet/vehicle-makes');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Vehicle Makes', 'tag', '/fleet/vehicle-makes', 'SIDEBAR', parent_id_var, 120, TRUE);
        END IF;
    END IF;
END $$;
