-- =============================================================================
-- V2__Create_Navigation_Menu_Table_And_Seed.sql
-- Create navigation menu items schema and seed enterprise module structure
-- =============================================================================

CREATE TABLE IF NOT EXISTS sec_menu_items (
    id            BIGSERIAL      PRIMARY KEY,
    name          VARCHAR(255)   NOT NULL,
    icon          VARCHAR(255),
    path          VARCHAR(255),
    section       VARCHAR(50)    NOT NULL DEFAULT 'SIDEBAR',
    parent_id     BIGINT,
    sort_order    INT            NOT NULL DEFAULT 0,
    permission_id BIGINT,
    active        BOOLEAN        NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_menu_parent     FOREIGN KEY (parent_id)     REFERENCES sec_menu_items(id) ON DELETE CASCADE,
    CONSTRAINT fk_menu_permission FOREIGN KEY (permission_id) REFERENCES sec_permissions(id) ON DELETE SET NULL,
    CONSTRAINT chk_menu_section   CHECK (section IN ('SIDEBAR', 'TOPBAR', 'PROFILE'))
);

CREATE INDEX idx_menu_parent ON sec_menu_items(parent_id);
CREATE INDEX idx_menu_section_active ON sec_menu_items(section, active, sort_order);

-- =============================================================================
-- SEED SIDEBAR MODULES (LEVEL 1)
-- =============================================================================

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1, 'Dashboard', 'dashboard', '/dashboard', 'SIDEBAR', NULL, 10, NULL, TRUE),
(2, 'CRM', 'crm', '/crm', 'SIDEBAR', NULL, 20, NULL, TRUE),
(3, 'Operations', 'operations', '/operations', 'SIDEBAR', NULL, 30, NULL, TRUE),
(4, 'Services', 'services', '/services', 'SIDEBAR', NULL, 40, NULL, TRUE),
(5, 'Resources', 'resources', '/resources', 'SIDEBAR', NULL, 50, NULL, TRUE),
(6, 'Procurement', 'procurement', '/procurement', 'SIDEBAR', NULL, 60, NULL, TRUE),
(7, 'Finance', 'finance', '/finance', 'SIDEBAR', NULL, 70, NULL, TRUE),
(8, 'HR', 'hr', '/hr', 'SIDEBAR', NULL, 80, NULL, TRUE),
(9, 'Support', 'support', '/support', 'SIDEBAR', NULL, 90, NULL, TRUE),
(10, 'Reports', 'reports', '/reports', 'SIDEBAR', NULL, 100, NULL, TRUE),
(11, 'Administration', 'admin', '/admin', 'SIDEBAR', NULL, 110, NULL, TRUE);

-- =============================================================================
-- SEED SUBMENUS & MENUS FOR MODULES
-- =============================================================================

-- 1. Dashboard Submenus (Level 2)
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
('Executive Dashboard', 'chart-bar', '/dashboard/executive', 'SIDEBAR', 1, 10, NULL, TRUE),
('Operations Dashboard', 'activity', '/dashboard/operations', 'SIDEBAR', 1, 20, NULL, TRUE),
('Sales Dashboard', 'trending-up', '/dashboard/sales', 'SIDEBAR', 1, 30, NULL, TRUE),
('Finance Dashboard', 'dollar-sign', '/dashboard/finance', 'SIDEBAR', 1, 40, NULL, TRUE),
('HR Dashboard', 'users', '/dashboard/hr', 'SIDEBAR', 1, 50, NULL, TRUE),
('Fleet Dashboard', 'truck', '/dashboard/fleet', 'SIDEBAR', 1, 60, NULL, TRUE);

-- 2. CRM Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(20, 'Leads', 'funnel', '/crm/leads', 'SIDEBAR', 2, 10, NULL, TRUE),
(21, 'Customers', 'user-check', '/crm/customers', 'SIDEBAR', 2, 20, NULL, TRUE),
(22, 'Surveys', 'clipboard-list', '/crm/surveys', 'SIDEBAR', 2, 30, NULL, TRUE),
(23, 'Quotations', 'file-text', '/crm/quotations', 'SIDEBAR', 2, 40, NULL, TRUE);

-- 2. CRM Submenus (Level 3)
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
-- Leads
('All Leads', 'list', '/crm/leads/all', 'SIDEBAR', 20, 10, NULL, TRUE),
('New Leads', 'plus', '/crm/leads/new', 'SIDEBAR', 20, 20, NULL, TRUE),
('Follow Ups', 'phone-call', '/crm/leads/follow-ups', 'SIDEBAR', 20, 30, NULL, TRUE),
('Lead Sources', 'globe', '/crm/leads/sources', 'SIDEBAR', 20, 40, NULL, TRUE),
('Lead Statuses', 'check-square', '/crm/leads/statuses', 'SIDEBAR', 20, 50, NULL, TRUE),
-- Customers
('All Customers', 'users', '/crm/customers/all', 'SIDEBAR', 21, 10, NULL, TRUE),
('Residential Customers', 'home', '/crm/customers/residential', 'SIDEBAR', 21, 20, NULL, TRUE),
('Commercial Customers', 'briefcase', '/crm/customers/commercial', 'SIDEBAR', 21, 30, NULL, TRUE),
('Customer Addresses', 'map-pin', '/crm/customers/addresses', 'SIDEBAR', 21, 40, NULL, TRUE),
('Customer Documents', 'file', '/crm/customers/documents', 'SIDEBAR', 21, 50, NULL, TRUE),
-- Surveys
('Survey Requests', 'mail', '/crm/surveys/requests', 'SIDEBAR', 22, 10, NULL, TRUE),
('Scheduled Surveys', 'calendar', '/crm/surveys/scheduled', 'SIDEBAR', 22, 20, NULL, TRUE),
('Completed Surveys', 'check', '/crm/surveys/completed', 'SIDEBAR', 22, 30, NULL, TRUE),
('Virtual Surveys', 'video', '/crm/surveys/virtual', 'SIDEBAR', 22, 40, NULL, TRUE),
('Survey Reports', 'file-bar-chart', '/crm/surveys/reports', 'SIDEBAR', 22, 50, NULL, TRUE),
-- Quotations
('Draft Quotes', 'edit', '/crm/quotations/draft', 'SIDEBAR', 23, 10, NULL, TRUE),
('Sent Quotes', 'send', '/crm/quotations/sent', 'SIDEBAR', 23, 20, NULL, TRUE),
('Approved Quotes', 'thumbs-up', '/crm/quotations/approved', 'SIDEBAR', 23, 30, NULL, TRUE),
('Rejected Quotes', 'thumbs-down', '/crm/quotations/rejected', 'SIDEBAR', 23, 40, NULL, TRUE),
('Expired Quotes', 'clock', '/crm/quotations/expired', 'SIDEBAR', 23, 50, NULL, TRUE),
('Quote Templates', 'copy', '/crm/quotations/templates', 'SIDEBAR', 23, 60, NULL, TRUE);

-- 3. Operations Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(30, 'Bookings', 'calendar-check', '/operations/bookings', 'SIDEBAR', 3, 10, NULL, TRUE),
(31, 'Move Orders', 'package', '/operations/move-orders', 'SIDEBAR', 3, 20, NULL, TRUE),
(32, 'Dispatch Center', 'navigation', '/operations/dispatch', 'SIDEBAR', 3, 30, NULL, TRUE),
(33, 'Move Tracking', 'map', '/operations/tracking', 'SIDEBAR', 3, 40, NULL, TRUE),
(34, 'Inventory Assessment', 'box', '/operations/inventory-assessment', 'SIDEBAR', 3, 50, NULL, TRUE);

-- 3. Operations Submenus (Level 3)
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
-- Bookings
('New Bookings', 'plus-circle', '/operations/bookings/new', 'SIDEBAR', 30, 10, NULL, TRUE),
('Confirmed Bookings', 'check-circle', '/operations/bookings/confirmed', 'SIDEBAR', 30, 20, NULL, TRUE),
('Assigned Bookings', 'user-plus', '/operations/bookings/assigned', 'SIDEBAR', 30, 30, NULL, TRUE),
('Completed Bookings', 'archive', '/operations/bookings/completed', 'SIDEBAR', 30, 40, NULL, TRUE),
('Cancelled Bookings', 'x-circle', '/operations/bookings/cancelled', 'SIDEBAR', 30, 50, NULL, TRUE),
-- Move Orders
('Active Moves', 'play', '/operations/moves/active', 'SIDEBAR', 31, 10, NULL, TRUE),
('Scheduled Moves', 'calendar', '/operations/moves/scheduled', 'SIDEBAR', 31, 20, NULL, TRUE),
('In Progress', 'refresh-cw', '/operations/moves/in-progress', 'SIDEBAR', 31, 30, NULL, TRUE),
('Completed', 'check-all', '/operations/moves/completed', 'SIDEBAR', 31, 40, NULL, TRUE),
('Cancelled', 'x', '/operations/moves/cancelled', 'SIDEBAR', 31, 50, NULL, TRUE),
-- Dispatch Center
('Dispatch Board', 'layers', '/operations/dispatch/board', 'SIDEBAR', 32, 10, NULL, TRUE),
('Assign Crew', 'users', '/operations/dispatch/assign-crew', 'SIDEBAR', 32, 20, NULL, TRUE),
('Assign Vehicles', 'truck', '/operations/dispatch/assign-vehicles', 'SIDEBAR', 32, 30, NULL, TRUE),
('Route Planning', 'compass', '/operations/dispatch/routes', 'SIDEBAR', 32, 40, NULL, TRUE),
-- Move Tracking
('Live Tracking', 'eye', '/operations/tracking/live', 'SIDEBAR', 33, 10, NULL, TRUE),
('Delayed Jobs', 'alert-triangle', '/operations/tracking/delayed', 'SIDEBAR', 33, 20, NULL, TRUE),
('Job Timeline', 'list', '/operations/tracking/timeline', 'SIDEBAR', 33, 30, NULL, TRUE),
('Activity Logs', 'file-text', '/operations/tracking/logs', 'SIDEBAR', 33, 40, NULL, TRUE),
-- Inventory Assessment
('Property Inventory', 'home', '/operations/inventory/property', 'SIDEBAR', 34, 10, NULL, TRUE),
('Room Inventory', 'columns', '/operations/inventory/room', 'SIDEBAR', 34, 20, NULL, TRUE),
('Special Items', 'star', '/operations/inventory/special', 'SIDEBAR', 34, 30, NULL, TRUE),
('Weight Estimation', 'activity', '/operations/inventory/weight', 'SIDEBAR', 34, 40, NULL, TRUE);

-- 4. Services Menus (Level 2)
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
('Service Categories', 'grid', '/services/categories', 'SIDEBAR', 4, 10, NULL, TRUE),
('Service Types', 'sliders', '/services/types', 'SIDEBAR', 4, 20, NULL, TRUE),
('Add-On Services', 'plus-square', '/services/add-ons', 'SIDEBAR', 4, 30, NULL, TRUE),
('Pricing Management', 'dollar-sign', '/services/pricing', 'SIDEBAR', 4, 40, NULL, TRUE),
('Property Types', 'layers', '/services/property-types', 'SIDEBAR', 4, 50, NULL, TRUE),
('Zones & Coverage', 'map-pin', '/services/zones', 'SIDEBAR', 4, 60, NULL, TRUE);

-- 5. Resources Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(50, 'Fleet Management', 'truck', '/resources/fleet', 'SIDEBAR', 5, 10, NULL, TRUE),
(51, 'Crew Management', 'users', '/resources/crew', 'SIDEBAR', 5, 20, NULL, TRUE),
(52, 'Warehouses', 'database', '/resources/warehouses', 'SIDEBAR', 5, 30, NULL, TRUE),
(53, 'Assets', 'tool', '/resources/assets', 'SIDEBAR', 5, 40, NULL, TRUE),
(54, 'Inventory', 'box', '/resources/inventory', 'SIDEBAR', 5, 50, NULL, TRUE),
(55, 'Vendors', 'shopping-cart', '/resources/vendors', 'SIDEBAR', 5, 60, NULL, TRUE);

-- 5. Resources Submenus (Level 3)
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
-- Fleet
('Vehicles', 'truck', '/resources/fleet/vehicles', 'SIDEBAR', 50, 10, NULL, TRUE),
('Vehicle Categories', 'layers', '/resources/fleet/categories', 'SIDEBAR', 50, 20, NULL, TRUE),
('Maintenance', 'tool', '/resources/fleet/maintenance', 'SIDEBAR', 50, 30, NULL, TRUE),
('Fuel Logs', 'droplet', '/resources/fleet/fuel', 'SIDEBAR', 50, 40, NULL, TRUE),
('Vehicle Insurance', 'shield', '/resources/fleet/insurance', 'SIDEBAR', 50, 50, NULL, TRUE),
('Vehicle Documents', 'file', '/resources/fleet/documents', 'SIDEBAR', 50, 60, NULL, TRUE),
-- Crew
('Crew Members', 'user', '/resources/crew/members', 'SIDEBAR', 51, 10, NULL, TRUE),
('Teams', 'users', '/resources/crew/teams', 'SIDEBAR', 51, 20, NULL, TRUE),
('Crew Schedules', 'calendar', '/resources/crew/schedules', 'SIDEBAR', 51, 30, NULL, TRUE),
('Availability', 'clock', '/resources/crew/availability', 'SIDEBAR', 51, 40, NULL, TRUE),
('Performance Ratings', 'star', '/resources/crew/performance', 'SIDEBAR', 51, 50, NULL, TRUE),
-- Warehouses
('Warehouses', 'database', '/resources/warehouses/all', 'SIDEBAR', 52, 10, NULL, TRUE),
('Storage Units', 'layers', '/resources/warehouses/units', 'SIDEBAR', 52, 20, NULL, TRUE),
('Stored Items', 'box', '/resources/warehouses/items', 'SIDEBAR', 52, 30, NULL, TRUE),
('Storage Contracts', 'file-text', '/resources/warehouses/contracts', 'SIDEBAR', 52, 40, NULL, TRUE),
('Warehouse Transfers', 'move', '/resources/warehouses/transfers', 'SIDEBAR', 52, 50, NULL, TRUE),
-- Assets
('Asset Registry', 'list', '/resources/assets/registry', 'SIDEBAR', 53, 10, NULL, TRUE),
('Asset Categories', 'grid', '/resources/assets/categories', 'SIDEBAR', 53, 20, NULL, TRUE),
('Asset Assignment', 'user-check', '/resources/assets/assignment', 'SIDEBAR', 53, 30, NULL, TRUE),
('Asset Maintenance', 'settings', '/resources/assets/maintenance', 'SIDEBAR', 53, 40, NULL, TRUE),
('Depreciation', 'trending-down', '/resources/assets/depreciation', 'SIDEBAR', 53, 50, NULL, TRUE),
('Asset Disposal', 'trash', '/resources/assets/disposal', 'SIDEBAR', 53, 60, NULL, TRUE),
-- Inventory
('Packing Materials', 'archive', '/resources/inventory/packing', 'SIDEBAR', 54, 10, NULL, TRUE),
('Consumables', 'box', '/resources/inventory/consumables', 'SIDEBAR', 54, 20, NULL, TRUE),
('Stock Levels', 'bar-chart-2', '/resources/inventory/stock', 'SIDEBAR', 54, 30, NULL, TRUE),
('Stock Transfers', 'repeat', '/resources/inventory/transfers', 'SIDEBAR', 54, 40, NULL, TRUE),
('Stock Adjustments', 'sliders', '/resources/inventory/adjustments', 'SIDEBAR', 54, 50, NULL, TRUE),
-- Vendors
('Vendors', 'briefcase', '/resources/vendors/all', 'SIDEBAR', 55, 10, NULL, TRUE),
('Vendor Contracts', 'file-text', '/resources/vendors/contracts', 'SIDEBAR', 55, 20, NULL, TRUE),
('Vendor Performance', 'star', '/resources/vendors/performance', 'SIDEBAR', 55, 30, NULL, TRUE),
('Vendor Payments', 'credit-card', '/resources/vendors/payments', 'SIDEBAR', 55, 40, NULL, TRUE);

-- 6. Procurement Menus (Level 2)
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
('Purchase Requests', 'file-plus', '/procurement/requests', 'SIDEBAR', 6, 10, NULL, TRUE),
('RFQs', 'file-text', '/procurement/rfqs', 'SIDEBAR', 6, 20, NULL, TRUE),
('Purchase Orders', 'check-square', '/procurement/orders', 'SIDEBAR', 6, 30, NULL, TRUE),
('Goods Received Notes', 'clipboard', '/procurement/grns', 'SIDEBAR', 6, 40, NULL, TRUE),
('Purchase Invoices', 'dollar-sign', '/procurement/invoices', 'SIDEBAR', 6, 50, NULL, TRUE),
('Vendor Bills', 'file-minus', '/procurement/bills', 'SIDEBAR', 6, 60, NULL, TRUE),
('Contract Management', 'shield', '/procurement/contracts', 'SIDEBAR', 6, 70, NULL, TRUE),
('Procurement Reports', 'bar-chart', '/procurement/reports', 'SIDEBAR', 6, 80, NULL, TRUE);

-- 7. Finance Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(70, 'Invoices', 'file-invoice', '/finance/invoices', 'SIDEBAR', 7, 10, NULL, TRUE),
(71, 'Payments', 'credit-card', '/finance/payments', 'SIDEBAR', 7, 20, NULL, TRUE),
(72, 'Expenses', 'file-minus', '/finance/expenses', 'SIDEBAR', 7, 30, NULL, TRUE),
(73, 'Accounting', 'book-open', '/finance/accounting', 'SIDEBAR', 7, 40, NULL, TRUE),
(74, 'Taxes', 'percent', '/finance/taxes', 'SIDEBAR', 7, 50, NULL, TRUE),
(75, 'Insurance & Claims', 'shield', '/finance/insurance', 'SIDEBAR', 7, 60, NULL, TRUE);

-- 7. Finance Submenus (Level 3)
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
-- Invoices
('Customer Invoices', 'user', '/finance/invoices/customer', 'SIDEBAR', 70, 10, NULL, TRUE),
('Draft Invoices', 'edit', '/finance/invoices/draft', 'SIDEBAR', 70, 20, NULL, TRUE),
('Paid Invoices', 'check-circle', '/finance/invoices/paid', 'SIDEBAR', 70, 30, NULL, TRUE),
('Overdue Invoices', 'alert-circle', '/finance/invoices/overdue', 'SIDEBAR', 70, 40, NULL, TRUE),
-- Payments
('Customer Payments', 'arrow-down-left', '/finance/payments/customer', 'SIDEBAR', 71, 10, NULL, TRUE),
('Vendor Payments', 'arrow-up-right', '/finance/payments/vendor', 'SIDEBAR', 71, 20, NULL, TRUE),
('Refunds', 'rotate-ccw', '/finance/payments/refunds', 'SIDEBAR', 71, 30, NULL, TRUE),
('Payment Methods', 'settings', '/finance/payments/methods', 'SIDEBAR', 71, 40, NULL, TRUE),
-- Expenses
('Fuel Expenses', 'droplet', '/finance/expenses/fuel', 'SIDEBAR', 72, 10, NULL, TRUE),
('Maintenance Expenses', 'tool', '/finance/expenses/maintenance', 'SIDEBAR', 72, 20, NULL, TRUE),
('Payroll Expenses', 'users', '/finance/expenses/payroll', 'SIDEBAR', 72, 30, NULL, TRUE),
('Other Expenses', 'file-text', '/finance/expenses/other', 'SIDEBAR', 72, 40, NULL, TRUE),
-- Accounting
('Chart Of Accounts', 'list', '/finance/accounting/coa', 'SIDEBAR', 73, 10, NULL, TRUE),
('Journal Entries', 'book', '/finance/accounting/journal', 'SIDEBAR', 73, 20, NULL, TRUE),
('General Ledger', 'book-open', '/finance/accounting/ledger', 'SIDEBAR', 73, 30, NULL, TRUE),
('Trial Balance', 'activity', '/finance/accounting/trial-balance', 'SIDEBAR', 73, 40, NULL, TRUE),
('Fiscal Years', 'calendar', '/finance/accounting/fiscal-years', 'SIDEBAR', 73, 50, NULL, TRUE),
-- Taxes
('Tax Rates', 'percent', '/finance/taxes/rates', 'SIDEBAR', 74, 10, NULL, TRUE),
('Tax Rules', 'settings', '/finance/taxes/rules', 'SIDEBAR', 74, 20, NULL, TRUE),
('Tax Reports', 'file-text', '/finance/taxes/reports', 'SIDEBAR', 74, 30, NULL, TRUE),
-- Insurance & Claims
('Insurance Policies', 'file-text', '/finance/insurance/policies', 'SIDEBAR', 75, 10, NULL, TRUE),
('Claims', 'file-plus', '/finance/insurance/claims', 'SIDEBAR', 75, 20, NULL, TRUE),
('Damaged Items', 'box', '/finance/insurance/damaged-items', 'SIDEBAR', 75, 30, NULL, TRUE),
('Claim Settlements', 'check-square', '/finance/insurance/settlements', 'SIDEBAR', 75, 40, NULL, TRUE);

-- 8. HR Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(80, 'Employees', 'users', '/hr/employees', 'SIDEBAR', 8, 10, NULL, TRUE),
(81, 'Attendance', 'clock', '/hr/attendance', 'SIDEBAR', 8, 20, NULL, TRUE),
(82, 'Leave Management', 'calendar', '/hr/leave', 'SIDEBAR', 8, 30, NULL, TRUE),
(83, 'Payroll', 'dollar-sign', '/hr/payroll', 'SIDEBAR', 8, 40, NULL, TRUE),
(84, 'Recruitment', 'user-plus', '/hr/recruitment', 'SIDEBAR', 8, 50, NULL, TRUE),
(85, 'Performance', 'award', '/hr/performance', 'SIDEBAR', 8, 60, NULL, TRUE);

-- 8. HR Submenus (Level 3)
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
-- Employees
('Employee Directory', 'user', '/hr/employees/directory', 'SIDEBAR', 80, 10, NULL, TRUE),
('Designations', 'briefcase', '/hr/employees/designations', 'SIDEBAR', 80, 20, NULL, TRUE),
('Departments', 'layers', '/hr/employees/departments', 'SIDEBAR', 80, 30, NULL, TRUE),
('Contracts', 'file-text', '/hr/employees/contracts', 'SIDEBAR', 80, 40, NULL, TRUE),
('Documents', 'file', '/hr/employees/documents', 'SIDEBAR', 80, 50, NULL, TRUE),
-- Attendance
('Daily Attendance', 'check-square', '/hr/attendance/daily', 'SIDEBAR', 81, 10, NULL, TRUE),
('Attendance Logs', 'list', '/hr/attendance/logs', 'SIDEBAR', 81, 20, NULL, TRUE),
('Attendance Reports', 'file-text', '/hr/attendance/reports', 'SIDEBAR', 81, 30, NULL, TRUE),
-- Leave Management
('Leave Requests', 'mail', '/hr/leave/requests', 'SIDEBAR', 82, 10, NULL, TRUE),
('Leave Types', 'sliders', '/hr/leave/types', 'SIDEBAR', 82, 20, NULL, TRUE),
('Leave Balances', 'activity', '/hr/leave/balances', 'SIDEBAR', 82, 30, NULL, TRUE),
('Leave Calendar', 'calendar', '/hr/leave/calendar', 'SIDEBAR', 82, 40, NULL, TRUE),
-- Payroll
('Salary Structures', 'layers', '/hr/payroll/structures', 'SIDEBAR', 83, 10, NULL, TRUE),
('Payroll Processing', 'refresh-cw', '/hr/payroll/process', 'SIDEBAR', 83, 20, NULL, TRUE),
('Payslips', 'file-text', '/hr/payroll/payslips', 'SIDEBAR', 83, 30, NULL, TRUE),
('Bonuses', 'gift', '/hr/payroll/bonuses', 'SIDEBAR', 83, 40, NULL, TRUE),
('Deductions', 'minus-circle', '/hr/payroll/deductions', 'SIDEBAR', 83, 50, NULL, TRUE),
-- Recruitment
('Job Positions', 'briefcase', '/hr/recruitment/positions', 'SIDEBAR', 84, 10, NULL, TRUE),
('Applicants', 'users', '/hr/recruitment/applicants', 'SIDEBAR', 84, 20, NULL, TRUE),
('Interviews', 'calendar', '/hr/recruitment/interviews', 'SIDEBAR', 84, 30, NULL, TRUE),
('Hiring Pipeline', 'activity', '/hr/recruitment/pipeline', 'SIDEBAR', 84, 40, NULL, TRUE),
-- Performance
('Reviews', 'check-square', '/hr/performance/reviews', 'SIDEBAR', 85, 10, NULL, TRUE),
('KPIs', 'trending-up', '/hr/performance/kpis', 'SIDEBAR', 85, 20, NULL, TRUE),
('Promotions', 'trending-up', '/hr/performance/promotions', 'SIDEBAR', 85, 30, NULL, TRUE),
('Warnings', 'alert-octagon', '/hr/performance/warnings', 'SIDEBAR', 85, 40, NULL, TRUE);

-- 9. Support Menus (Level 2)
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
('Tickets', 'mail', '/support/tickets', 'SIDEBAR', 9, 10, NULL, TRUE),
('Complaints', 'frown', '/support/complaints', 'SIDEBAR', 9, 20, NULL, TRUE),
('Escalations', 'alert-circle', '/support/escalations', 'SIDEBAR', 9, 30, NULL, TRUE),
('Customer Feedback', 'star', '/support/feedback', 'SIDEBAR', 9, 40, NULL, TRUE),
('Knowledge Base', 'book', '/support/kb', 'SIDEBAR', 9, 50, NULL, TRUE),
('Announcements', 'volume-2', '/support/announcements', 'SIDEBAR', 9, 60, NULL, TRUE);

-- 10. Reports Menus (Level 2)
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
('Sales Reports', 'bar-chart-2', '/reports/sales', 'SIDEBAR', 10, 10, NULL, TRUE),
('Customer Reports', 'users', '/reports/customers', 'SIDEBAR', 10, 20, NULL, TRUE),
('Lead Reports', 'funnel', '/reports/leads', 'SIDEBAR', 10, 30, NULL, TRUE),
('Booking Reports', 'calendar', '/reports/bookings', 'SIDEBAR', 10, 40, NULL, TRUE),
('Move Reports', 'package', '/reports/moves', 'SIDEBAR', 10, 50, NULL, TRUE),
('Fleet Reports', 'truck', '/reports/fleet', 'SIDEBAR', 10, 60, NULL, TRUE),
('Warehouse Reports', 'database', '/reports/warehouses', 'SIDEBAR', 10, 70, NULL, TRUE),
('Asset Reports', 'tool', '/reports/assets', 'SIDEBAR', 10, 80, NULL, TRUE),
('HR Reports', 'users', '/reports/hr', 'SIDEBAR', 10, 90, NULL, TRUE),
('Payroll Reports', 'dollar-sign', '/reports/payroll', 'SIDEBAR', 10, 100, NULL, TRUE),
('Financial Reports', 'trending-up', '/reports/financial', 'SIDEBAR', 10, 110, NULL, TRUE),
('Tax Reports', 'percent', '/reports/taxes', 'SIDEBAR', 10, 120, NULL, TRUE),
('Custom Reports', 'settings', '/reports/custom', 'SIDEBAR', 10, 130, NULL, TRUE);

-- 11. Administration Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(110, 'User Management', 'users', '/admin/users', 'SIDEBAR', 11, 10, NULL, TRUE),
(111, 'Organization', 'briefcase', '/admin/organization', 'SIDEBAR', 11, 20, NULL, TRUE),
(112, 'Workflow Configuration', 'git-branch', '/admin/workflows', 'SIDEBAR', 11, 30, NULL, TRUE),
(113, 'Notifications', 'bell', '/admin/notifications', 'SIDEBAR', 11, 40, NULL, TRUE),
(114, 'Document Management', 'file-text', '/admin/documents', 'SIDEBAR', 11, 50, NULL, TRUE),
(115, 'Integrations', 'sliders', '/admin/integrations', 'SIDEBAR', 11, 60, NULL, TRUE);

-- 11. Administration Submenus (Level 3)
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
-- User Management
('Users', 'user', '/admin/users/all', 'SIDEBAR', 110, 10, NULL, TRUE),
('Roles', 'shield', '/admin/users/roles', 'SIDEBAR', 110, 20, NULL, TRUE),
('Permissions', 'key', '/admin/users/permissions', 'SIDEBAR', 110, 30, NULL, TRUE),
('User Groups', 'users', '/admin/users/groups', 'SIDEBAR', 110, 40, NULL, TRUE),
-- Organization
('Branches', 'home', '/admin/org/branches', 'SIDEBAR', 111, 10, NULL, TRUE),
('Departments', 'layers', '/admin/org/departments', 'SIDEBAR', 111, 20, NULL, TRUE),
('Teams', 'users', '/admin/org/teams', 'SIDEBAR', 111, 30, NULL, TRUE),
('Locations', 'map-pin', '/admin/org/locations', 'SIDEBAR', 111, 40, NULL, TRUE),
-- Workflow Configuration
('Approval Flows', 'check-square', '/admin/workflows/approvals', 'SIDEBAR', 112, 10, NULL, TRUE),
('Booking Workflow', 'calendar', '/admin/workflows/bookings', 'SIDEBAR', 112, 20, NULL, TRUE),
('Quote Workflow', 'file-text', '/admin/workflows/quotes', 'SIDEBAR', 112, 30, NULL, TRUE),
('Notification Workflow', 'bell', '/admin/workflows/notifications', 'SIDEBAR', 112, 40, NULL, TRUE),
-- Notifications
('Email Templates', 'mail', '/admin/notifications/email', 'SIDEBAR', 113, 10, NULL, TRUE),
('SMS Templates', 'phone', '/admin/notifications/sms', 'SIDEBAR', 113, 20, NULL, TRUE),
('WhatsApp Templates', 'message-square', '/admin/notifications/whatsapp', 'SIDEBAR', 113, 30, NULL, TRUE),
('Push Templates', 'bell', '/admin/notifications/push', 'SIDEBAR', 113, 40, NULL, TRUE),
-- Document Management
('Document Types', 'file', '/admin/documents/types', 'SIDEBAR', 114, 10, NULL, TRUE),
('Templates', 'copy', '/admin/documents/templates', 'SIDEBAR', 114, 20, NULL, TRUE),
('Storage Settings', 'database', '/admin/documents/storage', 'SIDEBAR', 114, 30, NULL, TRUE),
-- Integrations
('Payment Gateways', 'credit-card', '/admin/integrations/payments', 'SIDEBAR', 115, 10, NULL, TRUE),
('SMS Providers', 'phone', '/admin/integrations/sms', 'SIDEBAR', 115, 20, NULL, TRUE),
('Email Providers', 'mail', '/admin/integrations/email', 'SIDEBAR', 115, 30, NULL, TRUE),
('Google Maps', 'map', '/admin/integrations/maps', 'SIDEBAR', 115, 40, NULL, TRUE),
('Webhooks', 'link', '/admin/integrations/webhooks', 'SIDEBAR', 115, 50, NULL, TRUE),
-- Administration Standalones
('Audit Logs', 'file-text', '/admin/audit-logs', 'SIDEBAR', 11, 70, NULL, TRUE),
('System Settings', 'settings', '/admin/system-settings', 'SIDEBAR', 11, 80, NULL, TRUE),
('Activity Logs', 'activity', '/admin/activity-logs', 'SIDEBAR', 11, 90, NULL, TRUE);

-- =============================================================================
-- SEED TOPBAR & PROFILE SECTIONS
-- =============================================================================

-- Topbar Items
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
('Quick Estimation', 'calculator', '/estimate/quick', 'TOPBAR', NULL, 10, NULL, TRUE),
('Support Chat', 'chat-bubble', '/support/live-chat', 'TOPBAR', NULL, 20, NULL, TRUE);

-- Profile Items
INSERT INTO sec_menu_items (name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
('My Profile', 'user-profile', '/user/profile', 'PROFILE', NULL, 10, NULL, TRUE),
('Account Settings', 'settings-gear', '/user/settings', 'PROFILE', NULL, 20, NULL, TRUE),
('Logout', 'sign-out', '/logout', 'PROFILE', NULL, 30, NULL, TRUE);
