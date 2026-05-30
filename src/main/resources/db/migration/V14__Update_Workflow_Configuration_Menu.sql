-- =============================================================================
-- V14__Update_Workflow_Configuration_Menu.sql
-- Remove Move Phases/Statuses and rename Workflow Configuration
-- =============================================================================

-- Remove Move Phases and Move Statuses
DELETE FROM sec_menu_items WHERE id IN (1135, 1136);

-- Update Workflow Configuration to Move State Management
UPDATE sec_menu_items SET name = 'Move State Management' WHERE id = 1103;
