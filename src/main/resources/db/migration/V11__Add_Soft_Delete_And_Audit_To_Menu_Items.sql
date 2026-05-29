-- =============================================================================
-- V11__Add_Soft_Delete_And_Audit_To_Menu_Items.sql
-- Add soft-delete and audit tracking columns to sec_menu_items table
-- =============================================================================

ALTER TABLE sec_menu_items ADD COLUMN IF NOT EXISTS deleted BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE sec_menu_items ADD COLUMN IF NOT EXISTS deleted_at TIMESTAMP;
ALTER TABLE sec_menu_items ADD COLUMN IF NOT EXISTS deleted_by BIGINT;
ALTER TABLE sec_menu_items ADD COLUMN IF NOT EXISTS created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP;
ALTER TABLE sec_menu_items ADD COLUMN IF NOT EXISTS created_by BIGINT;
ALTER TABLE sec_menu_items ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP;
ALTER TABLE sec_menu_items ADD COLUMN IF NOT EXISTS updated_by BIGINT;
ALTER TABLE sec_menu_items ADD COLUMN IF NOT EXISTS version BIGINT NOT NULL DEFAULT 0;
