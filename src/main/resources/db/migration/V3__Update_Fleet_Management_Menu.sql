-- =============================================================================
-- V3__Update_Fleet_Management_Menu.sql
-- Update or insert Fleet Management sub-menu items
-- =============================================================================

DO $$
DECLARE
    parent_id_var BIGINT;
BEGIN
    -- Find the parent menu item ID for 'Fleet Management'
    SELECT id INTO parent_id_var FROM sec_menu_items WHERE name = 'Fleet Management' OR id = 501 LIMIT 1;
    
    IF parent_id_var IS NOT NULL THEN
        -- 1. Vehicles (/fleet/vehicles)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Vehicles' OR path = '/resources/fleet/vehicles' OR path = '/fleet/vehicles')) THEN
            UPDATE sec_menu_items 
            SET path = '/fleet/vehicles', name = 'Vehicles' 
            WHERE parent_id = parent_id_var AND (name = 'Vehicles' OR path = '/resources/fleet/vehicles' OR path = '/fleet/vehicles');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Vehicles', 'truck', '/fleet/vehicles', 'SIDEBAR', parent_id_var, 10, TRUE);
        END IF;

        -- 2. Maintenance (/fleet/maintenance)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Maintenance' OR path = '/resources/fleet/maintenance' OR path = '/fleet/maintenance')) THEN
            UPDATE sec_menu_items 
            SET path = '/fleet/maintenance', name = 'Maintenance' 
            WHERE parent_id = parent_id_var AND (name = 'Maintenance' OR path = '/resources/fleet/maintenance' OR path = '/fleet/maintenance');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Maintenance', 'tool', '/fleet/maintenance', 'SIDEBAR', parent_id_var, 20, TRUE);
        END IF;

        -- 3. Inspections (/fleet/inspections)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Inspections' OR path = '/fleet/inspections')) THEN
            UPDATE sec_menu_items 
            SET path = '/fleet/inspections', name = 'Inspections' 
            WHERE parent_id = parent_id_var AND (name = 'Inspections' OR path = '/fleet/inspections');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Inspections', 'check-square', '/fleet/inspections', 'SIDEBAR', parent_id_var, 30, TRUE);
        END IF;

        -- 4. Assignments (/fleet/assignments)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Assignments' OR path = '/fleet/assignments')) THEN
            UPDATE sec_menu_items 
            SET path = '/fleet/assignments', name = 'Assignments' 
            WHERE parent_id = parent_id_var AND (name = 'Assignments' OR path = '/fleet/assignments');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Assignments', 'user-check', '/fleet/assignments', 'SIDEBAR', parent_id_var, 40, TRUE);
        END IF;

        -- 5. Fuel Logs (/fleet/fuel-logs)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Fuel Logs' OR path = '/resources/fleet/fuel' OR path = '/fleet/fuel-logs')) THEN
            UPDATE sec_menu_items 
            SET path = '/fleet/fuel-logs', name = 'Fuel Logs' 
            WHERE parent_id = parent_id_var AND (name = 'Fuel Logs' OR path = '/resources/fleet/fuel' OR path = '/fleet/fuel-logs');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Fuel Logs', 'droplet', '/fleet/fuel-logs', 'SIDEBAR', parent_id_var, 50, TRUE);
        END IF;

        -- 6. Incidents (/fleet/incidents)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Incidents' OR path = '/fleet/incidents')) THEN
            UPDATE sec_menu_items 
            SET path = '/fleet/incidents', name = 'Incidents' 
            WHERE parent_id = parent_id_var AND (name = 'Incidents' OR path = '/fleet/incidents');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Incidents', 'alert-triangle', '/fleet/incidents', 'SIDEBAR', parent_id_var, 60, TRUE);
        END IF;

        -- 7. Documents (/fleet/documents)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Vehicle Documents' OR name = 'Documents' OR path = '/resources/fleet/documents' OR path = '/fleet/documents')) THEN
            UPDATE sec_menu_items 
            SET path = '/fleet/documents', name = 'Documents' 
            WHERE parent_id = parent_id_var AND (name = 'Vehicle Documents' OR name = 'Documents' OR path = '/resources/fleet/documents' OR path = '/fleet/documents');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Documents', 'file', '/fleet/documents', 'SIDEBAR', parent_id_var, 70, TRUE);
        END IF;

        -- 8. Tracking (/fleet/tracking)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Vehicle Tracking' OR name = 'Tracking' OR path = '/fleet/tracking')) THEN
            UPDATE sec_menu_items 
            SET path = '/fleet/tracking', name = 'Tracking' 
            WHERE parent_id = parent_id_var AND (name = 'Vehicle Tracking' OR name = 'Tracking' OR path = '/fleet/tracking');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Tracking', 'map-pin', '/fleet/tracking', 'SIDEBAR', parent_id_var, 80, TRUE);
        END IF;
        
    END IF;
END $$;
