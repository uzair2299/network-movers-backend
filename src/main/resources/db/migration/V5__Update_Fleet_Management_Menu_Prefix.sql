-- =============================================================================
-- V5__Update_Fleet_Management_Menu_Prefix.sql
-- Align and sort all Fleet Management sub-menu paths according to operational flow
-- =============================================================================

DO $$
DECLARE
    parent_id_var BIGINT;
BEGIN
    -- Find the parent menu item ID for 'Fleet Management' (should be 501)
    SELECT id INTO parent_id_var FROM sec_menu_items WHERE name = 'Fleet Management' OR id = 501 LIMIT 1;
    
    IF parent_id_var IS NOT NULL THEN
        -- 1. Vehicle Types (sort_order = 10)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Vehicle Types' OR path LIKE '%/vehicle-types')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/vehicle-types', sort_order = 10, name = 'Vehicle Types', icon = 'truck'
            WHERE parent_id = parent_id_var AND (name = 'Vehicle Types' OR path LIKE '%/vehicle-types');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Vehicle Types', 'truck', '/resources/fleet/vehicle-types', 'SIDEBAR', parent_id_var, 10, TRUE);
        END IF;

        -- 2. Vehicle Makes (sort_order = 20)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Vehicle Makes' OR path LIKE '%/vehicle-makes')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/vehicle-makes', sort_order = 20, name = 'Vehicle Makes', icon = 'tag'
            WHERE parent_id = parent_id_var AND (name = 'Vehicle Makes' OR path LIKE '%/vehicle-makes');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Vehicle Makes', 'tag', '/resources/fleet/vehicle-makes', 'SIDEBAR', parent_id_var, 20, TRUE);
        END IF;

        -- 3. Vehicle Models (sort_order = 30)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Vehicle Models' OR path LIKE '%/vehicle-models')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/vehicle-models', sort_order = 30, name = 'Vehicle Models', icon = 'layers'
            WHERE parent_id = parent_id_var AND (name = 'Vehicle Models' OR path LIKE '%/vehicle-models');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Vehicle Models', 'layers', '/resources/fleet/vehicle-models', 'SIDEBAR', parent_id_var, 30, TRUE);
        END IF;

        -- 4. Vehicles (sort_order = 40)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Vehicles' OR path LIKE '%/vehicles')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/vehicles', sort_order = 40, name = 'Vehicles', icon = 'truck'
            WHERE parent_id = parent_id_var AND (name = 'Vehicles' OR path LIKE '%/vehicles');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Vehicles', 'truck', '/resources/fleet/vehicles', 'SIDEBAR', parent_id_var, 40, TRUE);
        END IF;

        -- 5. Vehicle Categories (sort_order = 50)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Vehicle Categories' OR path LIKE '%/categories')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/categories', sort_order = 50, name = 'Vehicle Categories', icon = 'layers'
            WHERE parent_id = parent_id_var AND (name = 'Vehicle Categories' OR path LIKE '%/categories');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Vehicle Categories', 'layers', '/resources/fleet/categories', 'SIDEBAR', parent_id_var, 50, TRUE);
        END IF;

        -- 6. Vehicle Insurance (sort_order = 60)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Vehicle Insurance' OR path LIKE '%/insurance')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/insurance', sort_order = 60, name = 'Vehicle Insurance', icon = 'shield'
            WHERE parent_id = parent_id_var AND (name = 'Vehicle Insurance' OR path LIKE '%/insurance');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Vehicle Insurance', 'shield', '/resources/fleet/insurance', 'SIDEBAR', parent_id_var, 60, TRUE);
        END IF;

        -- 7. Documents (sort_order = 70)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Documents' OR name = 'Vehicle Documents' OR path LIKE '%/documents')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/documents', sort_order = 70, name = 'Documents', icon = 'file'
            WHERE parent_id = parent_id_var AND (name = 'Documents' OR name = 'Vehicle Documents' OR path LIKE '%/documents');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Documents', 'file', '/resources/fleet/documents', 'SIDEBAR', parent_id_var, 70, TRUE);
        END IF;

        -- 8. Fuel Logs (sort_order = 80)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Fuel Logs' OR path LIKE '%/fuel-logs' OR path LIKE '%/fuel')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/fuel-logs', sort_order = 80, name = 'Fuel Logs', icon = 'droplet'
            WHERE parent_id = parent_id_var AND (name = 'Fuel Logs' OR path LIKE '%/fuel-logs' OR path LIKE '%/fuel');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Fuel Logs', 'droplet', '/resources/fleet/fuel-logs', 'SIDEBAR', parent_id_var, 80, TRUE);
        END IF;

        -- 9. Inspections (sort_order = 90)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Inspections' OR path LIKE '%/inspections')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/inspections', sort_order = 90, name = 'Inspections', icon = 'check-square'
            WHERE parent_id = parent_id_var AND (name = 'Inspections' OR path LIKE '%/inspections');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Inspections', 'check-square', '/resources/fleet/inspections', 'SIDEBAR', parent_id_var, 90, TRUE);
        END IF;

        -- 10. Maintenance (sort_order = 100)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Maintenance' OR path LIKE '%/maintenance')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/maintenance', sort_order = 100, name = 'Maintenance', icon = 'tool'
            WHERE parent_id = parent_id_var AND (name = 'Maintenance' OR path LIKE '%/maintenance');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Maintenance', 'tool', '/resources/fleet/maintenance', 'SIDEBAR', parent_id_var, 100, TRUE);
        END IF;

        -- 11. Assignments (sort_order = 110)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Assignments' OR path LIKE '%/assignments')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/assignments', sort_order = 110, name = 'Assignments', icon = 'user-check'
            WHERE parent_id = parent_id_var AND (name = 'Assignments' OR path LIKE '%/assignments');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Assignments', 'user-check', '/resources/fleet/assignments', 'SIDEBAR', parent_id_var, 110, TRUE);
        END IF;

        -- 12. Incidents (sort_order = 120)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Incidents' OR path LIKE '%/incidents')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/incidents', sort_order = 120, name = 'Incidents', icon = 'alert-triangle'
            WHERE parent_id = parent_id_var AND (name = 'Incidents' OR path LIKE '%/incidents');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Incidents', 'alert-triangle', '/resources/fleet/incidents', 'SIDEBAR', parent_id_var, 120, TRUE);
        END IF;

        -- 13. Tracking (sort_order = 130)
        IF EXISTS (SELECT 1 FROM sec_menu_items WHERE parent_id = parent_id_var AND (name = 'Tracking' OR name = 'Vehicle Tracking' OR path LIKE '%/tracking')) THEN
            UPDATE sec_menu_items 
            SET path = '/resources/fleet/tracking', sort_order = 130, name = 'Tracking', icon = 'map-pin'
            WHERE parent_id = parent_id_var AND (name = 'Tracking' OR name = 'Vehicle Tracking' OR path LIKE '%/tracking');
        ELSE
            INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, active) 
            VALUES ('Tracking', 'map-pin', '/resources/fleet/tracking', 'SIDEBAR', parent_id_var, 130, TRUE);
        END IF;
        
    END IF;
END $$;
