INSERT INTO sec_resources (code, name, module_id, created_at) VALUES
('DASHBOARD', 'Dashboard Overview', (SELECT id FROM modules WHERE code = 'DASHBOARD'), CURRENT_TIMESTAMP),

('CRM_CUSTOMERS', 'Customers Management', (SELECT id FROM modules WHERE code = 'CRM'), CURRENT_TIMESTAMP),
('CRM_LEADS', 'Leads Management', (SELECT id FROM modules WHERE code = 'CRM'), CURRENT_TIMESTAMP),
('CRM_PIPELINES', 'Pipelines Management', (SELECT id FROM modules WHERE code = 'CRM'), CURRENT_TIMESTAMP),

('OPERATIONS_MOVES', 'Moves Management', (SELECT id FROM modules WHERE code = 'OPERATIONS'), CURRENT_TIMESTAMP),
('OPERATIONS_TASKS', 'Tasks Management', (SELECT id FROM modules WHERE code = 'OPERATIONS'), CURRENT_TIMESTAMP),
('OPERATIONS_SCHEDULES', 'Schedules Management', (SELECT id FROM modules WHERE code = 'OPERATIONS'), CURRENT_TIMESTAMP),

('SERVICES_CATALOG', 'Service Catalog', (SELECT id FROM modules WHERE code = 'SERVICES'), CURRENT_TIMESTAMP),
('SERVICES_PRICING', 'Pricing Management', (SELECT id FROM modules WHERE code = 'SERVICES'), CURRENT_TIMESTAMP),

('RESOURCES_FLEET', 'Fleet Management', (SELECT id FROM modules WHERE code = 'RESOURCES'), CURRENT_TIMESTAMP),
('RESOURCES_CREW', 'Crew Management', (SELECT id FROM modules WHERE code = 'RESOURCES'), CURRENT_TIMESTAMP),
('RESOURCES_WAREHOUSE', 'Warehouse Management', (SELECT id FROM modules WHERE code = 'RESOURCES'), CURRENT_TIMESTAMP),
('RESOURCES_ASSETS', 'Assets Management', (SELECT id FROM modules WHERE code = 'RESOURCES'), CURRENT_TIMESTAMP),
('RESOURCES_VENDORS', 'Vendors Management', (SELECT id FROM modules WHERE code = 'RESOURCES'), CURRENT_TIMESTAMP),

('PROCUREMENT_ORDERS', 'Procurement Orders', (SELECT id FROM modules WHERE code = 'PROCUREMENT'), CURRENT_TIMESTAMP),
('PROCUREMENT_VENDORS', 'Procurement Vendors', (SELECT id FROM modules WHERE code = 'PROCUREMENT'), CURRENT_TIMESTAMP),

('FINANCE_INVOICES', 'Invoices Management', (SELECT id FROM modules WHERE code = 'FINANCE'), CURRENT_TIMESTAMP),
('FINANCE_PAYMENTS', 'Payments Management', (SELECT id FROM modules WHERE code = 'FINANCE'), CURRENT_TIMESTAMP),
('FINANCE_EXPENSES', 'Expenses Management', (SELECT id FROM modules WHERE code = 'FINANCE'), CURRENT_TIMESTAMP),

('HR_EMPLOYEES', 'Employees Management', (SELECT id FROM modules WHERE code = 'HR'), CURRENT_TIMESTAMP),
('HR_PAYROLL', 'Payroll Management', (SELECT id FROM modules WHERE code = 'HR'), CURRENT_TIMESTAMP),

('SUPPORT_TICKETS', 'Support Tickets', (SELECT id FROM modules WHERE code = 'SUPPORT'), CURRENT_TIMESTAMP),

('REPORTS_ANALYTICS', 'Analytics Reports', (SELECT id FROM modules WHERE code = 'REPORTS'), CURRENT_TIMESTAMP),

('ADMIN_USERS', 'Users Administration', (SELECT id FROM modules WHERE code = 'ADMIN'), CURRENT_TIMESTAMP),
('ADMIN_ROLES', 'Roles Administration', (SELECT id FROM modules WHERE code = 'ADMIN'), CURRENT_TIMESTAMP),
('ADMIN_SETTINGS', 'System Settings', (SELECT id FROM modules WHERE code = 'ADMIN'), CURRENT_TIMESTAMP),

('ESTIMATION_QUOTES', 'Quotes Estimation', (SELECT id FROM modules WHERE code = 'ESTIMATION'), CURRENT_TIMESTAMP),

('COMMUNICATION_MESSAGES', 'Messages & Notifications', (SELECT id FROM modules WHERE code = 'COMMUNICATION'), CURRENT_TIMESTAMP)
ON CONFLICT (code) DO UPDATE SET name = EXCLUDED.name, module_id = EXCLUDED.module_id;
