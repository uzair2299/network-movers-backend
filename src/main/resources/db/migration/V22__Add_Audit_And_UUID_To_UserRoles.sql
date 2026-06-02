-- =============================================================================
-- V22__Add_Audit_And_UUID_To_UserRoles.sql
-- Convert UserRole to use UUID primary key and add auditing columns
-- =============================================================================

-- 1. Drop existing BIGINT primary key
ALTER TABLE sec_user_roles DROP CONSTRAINT IF EXISTS sec_user_roles_pkey CASCADE;
ALTER TABLE sec_user_roles DROP COLUMN IF EXISTS id;

-- 2. Add new UUID primary key
ALTER TABLE sec_user_roles ADD COLUMN id UUID PRIMARY KEY DEFAULT gen_random_uuid();

-- 3. Add auditing columns
ALTER TABLE sec_user_roles ADD COLUMN IF NOT EXISTS version BIGINT DEFAULT 0;
ALTER TABLE sec_user_roles ADD COLUMN IF NOT EXISTS created_at TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP NOT NULL;
ALTER TABLE sec_user_roles ADD COLUMN IF NOT EXISTS created_by BIGINT;
ALTER TABLE sec_user_roles ADD COLUMN IF NOT EXISTS updated_at TIMESTAMP WITH TIME ZONE;
ALTER TABLE sec_user_roles ADD COLUMN IF NOT EXISTS updated_by BIGINT;
