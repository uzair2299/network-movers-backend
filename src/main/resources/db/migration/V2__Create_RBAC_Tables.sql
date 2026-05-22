-- Flyway RBAC Schema Migration
-- Network Movers - Database Schema v2.0.0
-- This migration creates the security and RBAC tables

-- Roles table
CREATE TABLE IF NOT EXISTS sec_roles (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_sec_roles_name ON sec_roles(name);

-- Permissions table
CREATE TABLE IF NOT EXISTS sec_permissions (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_sec_permissions_name ON sec_permissions(name);

-- Resources table
CREATE TABLE IF NOT EXISTS sec_resources (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description TEXT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_sec_resources_name ON sec_resources(name);

-- Role-Permission mapping table
CREATE TABLE IF NOT EXISTS sec_role_permissions (
    id BIGSERIAL PRIMARY KEY,
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    version BIGINT DEFAULT 0,
    CONSTRAINT fk_sec_role_permissions_role FOREIGN KEY (role_id) REFERENCES sec_roles(id) ON DELETE CASCADE,
    CONSTRAINT fk_sec_role_permissions_permission FOREIGN KEY (permission_id) REFERENCES sec_permissions(id) ON DELETE CASCADE,
    CONSTRAINT uc_sec_role_permissions UNIQUE (role_id, permission_id)
);
CREATE INDEX IF NOT EXISTS idx_sec_role_permissions_role_id ON sec_role_permissions(role_id);
CREATE INDEX IF NOT EXISTS idx_sec_role_permissions_permission_id ON sec_role_permissions(permission_id);

-- User-Role mapping table
CREATE TABLE IF NOT EXISTS sec_user_roles (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    version BIGINT DEFAULT 0,
    CONSTRAINT fk_sec_user_roles_role FOREIGN KEY (role_id) REFERENCES sec_roles(id) ON DELETE CASCADE,
    CONSTRAINT uc_sec_user_roles UNIQUE (user_id, role_id)
);
CREATE INDEX IF NOT EXISTS idx_sec_user_roles_user_id ON sec_user_roles(user_id);
CREATE INDEX IF NOT EXISTS idx_sec_user_roles_role_id ON sec_user_roles(role_id);
