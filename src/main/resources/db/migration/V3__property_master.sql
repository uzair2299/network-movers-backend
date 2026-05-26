-- =============================================================================
-- V3__property_master.sql
-- Property Master Module Schema (Highly Scalable Generic Lookups)
-- =============================================================================

-- 1. Property Category Table
CREATE TABLE IF NOT EXISTS property_category (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE INDEX idx_prop_cat_code ON property_category(code);

-- 2. Property Type Table
CREATE TABLE IF NOT EXISTS property_type (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    category_id UUID            NOT NULL,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT,
    CONSTRAINT fk_type_category FOREIGN KEY (category_id) REFERENCES property_category(id) ON DELETE CASCADE
);

CREATE INDEX idx_prop_type_code ON property_type(code);

-- 3. Property Size Table
CREATE TABLE IF NOT EXISTS property_size (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    type_id     UUID            NOT NULL,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    unit_type   VARCHAR(100)    NOT NULL,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT,
    CONSTRAINT fk_size_type FOREIGN KEY (type_id) REFERENCES property_type(id) ON DELETE CASCADE
);

CREATE INDEX idx_prop_size_code ON property_size(code);

-- 4. Occupancy Type Table
CREATE TABLE IF NOT EXISTS occupancy_type (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE INDEX idx_occupancy_type_code ON occupancy_type(code);

-- 5. Building Access Type Table
CREATE TABLE IF NOT EXISTS building_access_type (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE INDEX idx_building_access_type_code ON building_access_type(code);

-- 6. Parking Access Type Table
CREATE TABLE IF NOT EXISTS parking_access_type (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE INDEX idx_parking_access_type_code ON parking_access_type(code);

-- 7. Floor Type Table
CREATE TABLE IF NOT EXISTS floor_type (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE INDEX idx_floor_type_code ON floor_type(code);

-- 8. Access Restriction Type Table
CREATE TABLE IF NOT EXISTS access_restriction_type (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    name        VARCHAR(255)    NOT NULL,
    code        VARCHAR(100)    NOT NULL UNIQUE,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE INDEX idx_access_restriction_type_code ON access_restriction_type(code);
