-- =============================================================================
-- V16__Update_Workflow_Configuration_Path.sql
-- Update Move State Management menu item path
-- =============================================================================

-- Update path from /admin/workflows to /system/move-states for Workflow Configuration / Move State Management (id: 1103)
UPDATE sec_menu_items SET path = '/system/move-states' WHERE id = 1103;
