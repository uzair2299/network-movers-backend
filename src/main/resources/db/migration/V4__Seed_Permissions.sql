-- =============================================================================
-- V4__Seed_Permissions.sql
-- Dynamic permission seeder script for Network Movers Enterprise Platform
-- =============================================================================

DO $$
DECLARE
    r_rec RECORD;
    admin_role_id UUID;
    customer_role_id UUID;
    p_id UUID;
    perm_code VARCHAR(255);
    perm_name VARCHAR(255);
    actions VARCHAR[] := ARRAY['CREATE', 'READ', 'UPDATE', 'DELETE'];
    act VARCHAR;
BEGIN
    -- 1. Fetch Role IDs
    SELECT id INTO admin_role_id FROM sec_roles WHERE code = 'ROLE_ADMIN';
    SELECT id INTO customer_role_id FROM sec_roles WHERE code = 'ROLE_CUSTOMER';

    -- If roles are not found in the DB (since SecurityDataSeeder runs on startup after Flyway),
    -- we can insert them directly to ensure they exist before permission mapping.
    IF admin_role_id IS NULL THEN
        admin_role_id := gen_random_uuid();
        INSERT INTO sec_roles (id, version, code, name, description, active, deleted, created_at)
        VALUES (admin_role_id, 0, 'ROLE_ADMIN', 'Administrator', 'Administrator Role', true, false, CURRENT_TIMESTAMP)
        ON CONFLICT (code) DO UPDATE SET id = sec_roles.id
        RETURNING id INTO admin_role_id;
    END IF;

    IF customer_role_id IS NULL THEN
        customer_role_id := gen_random_uuid();
        INSERT INTO sec_roles (id, version, code, name, description, active, deleted, created_at)
        VALUES (customer_role_id, 0, 'ROLE_CUSTOMER', 'Customer', 'Default Customer Role', true, false, CURRENT_TIMESTAMP)
        ON CONFLICT (code) DO UPDATE SET id = sec_roles.id
        RETURNING id INTO customer_role_id;
    END IF;

    -- 2. Loop through all resources to create permissions dynamically
    FOR r_rec IN SELECT id, code, name FROM sec_resources LOOP
        -- 3. Loop through CRUD actions
        FOREACH act IN ARRAY actions LOOP
            perm_code := r_rec.code || '_' || act;
            perm_name := act || ' ' || r_rec.name;
            
            p_id := gen_random_uuid();
            
            INSERT INTO sec_permissions (id, version, code, name, description, active, deleted, created_at, resource_id)
            VALUES (p_id, 0, perm_code, perm_name, 'Grants permission to ' || LOWER(act) || ' ' || LOWER(r_rec.name), true, false, CURRENT_TIMESTAMP, r_rec.id)
            ON CONFLICT (code) DO UPDATE SET name = EXCLUDED.name, resource_id = EXCLUDED.resource_id
            RETURNING id INTO p_id;
            
            -- 4. Map all permissions to Admin Role
            IF admin_role_id IS NOT NULL THEN
                INSERT INTO sec_role_permissions (id, version, role_id, permission_id, active, deleted, created_at)
                VALUES (gen_random_uuid(), 0, admin_role_id, p_id, true, false, CURRENT_TIMESTAMP)
                ON CONFLICT (role_id, permission_id) DO NOTHING;
            END IF;

            -- 5. Map READ permissions to Customer Role (except for system admin resources)
            IF customer_role_id IS NOT NULL AND act = 'READ' AND r_rec.code NOT IN ('ADMIN_USERS', 'ADMIN_ROLES', 'ADMIN_SETTINGS') THEN
                INSERT INTO sec_role_permissions (id, version, role_id, permission_id, active, deleted, created_at)
                VALUES (gen_random_uuid(), 0, customer_role_id, p_id, true, false, CURRENT_TIMESTAMP)
                ON CONFLICT (role_id, permission_id) DO NOTHING;
            END IF;
        END LOOP;
    END LOOP;
END $$;
