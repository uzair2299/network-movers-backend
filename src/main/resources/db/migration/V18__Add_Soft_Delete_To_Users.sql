-- =============================================================================
-- V18__Add_Soft_Delete_To_Users.sql
-- Add soft delete fields to tbl_users and tbl_user_profiles
-- =============================================================================

ALTER TABLE tbl_users 
ADD COLUMN IF NOT EXISTS deleted BOOLEAN NOT NULL DEFAULT FALSE,
ADD COLUMN IF NOT EXISTS deleted_at TIMESTAMP,
ADD COLUMN IF NOT EXISTS deleted_by BIGINT;

ALTER TABLE tbl_user_profiles 
ADD COLUMN IF NOT EXISTS deleted BOOLEAN NOT NULL DEFAULT FALSE,
ADD COLUMN IF NOT EXISTS deleted_at TIMESTAMP,
ADD COLUMN IF NOT EXISTS deleted_by BIGINT;
