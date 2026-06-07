-- =============================================================================
-- V1__Schema.sql
-- Consolidated database schema for Network Movers Enterprise Platform
-- Merges all structural changes from V1 through V15 into one final-state schema.
-- No INSERT statements — data seeding is handled in V2__Data.sql
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

-- modules table (V9) must exist before sec_resources references it
CREATE TABLE IF NOT EXISTS modules (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT          DEFAULT 0,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL UNIQUE,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  BIGINT,
    updated_at  TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by  BIGINT
);

-- sec_resources — includes module_id FK added in V10
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
    updated_by  BIGINT,
    -- V10: module_id FK
    module_id   UUID,
    CONSTRAINT fk_resources_module FOREIGN KEY (module_id) REFERENCES modules(id) ON DELETE SET NULL
);

-- sec_permissions — includes resource_id FK added in V12
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
    updated_by  BIGINT,
    -- V12: resource_id FK
    resource_id UUID,
    CONSTRAINT fk_permissions_resource FOREIGN KEY (resource_id) REFERENCES sec_resources(id)
);

-- sec_role_permissions — final version from V13 (UUID PK, full audit/soft-delete columns)
CREATE TABLE IF NOT EXISTS sec_role_permissions (
    id            UUID        PRIMARY KEY DEFAULT gen_random_uuid(),
    version       BIGINT      DEFAULT 0,
    role_id       UUID        NOT NULL,
    permission_id UUID        NOT NULL,
    active        BOOLEAN     NOT NULL DEFAULT true,
    deleted       BOOLEAN     NOT NULL DEFAULT false,
    deleted_at    TIMESTAMP,
    deleted_by    BIGINT,
    created_at    TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by    BIGINT,
    updated_at    TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_by    BIGINT,
    CONSTRAINT uq_role_permission UNIQUE (role_id, permission_id),
    CONSTRAINT fk_rp_role       FOREIGN KEY (role_id)       REFERENCES sec_roles(id),
    CONSTRAINT fk_rp_permission FOREIGN KEY (permission_id) REFERENCES sec_permissions(id)
);

CREATE INDEX IF NOT EXISTS idx_role_permissions_role       ON sec_role_permissions(role_id);
CREATE INDEX IF NOT EXISTS idx_role_permissions_permission ON sec_role_permissions(permission_id);

-- =============================================================================
-- USER TABLES
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

CREATE TABLE IF NOT EXISTS tbl_user_profiles (
    id                  BIGSERIAL       PRIMARY KEY,
    version             BIGINT,
    user_id             UUID            NOT NULL UNIQUE,
    first_name          VARCHAR(50),
    last_name           VARCHAR(50),
    phone_number        VARCHAR(20),
    profile_picture_url VARCHAR(255),
    address             VARCHAR(255),
    deleted             BOOLEAN         NOT NULL DEFAULT false,
    deleted_at          TIMESTAMP,
    deleted_by          BIGINT,
    created_at          TIMESTAMP       NOT NULL,
    created_by          BIGINT,
    updated_at          TIMESTAMP,
    updated_by          BIGINT,
    CONSTRAINT fk_user_profile_user FOREIGN KEY (user_id) REFERENCES tbl_users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS sec_user_roles (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT          DEFAULT 0,
    user_id     UUID            NOT NULL,
    role_id     UUID            NOT NULL,
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT,
    CONSTRAINT uq_user_role UNIQUE (user_id, role_id),
    CONSTRAINT fk_ur_role FOREIGN KEY (role_id) REFERENCES sec_roles(id),
    CONSTRAINT fk_ur_user FOREIGN KEY (user_id) REFERENCES tbl_users(id)
);

CREATE INDEX IF NOT EXISTS idx_user_roles_role ON sec_user_roles(role_id);

-- =============================================================================
-- MENU TABLES
-- =============================================================================

CREATE TABLE IF NOT EXISTS sec_menu_items (
    id            BIGSERIAL      PRIMARY KEY,
    version       BIGINT         NOT NULL DEFAULT 0,
    name          VARCHAR(255)   NOT NULL,
    icon          VARCHAR(255),
    path          VARCHAR(255),
    section       VARCHAR(50)    NOT NULL DEFAULT 'SIDEBAR',
    parent_id     BIGINT,
    sort_order    INT            NOT NULL DEFAULT 0,
    permission_id UUID,
    active        BOOLEAN        NOT NULL DEFAULT TRUE,
    deleted       BOOLEAN        NOT NULL DEFAULT FALSE,
    deleted_at    TIMESTAMP,
    deleted_by    BIGINT,
    created_at    TIMESTAMP      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by    BIGINT,
    updated_at    TIMESTAMP,
    updated_by    BIGINT,
    CONSTRAINT fk_menu_parent     FOREIGN KEY (parent_id)     REFERENCES sec_menu_items(id) ON DELETE CASCADE,
    CONSTRAINT fk_menu_permission FOREIGN KEY (permission_id) REFERENCES sec_permissions(id) ON DELETE SET NULL,
    CONSTRAINT chk_menu_section   CHECK (section IN ('SIDEBAR', 'TOPBAR', 'PROFILE'))
);

CREATE INDEX IF NOT EXISTS idx_menu_parent ON sec_menu_items(parent_id);
CREATE INDEX IF NOT EXISTS idx_menu_section_active ON sec_menu_items(section, active, sort_order);
CREATE INDEX IF NOT EXISTS idx_menu_permission ON sec_menu_items(permission_id);

-- =============================================================================
-- PROPERTY REFERENCE TABLES
-- =============================================================================

-- 1. Property Category
CREATE TABLE IF NOT EXISTS property_category (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE INDEX IF NOT EXISTS idx_prop_cat_code ON property_category(code);

-- 2. Property Type
CREATE TABLE IF NOT EXISTS property_type (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    category_id UUID            NOT NULL,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT,
    CONSTRAINT fk_type_category FOREIGN KEY (category_id) REFERENCES property_category(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_prop_type_code ON property_type(code);
CREATE INDEX IF NOT EXISTS idx_prop_type_category ON property_type(category_id);

-- 3. Property Size
CREATE TABLE IF NOT EXISTS property_size (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    type_id     UUID            NOT NULL,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    unit_type   VARCHAR(100)    NOT NULL,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT,
    CONSTRAINT fk_size_type FOREIGN KEY (type_id) REFERENCES property_type(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS idx_prop_size_code ON property_size(code);
CREATE INDEX IF NOT EXISTS idx_prop_size_type ON property_size(type_id);

-- 4. Occupancy Type
CREATE TABLE IF NOT EXISTS occupancy_type (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE INDEX IF NOT EXISTS idx_occupancy_type_code ON occupancy_type(code);

-- 5. Building Access Type
CREATE TABLE IF NOT EXISTS building_access_type (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE INDEX IF NOT EXISTS idx_building_access_type_code ON building_access_type(code);

-- 6. Parking Access Type
CREATE TABLE IF NOT EXISTS parking_access_type (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE INDEX IF NOT EXISTS idx_parking_access_type_code ON parking_access_type(code);

-- 7. Floor Type
CREATE TABLE IF NOT EXISTS floor_type (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE INDEX IF NOT EXISTS idx_floor_type_code ON floor_type(code);

-- 8. Access Restriction Type
CREATE TABLE IF NOT EXISTS access_restriction_type (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE INDEX IF NOT EXISTS idx_access_restriction_type_code ON access_restriction_type(code);

-- =============================================================================
-- MOVE PHASE & STATUS TABLES
-- =============================================================================

CREATE TABLE IF NOT EXISTS move_phase (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    code        VARCHAR(50)     UNIQUE NOT NULL,
    name        VARCHAR(100)    NOT NULL,
    sequence_no INT             NOT NULL,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS move_status (
    id               UUID            PRIMARY KEY,
    version          BIGINT          NOT NULL DEFAULT 0,
    code             VARCHAR(100)    UNIQUE NOT NULL,
    name             VARCHAR(150)    NOT NULL,
    phase_id         UUID            NOT NULL,
    description      TEXT,
    sequence_no      INT,
    is_final         BOOLEAN         NOT NULL DEFAULT FALSE,
    active           BOOLEAN         NOT NULL DEFAULT TRUE,
    color_code       VARCHAR(20),
    customer_visible BOOLEAN         NOT NULL DEFAULT TRUE,
    internal_only    BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted          BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at       TIMESTAMP,
    deleted_by       BIGINT,
    created_at       TIMESTAMP       NOT NULL,
    created_by       BIGINT,
    updated_at       TIMESTAMP,
    updated_by       BIGINT,
    CONSTRAINT fk_phase FOREIGN KEY (phase_id) REFERENCES move_phase(id)
);

CREATE INDEX IF NOT EXISTS idx_move_status_phase ON move_status(phase_id);

-- =============================================================================
-- BOOKING TABLES
-- =============================================================================

CREATE TABLE IF NOT EXISTS tbl_booking (
    id                             UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version                        BIGINT,
    code                           VARCHAR(255)    NOT NULL UNIQUE,
    name                           VARCHAR(255),
    description                    VARCHAR(255),
    active                         BOOLEAN         NOT NULL DEFAULT true,
    deleted                        BOOLEAN         NOT NULL DEFAULT false,
    deleted_at                     TIMESTAMP,
    deleted_by                     BIGINT,
    created_at                     TIMESTAMP       NOT NULL,
    created_by                     BIGINT,
    updated_at                     TIMESTAMP,
    updated_by                     BIGINT,
    user_id                        UUID,
    pickup_address                 VARCHAR(500),
    pickup_latitude                NUMERIC(10, 8),
    pickup_longitude               NUMERIC(11, 8),
    destination_address            VARCHAR(500),
    destination_latitude           NUMERIC(10, 8),
    destination_longitude          NUMERIC(11, 8),
    distance_km                    NUMERIC(10, 2),
    duration_minutes               INT,
    schedule_type                  VARCHAR(50),
    scheduled_date                 TIMESTAMP WITH TIME ZONE,
    time_slot                      VARCHAR(100),
    property_category_id           UUID,
    property_type_id               UUID,
    property_size_id               UUID,
    pickup_floor_type_id           UUID,
    pickup_building_access_id      UUID,
    pickup_parking_access_id       UUID,
    destination_floor_type_id      UUID,
    destination_building_access_id UUID,
    destination_parking_access_id  UUID,
    current_status_id              UUID,
    CONSTRAINT fk_booking_user                         FOREIGN KEY (user_id)                        REFERENCES tbl_users(id),
    CONSTRAINT fk_booking_property_category            FOREIGN KEY (property_category_id)           REFERENCES property_category(id),
    CONSTRAINT fk_booking_property_type                FOREIGN KEY (property_type_id)               REFERENCES property_type(id),
    CONSTRAINT fk_booking_property_size                FOREIGN KEY (property_size_id)               REFERENCES property_size(id),
    CONSTRAINT fk_booking_pickup_floor_type            FOREIGN KEY (pickup_floor_type_id)           REFERENCES floor_type(id),
    CONSTRAINT fk_booking_pickup_building_access       FOREIGN KEY (pickup_building_access_id)      REFERENCES building_access_type(id),
    CONSTRAINT fk_booking_pickup_parking_access        FOREIGN KEY (pickup_parking_access_id)       REFERENCES parking_access_type(id),
    CONSTRAINT fk_booking_destination_floor_type       FOREIGN KEY (destination_floor_type_id)      REFERENCES floor_type(id),
    CONSTRAINT fk_booking_destination_building_access  FOREIGN KEY (destination_building_access_id) REFERENCES building_access_type(id),
    CONSTRAINT fk_booking_destination_parking_access   FOREIGN KEY (destination_parking_access_id)  REFERENCES parking_access_type(id),
    CONSTRAINT fk_booking_current_status               FOREIGN KEY (current_status_id)              REFERENCES move_status(id)
);

CREATE INDEX IF NOT EXISTS idx_booking_user                        ON tbl_booking(user_id);
CREATE INDEX IF NOT EXISTS idx_booking_property_category           ON tbl_booking(property_category_id);
CREATE INDEX IF NOT EXISTS idx_booking_property_type               ON tbl_booking(property_type_id);
CREATE INDEX IF NOT EXISTS idx_booking_property_size               ON tbl_booking(property_size_id);
CREATE INDEX IF NOT EXISTS idx_booking_pickup_floor_type           ON tbl_booking(pickup_floor_type_id);
CREATE INDEX IF NOT EXISTS idx_booking_destination_floor_type      ON tbl_booking(destination_floor_type_id);
CREATE INDEX IF NOT EXISTS idx_booking_pickup_building_access      ON tbl_booking(pickup_building_access_id);
CREATE INDEX IF NOT EXISTS idx_booking_destination_building_access ON tbl_booking(destination_building_access_id);
CREATE INDEX IF NOT EXISTS idx_booking_pickup_parking_access       ON tbl_booking(pickup_parking_access_id);
CREATE INDEX IF NOT EXISTS idx_booking_destination_parking_access  ON tbl_booking(destination_parking_access_id);
CREATE INDEX IF NOT EXISTS idx_booking_current_status              ON tbl_booking(current_status_id);

CREATE TABLE IF NOT EXISTS tbl_booking_pickup_restrictions (
    booking_id     UUID NOT NULL,
    restriction_id UUID NOT NULL,
    PRIMARY KEY (booking_id, restriction_id),
    CONSTRAINT fk_tbpr_booking     FOREIGN KEY (booking_id)     REFERENCES tbl_booking(id) ON DELETE CASCADE,
    CONSTRAINT fk_tbpr_restriction FOREIGN KEY (restriction_id) REFERENCES access_restriction_type(id)
);

CREATE INDEX IF NOT EXISTS idx_tbpr_restriction ON tbl_booking_pickup_restrictions(restriction_id);

CREATE TABLE IF NOT EXISTS tbl_booking_destination_restrictions (
    booking_id     UUID NOT NULL,
    restriction_id UUID NOT NULL,
    PRIMARY KEY (booking_id, restriction_id),
    CONSTRAINT fk_tbdr_booking     FOREIGN KEY (booking_id)     REFERENCES tbl_booking(id) ON DELETE CASCADE,
    CONSTRAINT fk_tbdr_restriction FOREIGN KEY (restriction_id) REFERENCES access_restriction_type(id)
);

CREATE INDEX IF NOT EXISTS idx_tbdr_restriction ON tbl_booking_destination_restrictions(restriction_id);

CREATE TABLE IF NOT EXISTS tbl_booking_history (
    id                 BIGSERIAL PRIMARY KEY,
    booking_id         UUID NOT NULL,
    previous_status_id UUID,
    new_status_id      UUID NOT NULL,
    notes              VARCHAR(1000),
    created_at         TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by         BIGINT,
    updated_at         TIMESTAMP WITH TIME ZONE,
    updated_by         BIGINT,
    CONSTRAINT fk_bh_booking         FOREIGN KEY (booking_id)         REFERENCES tbl_booking(id) ON DELETE CASCADE,
    CONSTRAINT fk_bh_previous_status FOREIGN KEY (previous_status_id) REFERENCES move_status(id),
    CONSTRAINT fk_bh_new_status      FOREIGN KEY (new_status_id)      REFERENCES move_status(id)
);

CREATE INDEX IF NOT EXISTS idx_booking_history_prev_status ON tbl_booking_history(previous_status_id);
CREATE INDEX IF NOT EXISTS idx_booking_history_new_status  ON tbl_booking_history(new_status_id);
CREATE INDEX IF NOT EXISTS idx_booking_history_booking_created ON tbl_booking_history(booking_id, created_at DESC);

-- =============================================================================
-- MODULE PLACEHOLDER TABLES
-- (Stub tables for each business domain module — structure to be expanded later)
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

-- =============================================================================
-- VEHICLE DOMAIN TABLES
-- =============================================================================

CREATE TABLE IF NOT EXISTS document_types (
    id               UUID PRIMARY KEY,
    version          BIGINT DEFAULT 0,
    created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by       BIGINT,
    updated_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by       BIGINT,
    deleted          BOOLEAN DEFAULT FALSE,
    deleted_at       TIMESTAMP,
    deleted_by       BIGINT,
    code             VARCHAR(50)  UNIQUE NOT NULL,
    name             VARCHAR(100) NOT NULL,
    active           BOOLEAN      NOT NULL DEFAULT TRUE,
    mandatory        BOOLEAN DEFAULT FALSE,
    expiry_required  BOOLEAN DEFAULT TRUE,
    description      TEXT
);

CREATE TABLE IF NOT EXISTS vehicle_types (
    id               UUID PRIMARY KEY,
    version          BIGINT DEFAULT 0,
    created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by       BIGINT,
    updated_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by       BIGINT,
    deleted          BOOLEAN DEFAULT FALSE,
    deleted_at       TIMESTAMP,
    deleted_by       BIGINT,
    code             VARCHAR(50)  UNIQUE NOT NULL,
    name             VARCHAR(100) NOT NULL,
    active           BOOLEAN      NOT NULL DEFAULT TRUE,
    description      TEXT
);

CREATE TABLE IF NOT EXISTS vehicle_makes (
    id               UUID PRIMARY KEY,
    version          BIGINT DEFAULT 0,
    created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by       BIGINT,
    updated_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by       BIGINT,
    deleted          BOOLEAN DEFAULT FALSE,
    deleted_at       TIMESTAMP,
    deleted_by       BIGINT,
    code             VARCHAR(50)  UNIQUE NOT NULL,
    name             VARCHAR(100) NOT NULL,
    active           BOOLEAN      NOT NULL DEFAULT TRUE,
    country          VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS vehicle_models (
    id                UUID PRIMARY KEY,
    version           BIGINT DEFAULT 0,
    created_at        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by        BIGINT,
    updated_at        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by        BIGINT,
    deleted           BOOLEAN DEFAULT FALSE,
    deleted_at        TIMESTAMP,
    deleted_by        BIGINT,
    make_id           UUID NOT NULL,
    vehicle_type_id   UUID NOT NULL,
    code              VARCHAR(50)  UNIQUE NOT NULL,
    name              VARCHAR(100) NOT NULL,
    active            BOOLEAN      NOT NULL DEFAULT TRUE,
    capacity_kg       NUMERIC(10,2),
    capacity_m3       NUMERIC(10,2),
    length_m          NUMERIC(10,2),
    width_m           NUMERIC(10,2),
    height_m          NUMERIC(10,2),
    CONSTRAINT fk_vm_make FOREIGN KEY (make_id)          REFERENCES vehicle_makes(id),
    CONSTRAINT fk_vm_type FOREIGN KEY (vehicle_type_id)  REFERENCES vehicle_types(id)
);

CREATE INDEX IF NOT EXISTS idx_vm_make ON vehicle_models(make_id);
CREATE INDEX IF NOT EXISTS idx_vm_type ON vehicle_models(vehicle_type_id);

CREATE TABLE IF NOT EXISTS vehicles (
    id                      UUID PRIMARY KEY,
    version                 BIGINT DEFAULT 0,
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by              BIGINT,
    updated_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by              BIGINT,
    deleted                 BOOLEAN DEFAULT FALSE,
    deleted_at              TIMESTAMP,
    deleted_by              BIGINT,
    vehicle_code            VARCHAR(50)  UNIQUE NOT NULL,
    registration_no         VARCHAR(100) UNIQUE NOT NULL,
    vehicle_model_id        UUID NOT NULL,
    manufacture_year        INTEGER,
    ownership_type          VARCHAR(50)  NOT NULL,
    status                  VARCHAR(50)  NOT NULL,
    current_odometer_km     NUMERIC(12,2),
    insurance_expiry_date   DATE,
    fitness_expiry_date     DATE,
    acquisition_date        DATE,
    active                  BOOLEAN      NOT NULL DEFAULT TRUE,
    remarks                 TEXT,
    CONSTRAINT fk_vehicle_model FOREIGN KEY (vehicle_model_id) REFERENCES vehicle_models(id)
);

CREATE INDEX IF NOT EXISTS idx_vehicle_model ON vehicles(vehicle_model_id);

CREATE TABLE IF NOT EXISTS vehicle_documents (
    id                UUID PRIMARY KEY,
    version           BIGINT DEFAULT 0,
    created_at        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by        BIGINT,
    updated_at        TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by        BIGINT,
    deleted           BOOLEAN DEFAULT FALSE,
    deleted_at        TIMESTAMP,
    deleted_by        BIGINT,
    vehicle_id        UUID NOT NULL,
    document_type_id  UUID NOT NULL,
    document_number   VARCHAR(255),
    issue_date        DATE,
    expiry_date       DATE,
    file_url          TEXT,
    verified          BOOLEAN DEFAULT FALSE,
    remarks           TEXT,
    CONSTRAINT fk_vd_vehicle  FOREIGN KEY (vehicle_id)       REFERENCES vehicles(id),
    CONSTRAINT fk_vd_doc_type FOREIGN KEY (document_type_id) REFERENCES document_types(id)
);

CREATE INDEX IF NOT EXISTS idx_vd_vehicle   ON vehicle_documents(vehicle_id);
CREATE INDEX IF NOT EXISTS idx_vd_doc_type  ON vehicle_documents(document_type_id);

CREATE TABLE IF NOT EXISTS vehicle_maintenance_types (
    id               UUID PRIMARY KEY,
    version          BIGINT DEFAULT 0,
    created_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by       BIGINT,
    updated_at       TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by       BIGINT,
    deleted          BOOLEAN DEFAULT FALSE,
    deleted_at       TIMESTAMP,
    deleted_by       BIGINT,
    code             VARCHAR(50)  UNIQUE NOT NULL,
    name             VARCHAR(100) NOT NULL,
    active           BOOLEAN      NOT NULL DEFAULT TRUE,
    description      TEXT
);

CREATE TABLE IF NOT EXISTS vehicle_maintenance (
    id                   UUID PRIMARY KEY,
    version              BIGINT DEFAULT 0,
    created_at           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by           BIGINT,
    updated_at           TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by           BIGINT,
    deleted              BOOLEAN DEFAULT FALSE,
    deleted_at           TIMESTAMP,
    deleted_by           BIGINT,
    vehicle_id           UUID NOT NULL,
    maintenance_type_id  UUID NOT NULL,
    maintenance_date     DATE NOT NULL,
    odometer_km          NUMERIC(12,2),
    cost                 NUMERIC(12,2),
    vendor_name          VARCHAR(255),
    next_service_date    DATE,
    next_service_km      NUMERIC(12,2),
    remarks              TEXT,
    CONSTRAINT fk_vmaint_vehicle FOREIGN KEY (vehicle_id)          REFERENCES vehicles(id),
    CONSTRAINT fk_vmaint_type    FOREIGN KEY (maintenance_type_id) REFERENCES vehicle_maintenance_types(id)
);

CREATE INDEX IF NOT EXISTS idx_vmaint_vehicle ON vehicle_maintenance(vehicle_id);
CREATE INDEX IF NOT EXISTS idx_vmaint_type    ON vehicle_maintenance(maintenance_type_id);

CREATE TABLE IF NOT EXISTS vehicle_fuel_logs (
    id                    UUID PRIMARY KEY,
    version               BIGINT DEFAULT 0,
    created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by            BIGINT,
    updated_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by            BIGINT,
    deleted               BOOLEAN DEFAULT FALSE,
    deleted_at            TIMESTAMP,
    deleted_by            BIGINT,
    vehicle_id            UUID NOT NULL,
    fuel_date             TIMESTAMP NOT NULL,
    fuel_quantity_liters  NUMERIC(10,2),
    cost_amount           NUMERIC(12,2),
    odometer_km           NUMERIC(12,2),
    fuel_station          VARCHAR(255),
    remarks               TEXT,
    CONSTRAINT fk_vfl_vehicle FOREIGN KEY (vehicle_id) REFERENCES vehicles(id)
);

CREATE INDEX IF NOT EXISTS idx_vfl_vehicle ON vehicle_fuel_logs(vehicle_id);
