-- =============================================================================
-- V1__Initial_Schema.sql
-- Initial database schema for Network Movers Enterprise Platform
-- Generated from JPA entity definitions
-- =============================================================================

-- =============================================================================
-- SECURITY / RBAC TABLES
-- =============================================================================

CREATE TABLE IF NOT EXISTS sec_roles (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL UNIQUE,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS sec_permissions (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS sec_resources (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS sec_role_permissions (
    id            BIGSERIAL   PRIMARY KEY,
    role_id       UUID        NOT NULL,
    permission_id BIGINT      NOT NULL,
    CONSTRAINT uq_role_permission UNIQUE (role_id, permission_id),
    CONSTRAINT fk_rp_role       FOREIGN KEY (role_id)       REFERENCES sec_roles(id),
    CONSTRAINT fk_rp_permission FOREIGN KEY (permission_id) REFERENCES sec_permissions(id)
);

-- =============================================================================
-- USER TABLE
-- =============================================================================

CREATE TABLE IF NOT EXISTS tbl_users (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    username    VARCHAR(255)    NOT NULL UNIQUE,
    email       VARCHAR(255)    NOT NULL UNIQUE,
    password    VARCHAR(255)    NOT NULL,
    enabled     BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS sec_user_roles (
    id      BIGSERIAL   PRIMARY KEY,
    user_id BIGINT      NOT NULL,
    role_id UUID        NOT NULL,
    CONSTRAINT uq_user_role UNIQUE (user_id, role_id),
    CONSTRAINT fk_ur_role FOREIGN KEY (role_id) REFERENCES sec_roles(id)
);

-- =============================================================================
-- MODULE TABLES
-- All module tables share the same base structure from BaseAuditEntity:
--   id, version, name (NOT NULL), description,
--   created_at (NOT NULL), created_by, updated_at, updated_by
-- =============================================================================

CREATE TABLE IF NOT EXISTS tbl_accounting (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_admin (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_ai (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_analytics (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_approval (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_attendance (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_audit (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_automation (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_backup (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_booking (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_chat (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_claims (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_communication (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_complaint (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_configuration (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_contract (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_coupon (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_customer (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_dashboard (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_dispatch (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_dispatcher (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_document (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_driver (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_estimate (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_filemanagement (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_finance (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_fleet (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_fraud (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_geofence (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_hr (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_identity (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_insurance (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_inventory (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_invoice (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_leave (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_location (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_logistics (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_lookup (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_maps (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_media (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_mover (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_movingitem (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_notification (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_optimization (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_package (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_partner (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_payment (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_payroll (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_pricing (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_promotion (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_quotation (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_rating (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_realtime (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_recommendation (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_report (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_review (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_route (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_scheduling (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_search (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_settings (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_subscription (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_support (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_taxation (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_ticket (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_tracking (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_trip (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_truck (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_vehicle (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_vendor (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_wallet (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_warehouse (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_workflow (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);
