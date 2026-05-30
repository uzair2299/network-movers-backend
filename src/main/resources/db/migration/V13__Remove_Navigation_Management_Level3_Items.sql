-- =============================================================================
-- V13__Remove_Navigation_Management_Level3_Items.sql
-- Remove Level 3 children under Navigation Management (id=1110).
-- Keep only the Level 2 entry as originally intended.
-- =============================================================================

DELETE FROM sec_menu_items WHERE id IN (1171, 1172, 1173, 1174);

-- Synchronize the sequence
SELECT setval('sec_menu_items_id_seq', (SELECT MAX(id) FROM sec_menu_items));
