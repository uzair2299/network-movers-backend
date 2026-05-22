-- =============================================================================
-- V1__Initial_Schema.sql
-- Initial database schema for Network Movers Enterprise Platform
-- Generated from JPA entity definitions
-- =============================================================================

-- =============================================================================
-- SECURITY / RBAC TABLES
-- =============================================================================

CREATE TABLE IF NOT EXISTS sec_roles (
    id          BIGSERIAL       PRIMARY KEY,
    name        VARCHAR(255)    NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS sec_permissions (
    id          BIGSERIAL       PRIMARY KEY,
    name        VARCHAR(255)    NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS sec_resources (
    id          BIGSERIAL       PRIMARY KEY,
    name        VARCHAR(255)    NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS sec_role_permissions (
    id            BIGSERIAL   PRIMARY KEY,
    role_id       BIGINT      NOT NULL,
    permission_id BIGINT      NOT NULL,
    CONSTRAINT uq_role_permission UNIQUE (role_id, permission_id),
    CONSTRAINT fk_rp_role       FOREIGN KEY (role_id)       REFERENCES sec_roles(id),
    CONSTRAINT fk_rp_permission FOREIGN KEY (permission_id) REFERENCES sec_permissions(id)
);

-- =============================================================================
-- USER TABLE
-- =============================================================================

CREATE TABLE IF NOT EXISTS tbl_users (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    username    VARCHAR(255)    NOT NULL UNIQUE,
    email       VARCHAR(255)    NOT NULL UNIQUE,
    password    VARCHAR(255)    NOT NULL,
    enabled     BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS sec_user_roles (
    id      BIGSERIAL   PRIMARY KEY,
    user_id BIGINT      NOT NULL,
    role_id BIGINT      NOT NULL,
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
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_admin (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_ai (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_analytics (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_approval (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_attendance (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_audit (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_automation (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_backup (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_booking (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_chat (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_claims (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_communication (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_complaint (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_configuration (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_contract (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_coupon (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_customer (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_dashboard (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_dispatch (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_dispatcher (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_document (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_driver (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_estimate (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_filemanagement (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_finance (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_fleet (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_fraud (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_geofence (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_hr (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_identity (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_insurance (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_inventory (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_invoice (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_leave (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_location (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_logistics (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_lookup (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_maps (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_media (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_mover (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_movingitem (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_notification (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_optimization (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_package (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_partner (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_payment (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_payroll (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_pricing (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_promotion (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_quotation (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_rating (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_realtime (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_recommendation (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_report (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_review (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_route (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_scheduling (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_search (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_settings (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_subscription (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_support (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_taxation (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_ticket (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_tracking (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_trip (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_truck (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_vehicle (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_vendor (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_wallet (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_warehouse (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS tbl_workflow (
    id          BIGSERIAL       PRIMARY KEY,
    version     BIGINT,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);
