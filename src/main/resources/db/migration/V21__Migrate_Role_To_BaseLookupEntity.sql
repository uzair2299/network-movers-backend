-- =============================================================================
-- V21__Migrate_Role_To_BaseLookupEntity.sql
-- Safely convert sec_roles.id from BIGINT to UUID, handling foreign keys
-- =============================================================================

-- 1. Drop existing constraints
ALTER TABLE sec_user_roles DROP CONSTRAINT IF EXISTS fk_ur_role;
ALTER TABLE sec_user_roles DROP CONSTRAINT IF EXISTS uq_user_role;
ALTER TABLE sec_role_permissions DROP CONSTRAINT IF EXISTS fk_rp_role;
ALTER TABLE sec_role_permissions DROP CONSTRAINT IF EXISTS uq_role_permission;
ALTER TABLE sec_roles DROP CONSTRAINT IF EXISTS sec_roles_pkey CASCADE;

-- 2. Add new UUID columns
ALTER TABLE sec_roles ADD COLUMN new_id UUID DEFAULT gen_random_uuid();
ALTER TABLE sec_user_roles ADD COLUMN new_role_id UUID;
ALTER TABLE sec_role_permissions ADD COLUMN new_role_id UUID;

-- 3. Map old BIGINT IDs to new UUIDs
UPDATE sec_user_roles ur SET new_role_id = (SELECT new_id FROM sec_roles sr WHERE sr.id = ur.role_id);
UPDATE sec_role_permissions rp SET new_role_id = (SELECT new_id FROM sec_roles sr WHERE sr.id = rp.role_id);

-- 4. Swap columns in sec_roles
ALTER TABLE sec_roles DROP COLUMN id;
ALTER TABLE sec_roles RENAME COLUMN new_id TO id;
ALTER TABLE sec_roles ADD PRIMARY KEY (id);

-- 5. Swap columns in sec_user_roles
ALTER TABLE sec_user_roles DROP COLUMN role_id;
ALTER TABLE sec_user_roles RENAME COLUMN new_role_id TO role_id;
ALTER TABLE sec_user_roles ALTER COLUMN role_id SET NOT NULL;

-- 6. Swap columns in sec_role_permissions
ALTER TABLE sec_role_permissions DROP COLUMN role_id;
ALTER TABLE sec_role_permissions RENAME COLUMN new_role_id TO role_id;
ALTER TABLE sec_role_permissions ALTER COLUMN role_id SET NOT NULL;

-- 7. Recreate constraints
ALTER TABLE sec_user_roles ADD CONSTRAINT fk_ur_role FOREIGN KEY (role_id) REFERENCES sec_roles(id);
ALTER TABLE sec_user_roles ADD CONSTRAINT uq_user_role UNIQUE (user_id, role_id);

ALTER TABLE sec_role_permissions ADD CONSTRAINT fk_rp_role FOREIGN KEY (role_id) REFERENCES sec_roles(id);
ALTER TABLE sec_role_permissions ADD CONSTRAINT uq_role_permission UNIQUE (role_id, permission_id);

-- 8. Add BaseLookupEntity required columns (code, active)
ALTER TABLE sec_roles ADD COLUMN code VARCHAR(255);
UPDATE sec_roles SET code = UPPER(REPLACE(name, ' ', '_'));
ALTER TABLE sec_roles ALTER COLUMN code SET NOT NULL;
ALTER TABLE sec_roles ADD CONSTRAINT uq_sec_roles_code UNIQUE (code);

ALTER TABLE sec_roles ADD COLUMN active BOOLEAN DEFAULT true NOT NULL;
