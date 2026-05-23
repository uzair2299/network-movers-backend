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
-- SEED SUBMENUS & MENUS FOR MODULES (LEVEL 2)
-- =============================================================================

-- 1. Dashboard Submenus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(101, 'Executive Dashboard', 'chart-bar', '/dashboard/executive', 'SIDEBAR', 1, 10, NULL, TRUE),
(102, 'Operations Dashboard', 'activity', '/dashboard/operations', 'SIDEBAR', 1, 20, NULL, TRUE),
(103, 'Sales Dashboard', 'trending-up', '/dashboard/sales', 'SIDEBAR', 1, 30, NULL, TRUE),
(104, 'Finance Dashboard', 'dollar-sign', '/dashboard/finance', 'SIDEBAR', 1, 40, NULL, TRUE),
(105, 'HR Dashboard', 'users', '/dashboard/hr', 'SIDEBAR', 1, 50, NULL, TRUE),
(106, 'Fleet Dashboard', 'truck', '/dashboard/fleet', 'SIDEBAR', 1, 60, NULL, TRUE);

-- 2. CRM Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(201, 'Leads', 'funnel', '/crm/leads', 'SIDEBAR', 2, 10, NULL, TRUE),
(202, 'Customers', 'user-check', '/crm/customers', 'SIDEBAR', 2, 20, NULL, TRUE),
(203, 'Surveys', 'clipboard-list', '/crm/surveys', 'SIDEBAR', 2, 30, NULL, TRUE),
(204, 'Quotations', 'file-text', '/crm/quotations', 'SIDEBAR', 2, 40, NULL, TRUE);

-- 3. Operations Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(301, 'Bookings', 'calendar-check', '/operations/bookings', 'SIDEBAR', 3, 10, NULL, TRUE),
(302, 'Move Orders', 'package', '/operations/move-orders', 'SIDEBAR', 3, 20, NULL, TRUE),
(303, 'Dispatch Center', 'navigation', '/operations/dispatch', 'SIDEBAR', 3, 30, NULL, TRUE),
(304, 'Move Tracking', 'map', '/operations/tracking', 'SIDEBAR', 3, 40, NULL, TRUE),
(305, 'Inventory Assessment', 'box', '/operations/inventory-assessment', 'SIDEBAR', 3, 50, NULL, TRUE);

-- 4. Services Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(401, 'Service Categories', 'grid', '/services/categories', 'SIDEBAR', 4, 10, NULL, TRUE),
(402, 'Service Types', 'sliders', '/services/types', 'SIDEBAR', 4, 20, NULL, TRUE),
(403, 'Add-On Services', 'plus-square', '/services/add-ons', 'SIDEBAR', 4, 30, NULL, TRUE),
(404, 'Pricing Management', 'dollar-sign', '/services/pricing', 'SIDEBAR', 4, 40, NULL, TRUE),
(405, 'Property Types', 'layers', '/services/property-types', 'SIDEBAR', 4, 50, NULL, TRUE),
(406, 'Zones & Coverage', 'map-pin', '/services/zones', 'SIDEBAR', 4, 60, NULL, TRUE);

-- 5. Resources Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(501, 'Fleet Management', 'truck', '/resources/fleet', 'SIDEBAR', 5, 10, NULL, TRUE),
(502, 'Crew Management', 'users', '/resources/crew', 'SIDEBAR', 5, 20, NULL, TRUE),
(503, 'Warehouses', 'database', '/resources/warehouses', 'SIDEBAR', 5, 30, NULL, TRUE),
(504, 'Assets', 'tool', '/resources/assets', 'SIDEBAR', 5, 40, NULL, TRUE),
(505, 'Inventory', 'box', '/resources/inventory', 'SIDEBAR', 5, 50, NULL, TRUE),
(506, 'Vendors', 'shopping-cart', '/resources/vendors', 'SIDEBAR', 5, 60, NULL, TRUE);

-- 6. Procurement Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(601, 'Purchase Requests', 'file-plus', '/procurement/requests', 'SIDEBAR', 6, 10, NULL, TRUE),
(602, 'RFQs', 'file-text', '/procurement/rfqs', 'SIDEBAR', 6, 20, NULL, TRUE),
(603, 'Purchase Orders', 'check-square', '/procurement/orders', 'SIDEBAR', 6, 30, NULL, TRUE),
(604, 'Goods Received Notes', 'clipboard', '/procurement/grns', 'SIDEBAR', 6, 40, NULL, TRUE),
(605, 'Purchase Invoices', 'dollar-sign', '/procurement/invoices', 'SIDEBAR', 6, 50, NULL, TRUE),
(606, 'Vendor Bills', 'file-minus', '/procurement/bills', 'SIDEBAR', 6, 60, NULL, TRUE),
(607, 'Contract Management', 'shield', '/procurement/contracts', 'SIDEBAR', 6, 70, NULL, TRUE),
(608, 'Procurement Reports', 'bar-chart', '/procurement/reports', 'SIDEBAR', 6, 80, NULL, TRUE);

-- 7. Finance Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(701, 'Invoices', 'file-invoice', '/finance/invoices', 'SIDEBAR', 7, 10, NULL, TRUE),
(702, 'Payments', 'credit-card', '/finance/payments', 'SIDEBAR', 7, 20, NULL, TRUE),
(703, 'Expenses', 'file-minus', '/finance/expenses', 'SIDEBAR', 7, 30, NULL, TRUE),
(704, 'Accounting', 'book-open', '/finance/accounting', 'SIDEBAR', 7, 40, NULL, TRUE),
(705, 'Taxes', 'percent', '/finance/taxes', 'SIDEBAR', 7, 50, NULL, TRUE),
(706, 'Insurance & Claims', 'shield', '/finance/insurance', 'SIDEBAR', 7, 60, NULL, TRUE);

-- 8. HR Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(801, 'Employees', 'users', '/hr/employees', 'SIDEBAR', 8, 10, NULL, TRUE),
(802, 'Attendance', 'clock', '/hr/attendance', 'SIDEBAR', 8, 20, NULL, TRUE),
(803, 'Leave Management', 'calendar', '/hr/leave', 'SIDEBAR', 8, 30, NULL, TRUE),
(804, 'Payroll', 'dollar-sign', '/hr/payroll', 'SIDEBAR', 8, 40, NULL, TRUE),
(805, 'Recruitment', 'user-plus', '/hr/recruitment', 'SIDEBAR', 8, 50, NULL, TRUE),
(806, 'Performance', 'award', '/hr/performance', 'SIDEBAR', 8, 60, NULL, TRUE);

-- 9. Support Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(901, 'Tickets', 'mail', '/support/tickets', 'SIDEBAR', 9, 10, NULL, TRUE),
(902, 'Complaints', 'frown', '/support/complaints', 'SIDEBAR', 9, 20, NULL, TRUE),
(903, 'Escalations', 'alert-circle', '/support/escalations', 'SIDEBAR', 9, 30, NULL, TRUE),
(904, 'Customer Feedback', 'star', '/support/feedback', 'SIDEBAR', 9, 40, NULL, TRUE),
(905, 'Knowledge Base', 'book', '/support/kb', 'SIDEBAR', 9, 50, NULL, TRUE),
(906, 'Announcements', 'volume-2', '/support/announcements', 'SIDEBAR', 9, 60, NULL, TRUE);

-- 10. Reports Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1001, 'Sales Reports', 'bar-chart-2', '/reports/sales', 'SIDEBAR', 10, 10, NULL, TRUE),
(1002, 'Customer Reports', 'users', '/reports/customers', 'SIDEBAR', 10, 20, NULL, TRUE),
(1003, 'Lead Reports', 'funnel', '/reports/leads', 'SIDEBAR', 10, 30, NULL, TRUE),
(1004, 'Booking Reports', 'calendar', '/reports/bookings', 'SIDEBAR', 10, 40, NULL, TRUE),
(1005, 'Move Reports', 'package', '/reports/moves', 'SIDEBAR', 10, 50, NULL, TRUE),
(1006, 'Fleet Reports', 'truck', '/reports/fleet', 'SIDEBAR', 10, 60, NULL, TRUE),
(1007, 'Warehouse Reports', 'database', '/reports/warehouses', 'SIDEBAR', 10, 70, NULL, TRUE),
(1008, 'Asset Reports', 'tool', '/reports/assets', 'SIDEBAR', 10, 80, NULL, TRUE),
(1009, 'HR Reports', 'users', '/reports/hr', 'SIDEBAR', 10, 90, NULL, TRUE),
(1010, 'Payroll Reports', 'dollar-sign', '/reports/payroll', 'SIDEBAR', 10, 100, NULL, TRUE),
(1011, 'Financial Reports', 'trending-up', '/reports/financial', 'SIDEBAR', 10, 110, NULL, TRUE),
(1012, 'Tax Reports', 'percent', '/reports/taxes', 'SIDEBAR', 10, 120, NULL, TRUE),
(1013, 'Custom Reports', 'settings', '/reports/custom', 'SIDEBAR', 10, 130, NULL, TRUE);

-- 11. Administration Menus (Level 2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1101, 'User Management', 'users', '/admin/users', 'SIDEBAR', 11, 10, NULL, TRUE),
(1102, 'Organization', 'briefcase', '/admin/organization', 'SIDEBAR', 11, 20, NULL, TRUE),
(1103, 'Workflow Configuration', 'git-branch', '/admin/workflows', 'SIDEBAR', 11, 30, NULL, TRUE),
(1104, 'Notifications', 'bell', '/admin/notifications', 'SIDEBAR', 11, 40, NULL, TRUE),
(1105, 'Document Management', 'file-text', '/admin/documents', 'SIDEBAR', 11, 50, NULL, TRUE),
(1106, 'Integrations', 'sliders', '/admin/integrations', 'SIDEBAR', 11, 60, NULL, TRUE),
(1107, 'Audit Logs', 'file-text', '/admin/audit-logs', 'SIDEBAR', 11, 70, NULL, TRUE),
(1108, 'System Settings', 'settings', '/admin/system-settings', 'SIDEBAR', 11, 80, NULL, TRUE),
(1109, 'Activity Logs', 'activity', '/admin/activity-logs', 'SIDEBAR', 11, 90, NULL, TRUE);


-- =============================================================================
-- SEED SUBMENUS (LEVEL 3)
-- =============================================================================

-- 2. CRM -> Leads Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(211, 'All Leads', 'list', '/crm/leads/all', 'SIDEBAR', 201, 10, NULL, TRUE),
(212, 'New Leads', 'plus', '/crm/leads/new', 'SIDEBAR', 201, 20, NULL, TRUE),
(213, 'Follow Ups', 'phone-call', '/crm/leads/follow-ups', 'SIDEBAR', 201, 30, NULL, TRUE),
(214, 'Lead Sources', 'globe', '/crm/leads/sources', 'SIDEBAR', 201, 40, NULL, TRUE),
(215, 'Lead Statuses', 'check-square', '/crm/leads/statuses', 'SIDEBAR', 201, 50, NULL, TRUE);

-- 2. CRM -> Customers Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(221, 'All Customers', 'users', '/crm/customers/all', 'SIDEBAR', 202, 10, NULL, TRUE),
(222, 'Residential Customers', 'home', '/crm/customers/residential', 'SIDEBAR', 202, 20, NULL, TRUE),
(223, 'Commercial Customers', 'briefcase', '/crm/customers/commercial', 'SIDEBAR', 202, 30, NULL, TRUE),
(224, 'Customer Addresses', 'map-pin', '/crm/customers/addresses', 'SIDEBAR', 202, 40, NULL, TRUE),
(225, 'Customer Documents', 'file', '/crm/customers/documents', 'SIDEBAR', 202, 50, NULL, TRUE);

-- 2. CRM -> Surveys Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(231, 'Survey Requests', 'mail', '/crm/surveys/requests', 'SIDEBAR', 203, 10, NULL, TRUE),
(232, 'Scheduled Surveys', 'calendar', '/crm/surveys/scheduled', 'SIDEBAR', 203, 20, NULL, TRUE),
(233, 'Completed Surveys', 'check', '/crm/surveys/completed', 'SIDEBAR', 203, 30, NULL, TRUE),
(234, 'Virtual Surveys', 'video', '/crm/surveys/virtual', 'SIDEBAR', 203, 40, NULL, TRUE),
(235, 'Survey Reports', 'file-bar-chart', '/crm/surveys/reports', 'SIDEBAR', 203, 50, NULL, TRUE);

-- 2. CRM -> Quotations Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(241, 'Draft Quotes', 'edit', '/crm/quotations/draft', 'SIDEBAR', 204, 10, NULL, TRUE),
(242, 'Sent Quotes', 'send', '/crm/quotations/sent', 'SIDEBAR', 204, 20, NULL, TRUE),
(243, 'Approved Quotes', 'thumbs-up', '/crm/quotations/approved', 'SIDEBAR', 204, 30, NULL, TRUE),
(244, 'Rejected Quotes', 'thumbs-down', '/crm/quotations/rejected', 'SIDEBAR', 204, 40, NULL, TRUE),
(245, 'Expired Quotes', 'clock', '/crm/quotations/expired', 'SIDEBAR', 204, 50, NULL, TRUE),
(246, 'Quote Templates', 'copy', '/crm/quotations/templates', 'SIDEBAR', 204, 60, NULL, TRUE);

-- 3. Operations -> Bookings Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(311, 'New Bookings', 'plus-circle', '/operations/bookings/new', 'SIDEBAR', 301, 10, NULL, TRUE),
(312, 'Confirmed Bookings', 'check-circle', '/operations/bookings/confirmed', 'SIDEBAR', 301, 20, NULL, TRUE),
(313, 'Assigned Bookings', 'user-plus', '/operations/bookings/assigned', 'SIDEBAR', 301, 30, NULL, TRUE),
(314, 'Completed Bookings', 'archive', '/operations/bookings/completed', 'SIDEBAR', 301, 40, NULL, TRUE),
(315, 'Cancelled Bookings', 'x-circle', '/operations/bookings/cancelled', 'SIDEBAR', 301, 50, NULL, TRUE);

-- 3. Operations -> Move Orders Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(321, 'Active Moves', 'play', '/operations/moves/active', 'SIDEBAR', 302, 10, NULL, TRUE),
(322, 'Scheduled Moves', 'calendar', '/operations/moves/scheduled', 'SIDEBAR', 302, 20, NULL, TRUE),
(323, 'In Progress', 'refresh-cw', '/operations/moves/in-progress', 'SIDEBAR', 302, 30, NULL, TRUE),
(324, 'Completed', 'check-all', '/operations/moves/completed', 'SIDEBAR', 302, 40, NULL, TRUE),
(325, 'Cancelled', 'x', '/operations/moves/cancelled', 'SIDEBAR', 302, 50, NULL, TRUE);

-- 3. Operations -> Dispatch Center Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(331, 'Dispatch Board', 'layers', '/operations/dispatch/board', 'SIDEBAR', 303, 10, NULL, TRUE),
(332, 'Assign Crew', 'users', '/operations/dispatch/assign-crew', 'SIDEBAR', 303, 20, NULL, TRUE),
(333, 'Assign Vehicles', 'truck', '/operations/dispatch/assign-vehicles', 'SIDEBAR', 303, 30, NULL, TRUE),
(334, 'Route Planning', 'compass', '/operations/dispatch/routes', 'SIDEBAR', 303, 40, NULL, TRUE);

-- 3. Operations -> Move Tracking Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(341, 'Live Tracking', 'eye', '/operations/tracking/live', 'SIDEBAR', 304, 10, NULL, TRUE),
(342, 'Delayed Jobs', 'alert-triangle', '/operations/tracking/delayed', 'SIDEBAR', 304, 20, NULL, TRUE),
(343, 'Job Timeline', 'list', '/operations/tracking/timeline', 'SIDEBAR', 304, 30, NULL, TRUE),
(344, 'Activity Logs', 'file-text', '/operations/tracking/logs', 'SIDEBAR', 304, 40, NULL, TRUE);

-- 3. Operations -> Inventory Assessment Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(351, 'Property Inventory', 'home', '/operations/inventory/property', 'SIDEBAR', 305, 10, NULL, TRUE),
(352, 'Room Inventory', 'columns', '/operations/inventory/room', 'SIDEBAR', 352, 20, NULL, TRUE),
(353, 'Special Items', 'star', '/operations/inventory/special', 'SIDEBAR', 305, 30, NULL, TRUE),
(354, 'Weight Estimation', 'activity', '/operations/inventory/weight', 'SIDEBAR', 305, 40, NULL, TRUE);

-- 5. Resources -> Fleet Management Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(511, 'Vehicles', 'truck', '/resources/fleet/vehicles', 'SIDEBAR', 501, 10, NULL, TRUE),
(512, 'Vehicle Categories', 'layers', '/resources/fleet/categories', 'SIDEBAR', 501, 20, NULL, TRUE),
(513, 'Maintenance', 'tool', '/resources/fleet/maintenance', 'SIDEBAR', 501, 30, NULL, TRUE),
(514, 'Fuel Logs', 'droplet', '/resources/fleet/fuel', 'SIDEBAR', 501, 40, NULL, TRUE),
(515, 'Vehicle Insurance', 'shield', '/resources/fleet/insurance', 'SIDEBAR', 501, 50, NULL, TRUE),
(516, 'Vehicle Documents', 'file', '/resources/fleet/documents', 'SIDEBAR', 501, 60, NULL, TRUE);

-- 5. Resources -> Crew Management Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(521, 'Crew Members', 'user', '/resources/crew/members', 'SIDEBAR', 502, 10, NULL, TRUE),
(522, 'Teams', 'users', '/resources/crew/teams', 'SIDEBAR', 502, 20, NULL, TRUE),
(523, 'Crew Schedules', 'calendar', '/resources/crew/schedules', 'SIDEBAR', 502, 30, NULL, TRUE),
(524, 'Availability', 'clock', '/resources/crew/availability', 'SIDEBAR', 502, 40, NULL, TRUE),
(525, 'Performance Ratings', 'star', '/resources/crew/performance', 'SIDEBAR', 502, 50, NULL, TRUE);

-- 5. Resources -> Warehouses Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(531, 'Warehouses', 'database', '/resources/warehouses/all', 'SIDEBAR', 503, 10, NULL, TRUE),
(532, 'Storage Units', 'layers', '/resources/warehouses/units', 'SIDEBAR', 503, 20, NULL, TRUE),
(533, 'Stored Items', 'box', '/resources/warehouses/items', 'SIDEBAR', 503, 30, NULL, TRUE),
(534, 'Storage Contracts', 'file-text', '/resources/warehouses/contracts', 'SIDEBAR', 503, 40, NULL, TRUE),
(535, 'Warehouse Transfers', 'move', '/resources/warehouses/transfers', 'SIDEBAR', 503, 50, NULL, TRUE);

-- 5. Resources -> Assets Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(541, 'Asset Registry', 'list', '/resources/assets/registry', 'SIDEBAR', 504, 10, NULL, TRUE),
(542, 'Asset Categories', 'grid', '/resources/assets/categories', 'SIDEBAR', 504, 20, NULL, TRUE),
(543, 'Asset Assignment', 'user-check', '/resources/assets/assignment', 'SIDEBAR', 504, 30, NULL, TRUE),
(544, 'Asset Maintenance', 'settings', '/resources/assets/maintenance', 'SIDEBAR', 504, 40, NULL, TRUE),
(545, 'Depreciation', 'trending-down', '/resources/assets/depreciation', 'SIDEBAR', 504, 50, NULL, TRUE),
(546, 'Asset Disposal', 'trash', '/resources/assets/disposal', 'SIDEBAR', 504, 60, NULL, TRUE);

-- 5. Resources -> Inventory Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(551, 'Packing Materials', 'archive', '/resources/inventory/packing', 'SIDEBAR', 505, 10, NULL, TRUE),
(552, 'Consumables', 'box', '/resources/inventory/consumables', 'SIDEBAR', 505, 20, NULL, TRUE),
(553, 'Stock Levels', 'bar-chart-2', '/resources/inventory/stock', 'SIDEBAR', 505, 30, NULL, TRUE),
(554, 'Stock Transfers', 'repeat', '/resources/inventory/transfers', 'SIDEBAR', 505, 40, NULL, TRUE),
(555, 'Stock Adjustments', 'sliders', '/resources/inventory/adjustments', 'SIDEBAR', 505, 50, NULL, TRUE);

-- 5. Resources -> Vendors Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(561, 'Vendors', 'briefcase', '/resources/vendors/all', 'SIDEBAR', 506, 10, NULL, TRUE),
(562, 'Vendor Contracts', 'file-text', '/resources/vendors/contracts', 'SIDEBAR', 506, 20, NULL, TRUE),
(563, 'Vendor Performance', 'star', '/resources/vendors/performance', 'SIDEBAR', 506, 30, NULL, TRUE),
(564, 'Vendor Payments', 'credit-card', '/resources/vendors/payments', 'SIDEBAR', 506, 40, NULL, TRUE);

-- 7. Finance -> Invoices Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(711, 'Customer Invoices', 'user', '/finance/invoices/customer', 'SIDEBAR', 701, 10, NULL, TRUE),
(712, 'Draft Invoices', 'edit', '/finance/invoices/draft', 'SIDEBAR', 701, 20, NULL, TRUE),
(713, 'Paid Invoices', 'check-circle', '/finance/invoices/paid', 'SIDEBAR', 701, 30, NULL, TRUE),
(714, 'Overdue Invoices', 'alert-circle', '/finance/invoices/overdue', 'SIDEBAR', 701, 40, NULL, TRUE);

-- 7. Finance -> Payments Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(721, 'Customer Payments', 'arrow-down-left', '/finance/payments/customer', 'SIDEBAR', 702, 10, NULL, TRUE),
(722, 'Vendor Payments', 'arrow-up-right', '/finance/payments/vendor', 'SIDEBAR', 702, 20, NULL, TRUE),
(723, 'Refunds', 'rotate-ccw', '/finance/payments/refunds', 'SIDEBAR', 702, 30, NULL, TRUE),
(724, 'Payment Methods', 'settings', '/finance/payments/methods', 'SIDEBAR', 702, 40, NULL, TRUE);

-- 7. Finance -> Expenses Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(731, 'Fuel Expenses', 'droplet', '/finance/expenses/fuel', 'SIDEBAR', 703, 10, NULL, TRUE),
(732, 'Maintenance Expenses', 'tool', '/finance/expenses/maintenance', 'SIDEBAR', 703, 20, NULL, TRUE),
(733, 'Payroll Expenses', 'users', '/finance/expenses/payroll', 'SIDEBAR', 703, 30, NULL, TRUE),
(734, 'Other Expenses', 'file-text', '/finance/expenses/other', 'SIDEBAR', 703, 40, NULL, TRUE);

-- 7. Finance -> Accounting Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(741, 'Chart Of Accounts', 'list', '/finance/accounting/coa', 'SIDEBAR', 701, 10, NULL, TRUE),
(742, 'Journal Entries', 'book', '/finance/accounting/journal', 'SIDEBAR', 701, 20, NULL, TRUE),
(743, 'General Ledger', 'book-open', '/finance/accounting/ledger', 'SIDEBAR', 701, 30, NULL, TRUE),
(744, 'Trial Balance', 'activity', '/finance/accounting/trial-balance', 'SIDEBAR', 701, 40, NULL, TRUE),
(745, 'Fiscal Years', 'calendar', '/finance/accounting/fiscal-years', 'SIDEBAR', 701, 50, NULL, TRUE);

-- 7. Finance -> Taxes Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(751, 'Tax Rates', 'percent', '/finance/taxes/rates', 'SIDEBAR', 705, 10, NULL, TRUE),
(752, 'Tax Rules', 'settings', '/finance/taxes/rules', 'SIDEBAR', 705, 20, NULL, TRUE),
(753, 'Tax Reports', 'file-text', '/finance/taxes/reports', 'SIDEBAR', 705, 30, NULL, TRUE);

-- 7. Finance -> Insurance & Claims Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(761, 'Insurance Policies', 'file-text', '/finance/insurance/policies', 'SIDEBAR', 706, 10, NULL, TRUE),
(762, 'Claims', 'file-plus', '/finance/insurance/claims', 'SIDEBAR', 706, 20, NULL, TRUE),
(763, 'Damaged Items', 'box', '/finance/insurance/damaged-items', 'SIDEBAR', 706, 30, NULL, TRUE),
(764, 'Claim Settlements', 'check-square', '/finance/insurance/settlements', 'SIDEBAR', 706, 40, NULL, TRUE);

-- 8. HR -> Employees Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(811, 'Employee Directory', 'user', '/hr/employees/directory', 'SIDEBAR', 801, 10, NULL, TRUE),
(812, 'Designations', 'briefcase', '/hr/employees/designations', 'SIDEBAR', 801, 20, NULL, TRUE),
(813, 'Departments', 'layers', '/hr/employees/departments', 'SIDEBAR', 801, 30, NULL, TRUE),
(814, 'Contracts', 'file-text', '/hr/employees/contracts', 'SIDEBAR', 801, 40, NULL, TRUE),
(815, 'Documents', 'file', '/hr/employees/documents', 'SIDEBAR', 801, 50, NULL, TRUE);

-- 8. HR -> Attendance Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(821, 'Daily Attendance', 'check-square', '/hr/attendance/daily', 'SIDEBAR', 802, 10, NULL, TRUE),
(822, 'Attendance Logs', 'list', '/hr/attendance/logs', 'SIDEBAR', 802, 20, NULL, TRUE),
(823, 'Attendance Reports', 'file-text', '/hr/attendance/reports', 'SIDEBAR', 802, 30, NULL, TRUE);

-- 8. HR -> Leave Management Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(831, 'Leave Requests', 'mail', '/hr/leave/requests', 'SIDEBAR', 803, 10, NULL, TRUE),
(832, 'Leave Types', 'sliders', '/hr/leave/types', 'SIDEBAR', 803, 20, NULL, TRUE),
(833, 'Leave Balances', 'activity', '/hr/leave/balances', 'SIDEBAR', 803, 30, NULL, TRUE),
(834, 'Leave Calendar', 'calendar', '/hr/leave/calendar', 'SIDEBAR', 803, 40, NULL, TRUE);

-- 8. HR -> Payroll Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(841, 'Salary Structures', 'layers', '/hr/payroll/structures', 'SIDEBAR', 804, 10, NULL, TRUE),
(842, 'Payroll Processing', 'refresh-cw', '/hr/payroll/process', 'SIDEBAR', 804, 20, NULL, TRUE),
(843, 'Payslips', 'file-text', '/hr/payroll/payslips', 'SIDEBAR', 804, 30, NULL, TRUE),
(844, 'Bonuses', 'gift', '/hr/payroll/bonuses', 'SIDEBAR', 804, 40, NULL, TRUE),
(845, 'Deductions', 'minus-circle', '/hr/payroll/deductions', 'SIDEBAR', 804, 50, NULL, TRUE);

-- 8. HR -> Recruitment Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(851, 'Job Positions', 'briefcase', '/hr/recruitment/positions', 'SIDEBAR', 805, 10, NULL, TRUE),
(852, 'Applicants', 'users', '/hr/recruitment/applicants', 'SIDEBAR', 805, 20, NULL, TRUE),
(853, 'Interviews', 'calendar', '/hr/recruitment/interviews', 'SIDEBAR', 805, 30, NULL, TRUE),
(854, 'Hiring Pipeline', 'activity', '/hr/recruitment/pipeline', 'SIDEBAR', 805, 40, NULL, TRUE);

-- 8. HR -> Performance Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(861, 'Reviews', 'check-square', '/hr/performance/reviews', 'SIDEBAR', 806, 10, NULL, TRUE),
(862, 'KPIs', 'trending-up', '/hr/performance/kpis', 'SIDEBAR', 806, 20, NULL, TRUE),
(863, 'Promotions', 'trending-up', '/hr/performance/promotions', 'SIDEBAR', 806, 30, NULL, TRUE),
(864, 'Warnings', 'alert-octagon', '/hr/performance/warnings', 'SIDEBAR', 806, 40, NULL, TRUE);

-- 11. Administration -> User Management Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1111, 'Users', 'user', '/admin/users/all', 'SIDEBAR', 1101, 10, NULL, TRUE),
(1112, 'Roles', 'shield', '/admin/users/roles', 'SIDEBAR', 1101, 20, NULL, TRUE),
(1113, 'Permissions', 'key', '/admin/users/permissions', 'SIDEBAR', 1101, 30, NULL, TRUE),
(1114, 'User Groups', 'users', '/admin/users/groups', 'SIDEBAR', 1101, 40, NULL, TRUE);

-- 11. Administration -> Organization Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1121, 'Branches', 'home', '/admin/org/branches', 'SIDEBAR', 1102, 10, NULL, TRUE),
(1122, 'Departments', 'layers', '/admin/org/departments', 'SIDEBAR', 1102, 20, NULL, TRUE),
(1123, 'Teams', 'users', '/admin/org/teams', 'SIDEBAR', 1102, 30, NULL, TRUE),
(1124, 'Locations', 'map-pin', '/admin/org/locations', 'SIDEBAR', 1102, 40, NULL, TRUE);

-- 11. Administration -> Workflow Configuration Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1131, 'Approval Flows', 'check-square', '/admin/workflows/approvals', 'SIDEBAR', 1102, 10, NULL, TRUE),
(1132, 'Booking Workflow', 'calendar', '/admin/workflows/bookings', 'SIDEBAR', 1102, 20, NULL, TRUE),
(1133, 'Quote Workflow', 'file-text', '/admin/workflows/quotes', 'SIDEBAR', 1102, 30, NULL, TRUE),
(1134, 'Notification Workflow', 'bell', '/admin/workflows/notifications', 'SIDEBAR', 1102, 40, NULL, TRUE);

-- 11. Administration -> Notifications Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1141, 'Email Templates', 'mail', '/admin/notifications/email', 'SIDEBAR', 1104, 10, NULL, TRUE),
(1142, 'SMS Templates', 'phone', '/admin/notifications/sms', 'SIDEBAR', 1104, 20, NULL, TRUE),
(1143, 'WhatsApp Templates', 'message-square', '/admin/notifications/whatsapp', 'SIDEBAR', 1104, 30, NULL, TRUE),
(1144, 'Push Templates', 'bell', '/admin/notifications/push', 'SIDEBAR', 1104, 40, NULL, TRUE);

-- 11. Administration -> Document Management Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1151, 'Document Types', 'file', '/admin/documents/types', 'SIDEBAR', 1105, 10, NULL, TRUE),
(1152, 'Templates', 'copy', '/admin/documents/templates', 'SIDEBAR', 1105, 20, NULL, TRUE),
(1153, 'Storage Settings', 'database', '/admin/documents/storage', 'SIDEBAR', 1105, 30, NULL, TRUE);

-- 11. Administration -> Integrations Submenus (Level 3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1161, 'Payment Gateways', 'credit-card', '/admin/integrations/payments', 'SIDEBAR', 1106, 10, NULL, TRUE),
(1162, 'SMS Providers', 'phone', '/admin/integrations/sms', 'SIDEBAR', 1106, 20, NULL, TRUE),
(1163, 'Email Providers', 'mail', '/admin/integrations/email', 'SIDEBAR', 1106, 30, NULL, TRUE),
(1164, 'Google Maps', 'map', '/admin/integrations/maps', 'SIDEBAR', 1106, 40, NULL, TRUE),
(1165, 'Webhooks', 'link', '/admin/integrations/webhooks', 'SIDEBAR', 1106, 50, NULL, TRUE);


-- =============================================================================
-- SEED TOPBAR & PROFILE SECTIONS
-- =============================================================================

-- Topbar Items
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1201, 'Quick Estimation', 'calculator', '/estimate/quick', 'TOPBAR', NULL, 10, NULL, TRUE),
(1202, 'Support Chat', 'chat-bubble', '/support/live-chat', 'TOPBAR', NULL, 20, NULL, TRUE);

-- Profile Items
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1301, 'My Profile', 'user-profile', '/user/profile', 'PROFILE', NULL, 10, NULL, TRUE),
(1302, 'Account Settings', 'settings-gear', '/user/settings', 'PROFILE', NULL, 20, NULL, TRUE),
(1303, 'Logout', 'sign-out', '/logout', 'PROFILE', NULL, 30, NULL, TRUE);

-- Final sequence synchronization
SELECT setval('sec_menu_items_id_seq', (SELECT MAX(id) FROM sec_menu_items));
