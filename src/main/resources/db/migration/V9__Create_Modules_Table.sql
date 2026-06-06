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

INSERT INTO modules (code, name) VALUES
('DASHBOARD', 'Dashboard & Analytics'),
('CRM', 'Customer Relationship Management'),
('OPERATIONS', 'Operations & Move Management'),
('SERVICES', 'Service Catalog & Pricing'),
('RESOURCES', 'Resource Management (Fleet, Crew, Warehouse, Assets, Inventory, Vendors)'),
('PROCUREMENT', 'Procurement & Vendor Management'),
('FINANCE', 'Finance & Accounting'),
('HR', 'Human Resource Management'),
('SUPPORT', 'Customer Support & Tickets'),
('REPORTS', 'Reports & Analytics'),
('ADMIN', 'Administration & System Settings'),
('ESTIMATION', 'Quick Estimation & Tools'),
('COMMUNICATION', 'Notifications & Messaging')
ON CONFLICT (code) DO UPDATE SET name = EXCLUDED.name;
