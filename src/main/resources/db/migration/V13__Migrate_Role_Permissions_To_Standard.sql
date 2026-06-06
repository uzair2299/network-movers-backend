-- V13: Migrate sec_role_permissions to standard UUID pattern with full audit/soft-delete fields

-- Drop old table and recreate with standard pattern
DROP TABLE IF EXISTS sec_role_permissions;

CREATE TABLE IF NOT EXISTS sec_role_permissions (
    id          UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT      DEFAULT 0,
    role_id     UUID        NOT NULL,
    permission_id UUID      NOT NULL,
    active      BOOLEAN     NOT NULL DEFAULT true,
    deleted     BOOLEAN     NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  BIGINT,
    updated_at  TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_by  BIGINT,
    CONSTRAINT uq_role_permission UNIQUE (role_id, permission_id),
    CONSTRAINT fk_rp_role       FOREIGN KEY (role_id)       REFERENCES sec_roles(id),
    CONSTRAINT fk_rp_permission FOREIGN KEY (permission_id) REFERENCES sec_permissions(id)
);

CREATE INDEX IF NOT EXISTS idx_role_permissions_role       ON sec_role_permissions(role_id);
CREATE INDEX IF NOT EXISTS idx_role_permissions_permission ON sec_role_permissions(permission_id);
