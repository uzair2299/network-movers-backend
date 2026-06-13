-- =============================================================================
-- V5__Asset_Management_Schema.sql
-- Database schema for Asset Management Module (20 tables)
-- =============================================================================

-- 1. ast_asset_types
CREATE TABLE IF NOT EXISTS ast_asset_types (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT          NOT NULL DEFAULT 0,
    code        VARCHAR(255)    NOT NULL,
    name        VARCHAR(255)    NOT NULL,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  BIGINT,
    updated_at  TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by  BIGINT
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_ast_asset_types_code ON ast_asset_types(code) WHERE deleted = false;

-- 2. ast_categories
CREATE TABLE IF NOT EXISTS ast_categories (
    id                  UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version             BIGINT          NOT NULL DEFAULT 0,
    code                VARCHAR(255)    NOT NULL,
    name                VARCHAR(255)    NOT NULL,
    asset_type_id       UUID            NOT NULL,
    parent_category_id  UUID,
    description         VARCHAR(500),
    active              BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted             BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at          TIMESTAMP,
    deleted_by          BIGINT,
    created_at          TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by          BIGINT,
    updated_at          TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by          BIGINT,
    CONSTRAINT fk_categories_asset_type FOREIGN KEY (asset_type_id) REFERENCES ast_asset_types(id),
    CONSTRAINT fk_categories_parent FOREIGN KEY (parent_category_id) REFERENCES ast_categories(id)
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_ast_categories_code ON ast_categories(code) WHERE deleted = false;
CREATE INDEX IF NOT EXISTS idx_ast_categories_asset_type ON ast_categories(asset_type_id);
CREATE INDEX IF NOT EXISTS idx_ast_categories_parent ON ast_categories(parent_category_id);

-- 3. ast_suppliers
CREATE TABLE IF NOT EXISTS ast_suppliers (
    id            UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version       BIGINT          NOT NULL DEFAULT 0,
    code          VARCHAR(255)    NOT NULL,
    name          VARCHAR(255)    NOT NULL,
    contact_name  VARCHAR(255),
    email         VARCHAR(255),
    phone         VARCHAR(50),
    address       VARCHAR(500),
    active        BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted       BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at    TIMESTAMP,
    deleted_by    BIGINT,
    created_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by    BIGINT,
    updated_at    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by    BIGINT
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_ast_suppliers_code ON ast_suppliers(code) WHERE deleted = false;

-- 4. ast_locations
CREATE TABLE IF NOT EXISTS ast_locations (
    id            UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version       BIGINT          NOT NULL DEFAULT 0,
    code          VARCHAR(255)    NOT NULL,
    name          VARCHAR(255)    NOT NULL,
    description   VARCHAR(500),
    active        BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted       BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at    TIMESTAMP,
    deleted_by    BIGINT,
    created_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by    BIGINT,
    updated_at    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by    BIGINT
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_ast_locations_code ON ast_locations(code) WHERE deleted = false;

-- 5. ast_companies
CREATE TABLE IF NOT EXISTS ast_companies (
    id            UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version       BIGINT          NOT NULL DEFAULT 0,
    code          VARCHAR(255)    NOT NULL,
    name          VARCHAR(255)    NOT NULL,
    active        BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted       BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at    TIMESTAMP,
    deleted_by    BIGINT,
    created_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by    BIGINT,
    updated_at    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by    BIGINT
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_ast_companies_code ON ast_companies(code) WHERE deleted = false;

-- 6. ast_units_of_measure
CREATE TABLE IF NOT EXISTS ast_units_of_measure (
    id            UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version       BIGINT          NOT NULL DEFAULT 0,
    code          VARCHAR(255)    NOT NULL,
    name          VARCHAR(255)    NOT NULL,
    active        BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted       BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at    TIMESTAMP,
    deleted_by    BIGINT,
    created_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by    BIGINT,
    updated_at    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by    BIGINT
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_ast_units_of_measure_code ON ast_units_of_measure(code) WHERE deleted = false;

-- 7. ast_assets
CREATE TABLE IF NOT EXISTS ast_assets (
    id                  UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version             BIGINT          NOT NULL DEFAULT 0,
    code                VARCHAR(255)    NOT NULL,
    name                VARCHAR(255)    NOT NULL,
    sku                 VARCHAR(255),
    barcode             VARCHAR(255),
    description         VARCHAR(500),
    model               VARCHAR(255),
    serial_number       VARCHAR(255),
    asset_type_id       UUID            NOT NULL,
    category_id         UUID            NOT NULL,
    company_id          UUID            NOT NULL,
    unit_of_measure_id  UUID            NOT NULL,
    supplier_id         UUID,
    purchase_date       DATE,
    purchase_cost       DECIMAL(18,2),
    status              VARCHAR(50)     NOT NULL, -- ACTIVE, MAINTENANCE, RETIRED, LOST, DAMAGED
    active              BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted             BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at          TIMESTAMP,
    deleted_by          BIGINT,
    created_at          TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by          BIGINT,
    updated_at          TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by          BIGINT,
    CONSTRAINT fk_assets_asset_type FOREIGN KEY (asset_type_id) REFERENCES ast_asset_types(id),
    CONSTRAINT fk_assets_category FOREIGN KEY (category_id) REFERENCES ast_categories(id),
    CONSTRAINT fk_assets_company FOREIGN KEY (company_id) REFERENCES ast_companies(id),
    CONSTRAINT fk_assets_uom FOREIGN KEY (unit_of_measure_id) REFERENCES ast_units_of_measure(id),
    CONSTRAINT fk_assets_supplier FOREIGN KEY (supplier_id) REFERENCES ast_suppliers(id)
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_ast_assets_code ON ast_assets(code) WHERE deleted = false;
CREATE INDEX IF NOT EXISTS idx_ast_assets_type ON ast_assets(asset_type_id);
CREATE INDEX IF NOT EXISTS idx_ast_assets_category ON ast_assets(category_id);
CREATE INDEX IF NOT EXISTS idx_ast_assets_company ON ast_assets(company_id);
CREATE INDEX IF NOT EXISTS idx_ast_assets_uom ON ast_assets(unit_of_measure_id);
CREATE INDEX IF NOT EXISTS idx_ast_assets_supplier ON ast_assets(supplier_id);

-- 8. ast_asset_stock
CREATE TABLE IF NOT EXISTS ast_asset_stock (
    id                UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version           BIGINT          NOT NULL DEFAULT 0,
    asset_id          UUID            NOT NULL,
    location_id       UUID            NOT NULL,
    quantity          DECIMAL(18,2)   NOT NULL DEFAULT 0.00,
    minimum_quantity  DECIMAL(18,2)   NOT NULL DEFAULT 0.00,
    maximum_quantity  DECIMAL(18,2),
    deleted           BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at        TIMESTAMP,
    deleted_by        BIGINT,
    created_at        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by        BIGINT,
    updated_at        TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by        BIGINT,
    CONSTRAINT fk_stock_asset FOREIGN KEY (asset_id) REFERENCES ast_assets(id),
    CONSTRAINT fk_stock_location FOREIGN KEY (location_id) REFERENCES ast_locations(id)
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_ast_asset_stock_asset_loc ON ast_asset_stock(asset_id, location_id) WHERE deleted = false;
CREATE INDEX IF NOT EXISTS idx_ast_asset_stock_asset ON ast_asset_stock(asset_id);
CREATE INDEX IF NOT EXISTS idx_ast_asset_stock_location ON ast_asset_stock(location_id);

-- 9. ast_asset_transactions
CREATE TABLE IF NOT EXISTS ast_asset_transactions (
    id                      UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version                 BIGINT          NOT NULL DEFAULT 0,
    asset_id                UUID            NOT NULL,
    transaction_type        VARCHAR(50)     NOT NULL, -- IN, OUT, TRANSFER, ADJUST, MAINTENANCE, DAMAGE
    quantity                DECIMAL(18,2)   NOT NULL,
    source_location_id      UUID,
    destination_location_id UUID,
    reference_type          VARCHAR(50), -- PURCHASE_ORDER, BOOKING, STOCK_AUDIT, DAMAGE_REPORT, MAINTENANCE
    reference_id            UUID,
    remarks                 VARCHAR(500),
    deleted                 BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at              TIMESTAMP,
    deleted_by              BIGINT,
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by              BIGINT,
    updated_at              TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by              BIGINT,
    CONSTRAINT fk_tx_asset FOREIGN KEY (asset_id) REFERENCES ast_assets(id),
    CONSTRAINT fk_tx_src_loc FOREIGN KEY (source_location_id) REFERENCES ast_locations(id),
    CONSTRAINT fk_tx_dst_loc FOREIGN KEY (destination_location_id) REFERENCES ast_locations(id)
);
CREATE INDEX IF NOT EXISTS idx_ast_tx_asset ON ast_asset_transactions(asset_id);
CREATE INDEX IF NOT EXISTS idx_ast_tx_src_loc ON ast_asset_transactions(source_location_id);
CREATE INDEX IF NOT EXISTS idx_ast_tx_dst_loc ON ast_asset_transactions(destination_location_id);
CREATE INDEX IF NOT EXISTS idx_ast_tx_reference ON ast_asset_transactions(reference_type, reference_id);

-- 10. ast_purchase_orders
CREATE TABLE IF NOT EXISTS ast_purchase_orders (
    id                      UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version                 BIGINT          NOT NULL DEFAULT 0,
    purchase_order_number   VARCHAR(100)    NOT NULL,
    supplier_id             UUID            NOT NULL,
    order_date              DATE            NOT NULL,
    expected_delivery_date  DATE,
    total_amount            DECIMAL(18,2)   NOT NULL DEFAULT 0.00,
    status                  VARCHAR(50)     NOT NULL, -- DRAFT, SUBMITTED, RECEIVED, CANCELLED
    deleted                 BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at              TIMESTAMP,
    deleted_by              BIGINT,
    created_at              TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by              BIGINT,
    updated_at              TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by              BIGINT,
    CONSTRAINT fk_po_supplier FOREIGN KEY (supplier_id) REFERENCES ast_suppliers(id)
);
CREATE UNIQUE INDEX IF NOT EXISTS uq_ast_po_number ON ast_purchase_orders(purchase_order_number) WHERE deleted = false;
CREATE INDEX IF NOT EXISTS idx_ast_po_supplier ON ast_purchase_orders(supplier_id);

-- 11. ast_purchase_order_items
CREATE TABLE IF NOT EXISTS ast_purchase_order_items (
    id                 UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version            BIGINT          NOT NULL DEFAULT 0,
    purchase_order_id  UUID            NOT NULL,
    asset_id           UUID            NOT NULL,
    quantity           DECIMAL(18,2)   NOT NULL,
    unit_price         DECIMAL(18,2)   NOT NULL,
    total_price        DECIMAL(18,2)   NOT NULL,
    deleted            BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at         TIMESTAMP,
    deleted_by         BIGINT,
    created_at         TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by         BIGINT,
    updated_at         TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by         BIGINT,
    CONSTRAINT fk_po_item_po FOREIGN KEY (purchase_order_id) REFERENCES ast_purchase_orders(id),
    CONSTRAINT fk_po_item_asset FOREIGN KEY (asset_id) REFERENCES ast_assets(id)
);
CREATE INDEX IF NOT EXISTS idx_ast_po_item_po ON ast_purchase_order_items(purchase_order_id);
CREATE INDEX IF NOT EXISTS idx_ast_po_item_asset ON ast_purchase_order_items(asset_id);

-- 12. move_asset_usage
CREATE TABLE IF NOT EXISTS move_asset_usage (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT          NOT NULL DEFAULT 0,
    booking_id  UUID            NOT NULL,
    start_date  DATE            NOT NULL,
    end_date    DATE,
    remarks     VARCHAR(500),
    status      VARCHAR(50)     NOT NULL, -- ISSUED, RETURNED, PARTIALLY_RETURNED
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  BIGINT,
    updated_at  TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by  BIGINT,
    CONSTRAINT fk_usage_booking FOREIGN KEY (booking_id) REFERENCES tbl_booking(id)
);
CREATE INDEX IF NOT EXISTS idx_move_usage_booking ON move_asset_usage(booking_id);

-- 13. move_asset_usage_items
CREATE TABLE IF NOT EXISTS move_asset_usage_items (
    id                  UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version             BIGINT          NOT NULL DEFAULT 0,
    move_asset_usage_id UUID            NOT NULL,
    asset_id            UUID            NOT NULL,
    quantity            DECIMAL(18,2)   NOT NULL,
    returned_quantity   DECIMAL(18,2)   NOT NULL DEFAULT 0.00,
    condition_on_issue  VARCHAR(255),
    condition_on_return VARCHAR(255),
    status              VARCHAR(50)     NOT NULL, -- ISSUED, RETURNED
    deleted             BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at          TIMESTAMP,
    deleted_by          BIGINT,
    created_at          TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by          BIGINT,
    updated_at          TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by          BIGINT,
    CONSTRAINT fk_usage_item_usage FOREIGN KEY (move_asset_usage_id) REFERENCES move_asset_usage(id),
    CONSTRAINT fk_usage_item_asset FOREIGN KEY (asset_id) REFERENCES ast_assets(id)
);
CREATE INDEX IF NOT EXISTS idx_move_usage_item_usage ON move_asset_usage_items(move_asset_usage_id);
CREATE INDEX IF NOT EXISTS idx_move_usage_item_asset ON move_asset_usage_items(asset_id);

-- 14. employee_asset_assignments
CREATE TABLE IF NOT EXISTS employee_asset_assignments (
    id                  UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version             BIGINT          NOT NULL DEFAULT 0,
    employee_id         UUID            NOT NULL,
    asset_id            UUID            NOT NULL,
    assigned_date       DATE            NOT NULL,
    returned_date       DATE,
    condition_on_issue  VARCHAR(255),
    condition_on_return VARCHAR(255),
    remarks             VARCHAR(500),
    status              VARCHAR(50)     NOT NULL, -- ASSIGNED, RETURNED
    deleted             BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at          TIMESTAMP,
    deleted_by          BIGINT,
    created_at          TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by          BIGINT,
    updated_at          TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by          BIGINT,
    CONSTRAINT fk_assignment_employee FOREIGN KEY (employee_id) REFERENCES tbl_users(id),
    CONSTRAINT fk_assignment_asset FOREIGN KEY (asset_id) REFERENCES ast_assets(id)
);
CREATE INDEX IF NOT EXISTS idx_emp_assignment_employee ON employee_asset_assignments(employee_id);
CREATE INDEX IF NOT EXISTS idx_emp_assignment_asset ON employee_asset_assignments(asset_id);

-- 15. ast_asset_maintenance
CREATE TABLE IF NOT EXISTS ast_asset_maintenance (
    id                UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version           BIGINT          NOT NULL DEFAULT 0,
    asset_id          UUID            NOT NULL,
    maintenance_type  VARCHAR(50)     NOT NULL, -- PREVENTIVE, REPAIR, CALIBRATION
    description       VARCHAR(500),
    scheduled_date    DATE            NOT NULL,
    start_date        DATE,
    completion_date   DATE,
    cost              DECIMAL(18,2),
    performed_by      VARCHAR(255),
    remarks           VARCHAR(500),
    status            VARCHAR(50)     NOT NULL, -- SCHEDULED, IN_PROGRESS, COMPLETED, CANCELLED
    deleted           BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at        TIMESTAMP,
    deleted_by        BIGINT,
    created_at        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by        BIGINT,
    updated_at        TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by        BIGINT,
    CONSTRAINT fk_maintenance_asset FOREIGN KEY (asset_id) REFERENCES ast_assets(id)
);
CREATE INDEX IF NOT EXISTS idx_ast_maintenance_asset ON ast_asset_maintenance(asset_id);

-- 16. ast_damage_reports
CREATE TABLE IF NOT EXISTS ast_damage_reports (
    id            UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version       BIGINT          NOT NULL DEFAULT 0,
    asset_id      UUID            NOT NULL,
    reported_by   UUID            NOT NULL,
    damage_date   DATE            NOT NULL,
    description   VARCHAR(500),
    severity      VARCHAR(50)     NOT NULL, -- LOW, MEDIUM, HIGH, CRITICAL
    status        VARCHAR(50)     NOT NULL, -- REPORTED, UNDER_REVIEW, REPAIRED, SCRAPPED
    action_taken  VARCHAR(500),
    deleted       BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at    TIMESTAMP,
    deleted_by    BIGINT,
    created_at    TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by    BIGINT,
    updated_at    TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by    BIGINT,
    CONSTRAINT fk_damage_asset FOREIGN KEY (asset_id) REFERENCES ast_assets(id),
    CONSTRAINT fk_damage_reporter FOREIGN KEY (reported_by) REFERENCES tbl_users(id)
);
CREATE INDEX IF NOT EXISTS idx_ast_damage_asset ON ast_damage_reports(asset_id);
CREATE INDEX IF NOT EXISTS idx_ast_damage_reporter ON ast_damage_reports(reported_by);

-- 17. ast_stock_audits
CREATE TABLE IF NOT EXISTS ast_stock_audits (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT          NOT NULL DEFAULT 0,
    audit_date  DATE            NOT NULL,
    auditor_id  UUID            NOT NULL,
    remarks     VARCHAR(500),
    status      VARCHAR(50)     NOT NULL, -- DRAFT, IN_PROGRESS, COMPLETED, CANCELLED
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  BIGINT,
    updated_at  TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by  BIGINT,
    CONSTRAINT fk_audit_auditor FOREIGN KEY (auditor_id) REFERENCES tbl_users(id)
);
CREATE INDEX IF NOT EXISTS idx_ast_audit_auditor ON ast_stock_audits(auditor_id);

-- 18. ast_stock_audit_items
CREATE TABLE IF NOT EXISTS ast_stock_audit_items (
    id                UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version           BIGINT          NOT NULL DEFAULT 0,
    stock_audit_id    UUID            NOT NULL,
    asset_id          UUID            NOT NULL,
    location_id       UUID            NOT NULL,
    expected_quantity DECIMAL(18,2)   NOT NULL,
    actual_quantity   DECIMAL(18,2)   NOT NULL,
    discrepancy       DECIMAL(18,2)   NOT NULL,
    remarks           VARCHAR(500),
    deleted           BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at        TIMESTAMP,
    deleted_by        BIGINT,
    created_at        TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by        BIGINT,
    updated_at        TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by        BIGINT,
    CONSTRAINT fk_audit_item_audit FOREIGN KEY (stock_audit_id) REFERENCES ast_stock_audits(id),
    CONSTRAINT fk_audit_item_asset FOREIGN KEY (asset_id) REFERENCES ast_assets(id),
    CONSTRAINT fk_audit_item_loc FOREIGN KEY (location_id) REFERENCES ast_locations(id)
);
CREATE INDEX IF NOT EXISTS idx_ast_audit_item_audit ON ast_stock_audit_items(stock_audit_id);
CREATE INDEX IF NOT EXISTS idx_ast_audit_item_asset ON ast_stock_audit_items(asset_id);
CREATE INDEX IF NOT EXISTS idx_ast_audit_item_loc ON ast_stock_audit_items(location_id);

-- 19. ast_asset_documents
CREATE TABLE IF NOT EXISTS ast_asset_documents (
    id             UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version        BIGINT          NOT NULL DEFAULT 0,
    asset_id       UUID            NOT NULL,
    document_name  VARCHAR(255)    NOT NULL,
    document_type  VARCHAR(100),
    file_path      VARCHAR(500)    NOT NULL,
    deleted        BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at     TIMESTAMP,
    deleted_by     BIGINT,
    created_at     TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by     BIGINT,
    updated_at     TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by     BIGINT,
    CONSTRAINT fk_document_asset FOREIGN KEY (asset_id) REFERENCES ast_assets(id)
);
CREATE INDEX IF NOT EXISTS idx_ast_document_asset ON ast_asset_documents(asset_id);

-- 20. ast_asset_images
CREATE TABLE IF NOT EXISTS ast_asset_images (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT          NOT NULL DEFAULT 0,
    asset_id    UUID            NOT NULL,
    image_name  VARCHAR(255)    NOT NULL,
    image_path  VARCHAR(500)    NOT NULL,
    is_primary  BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  BIGINT,
    updated_at  TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by  BIGINT,
    CONSTRAINT fk_image_asset FOREIGN KEY (asset_id) REFERENCES ast_assets(id)
);
CREATE INDEX IF NOT EXISTS idx_ast_image_asset ON ast_asset_images(asset_id);
