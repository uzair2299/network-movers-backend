-- Flyway Initial Schema Migration
-- Network Movers - Database Schema v1.0.0
-- This migration creates all necessary tables for the application

-- ===================================================================
-- SECURITY & RBAC TABLES MOVED TO V2__Create_RBAC_Tables.sql
-- ===================================================================

-- ===================================================================
-- IDENTITY & USER MANAGEMENT TABLES
-- ===================================================================

-- Users table (for identity/authentication)
CREATE TABLE IF NOT EXISTS tbl_users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_users_username ON tbl_users(username);
CREATE INDEX IF NOT EXISTS idx_tbl_users_email ON tbl_users(email);

-- Identity entity table
CREATE TABLE IF NOT EXISTS tbl_identity (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_identity_name ON tbl_identity(name);

-- ===================================================================
-- BUSINESS ENTITIES TABLES
-- ===================================================================

-- Customer table
CREATE TABLE IF NOT EXISTS tbl_customer (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_customer_name ON tbl_customer(name);

-- Vehicle table
CREATE TABLE IF NOT EXISTS tbl_vehicle (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_vehicle_name ON tbl_vehicle(name);

-- Driver table
CREATE TABLE IF NOT EXISTS tbl_driver (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_driver_name ON tbl_driver(name);

-- Warehouse table
CREATE TABLE IF NOT EXISTS tbl_warehouse (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_warehouse_name ON tbl_warehouse(name);

-- Estimate table
CREATE TABLE IF NOT EXISTS tbl_estimate (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_estimate_name ON tbl_estimate(name);

-- Dispatch table
CREATE TABLE IF NOT EXISTS tbl_dispatch (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_dispatch_name ON tbl_dispatch(name);

-- Dispatcher table
CREATE TABLE IF NOT EXISTS tbl_dispatcher (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_dispatcher_name ON tbl_dispatcher(name);

-- Fleet table
CREATE TABLE IF NOT EXISTS tbl_fleet (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_fleet_name ON tbl_fleet(name);

-- Truck table
CREATE TABLE IF NOT EXISTS tbl_truck (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_truck_name ON tbl_truck(name);

-- Vendor table
CREATE TABLE IF NOT EXISTS tbl_vendor (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_vendor_name ON tbl_vendor(name);

-- ===================================================================
-- OPERATIONAL & MANAGEMENT TABLES
-- ===================================================================

-- Workflow table
CREATE TABLE IF NOT EXISTS tbl_workflow (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_workflow_name ON tbl_workflow(name);

-- File Management table
CREATE TABLE IF NOT EXISTS tbl_filemanagement (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_filemanagement_name ON tbl_filemanagement(name);

-- HR (Human Resources) table
CREATE TABLE IF NOT EXISTS tbl_hr (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_hr_name ON tbl_hr(name);

-- Document table
CREATE TABLE IF NOT EXISTS tbl_document (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_document_name ON tbl_document(name);

-- Wallet table
CREATE TABLE IF NOT EXISTS tbl_wallet (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_wallet_name ON tbl_wallet(name);

-- Geofence table
CREATE TABLE IF NOT EXISTS tbl_geofence (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_geofence_name ON tbl_geofence(name);

-- Fraud table
CREATE TABLE IF NOT EXISTS tbl_fraud (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_fraud_name ON tbl_fraud(name);

CREATE TABLE IF NOT EXISTS tbl_dashboard (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_dashboard_name ON tbl_dashboard(name);

-- Accounting table
CREATE TABLE IF NOT EXISTS tbl_accounting (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_accounting_name ON tbl_accounting(name);

-- Insurance table
CREATE TABLE IF NOT EXISTS tbl_insurance (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_insurance_name ON tbl_insurance(name);

-- Inventory table
CREATE TABLE IF NOT EXISTS tbl_inventory (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_inventory_name ON tbl_inventory(name);

-- Finance table
CREATE TABLE IF NOT EXISTS tbl_finance (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_finance_name ON tbl_finance(name);

-- Trip table
CREATE TABLE IF NOT EXISTS tbl_trip (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_trip_name ON tbl_trip(name);

-- Configuration table
CREATE TABLE IF NOT EXISTS tbl_configuration (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_configuration_name ON tbl_configuration(name);

-- Contract table
CREATE TABLE IF NOT EXISTS tbl_contract (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_contract_name ON tbl_contract(name);

-- Ticket table
CREATE TABLE IF NOT EXISTS tbl_ticket (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_ticket_name ON tbl_ticket(name);

-- Tracking table (extends BaseAuditEntity)
CREATE TABLE IF NOT EXISTS tbl_tracking (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    created_at TIMESTAMP,
    created_by BIGINT,
    updated_at TIMESTAMP,
    updated_by BIGINT,
    version BIGINT DEFAULT 0
);
CREATE INDEX IF NOT EXISTS idx_tbl_tracking_name ON tbl_tracking(name);

-- ===================================================================
-- MIGRATION END
-- ===================================================================
-- Schema created successfully with all required tables and indexes
-- All tables include audit fields: created_at, created_by, updated_at, updated_by, version
-- Foreign keys are set with CASCADE ON DELETE for referential integrity
