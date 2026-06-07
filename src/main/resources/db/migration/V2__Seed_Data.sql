-- =============================================================================
-- V2__Seed_Data.sql
-- Consolidated Seed Data for Network Movers Enterprise Platform
-- Combines: V2 (original seeds), V4 (vehicle types/makes menu),
--           V5 (fleet menu path updates - final paths applied directly),
--           V6 (vehicle models), V7 (vehicles), V9 (modules),
--           V11 (sec_resources RBAC data)
-- =============================================================================

-- =============================================================================
-- SECTION 1: NAVIGATION MENU SEEDS
-- =============================================================================

-- Top-level navigation items
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1,  'Dashboard',      'dashboard',   '/dashboard',   'SIDEBAR', NULL, 10,  NULL, TRUE),
(2,  'CRM',            'crm',         '/crm',         'SIDEBAR', NULL, 20,  NULL, TRUE),
(3,  'Operations',     'operations',  '/operations',  'SIDEBAR', NULL, 30,  NULL, TRUE),
(4,  'Services',       'services',    '/services',    'SIDEBAR', NULL, 40,  NULL, TRUE),
(5,  'Resources',      'resources',   '/resources',   'SIDEBAR', NULL, 50,  NULL, TRUE),
(6,  'Procurement',    'procurement', '/procurement', 'SIDEBAR', NULL, 60,  NULL, TRUE),
(7,  'Finance',        'finance',     '/finance',     'SIDEBAR', NULL, 70,  NULL, TRUE),
(8,  'HR',             'hr',          '/hr',          'SIDEBAR', NULL, 80,  NULL, TRUE),
(9,  'Support',        'support',     '/support',     'SIDEBAR', NULL, 90,  NULL, TRUE),
(10, 'Reports',        'reports',     '/reports',     'SIDEBAR', NULL, 100, NULL, TRUE),
(11, 'Administration', 'admin',       '/admin',       'SIDEBAR', NULL, 110, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Dashboard children (parent_id=1)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(101, 'Executive Dashboard',   'chart-bar',   '/dashboard/executive',   'SIDEBAR', 1, 10, NULL, TRUE),
(102, 'Operations Dashboard',  'activity',    '/dashboard/operations',  'SIDEBAR', 1, 20, NULL, TRUE),
(103, 'Sales Dashboard',       'trending-up', '/dashboard/sales',       'SIDEBAR', 1, 30, NULL, TRUE),
(104, 'Finance Dashboard',     'dollar-sign', '/dashboard/finance',     'SIDEBAR', 1, 40, NULL, TRUE),
(105, 'HR Dashboard',          'users',       '/dashboard/hr',          'SIDEBAR', 1, 50, NULL, TRUE),
(106, 'Fleet Dashboard',       'truck',       '/dashboard/fleet',       'SIDEBAR', 1, 60, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- CRM children (parent_id=2)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(201, 'Leads',      'funnel',          '/crm/leads',      'SIDEBAR', 2, 10, NULL, TRUE),
(202, 'Customers',  'user-check',      '/crm/customers',  'SIDEBAR', 2, 20, NULL, TRUE),
(203, 'Surveys',    'clipboard-list',  '/crm/surveys',    'SIDEBAR', 2, 30, NULL, TRUE),
(204, 'Quotations', 'file-text',       '/crm/quotations', 'SIDEBAR', 2, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Operations children (parent_id=3)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(301, 'Bookings',             'calendar-check', '/operations/bookings',             'SIDEBAR', 3, 10, NULL, TRUE),
(302, 'Move Orders',          'package',        '/operations/move-orders',           'SIDEBAR', 3, 20, NULL, TRUE),
(303, 'Dispatch Center',      'navigation',     '/operations/dispatch',             'SIDEBAR', 3, 30, NULL, TRUE),
(304, 'Move Tracking',        'map',            '/operations/tracking',             'SIDEBAR', 3, 40, NULL, TRUE),
(305, 'Inventory Assessment', 'box',            '/operations/inventory-assessment', 'SIDEBAR', 3, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Services children (parent_id=4)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(401, 'Service Categories',          'grid',       '/services/categories',    'SIDEBAR', 4,  10, NULL, TRUE),
(402, 'Service Types',               'sliders',    '/services/types',         'SIDEBAR', 4,  20, NULL, TRUE),
(403, 'Add-On Services',             'plus-square','/services/add-ons',       'SIDEBAR', 4,  30, NULL, TRUE),
(404, 'Pricing Management',          'dollar-sign','/services/pricing',       'SIDEBAR', 4,  40, NULL, TRUE),
(405, 'Property Types Management',   'layers',     '/system/property-types',  'SIDEBAR', 11, 50, NULL, TRUE),
(406, 'Zones & Coverage',            'map-pin',    '/services/zones',         'SIDEBAR', 4,  60, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Resources children (parent_id=5)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(501, 'Fleet Management', 'truck',         '/resources/fleet',      'SIDEBAR', 5, 10, NULL, TRUE),
(502, 'Crew Management',  'users',         '/resources/crew',       'SIDEBAR', 5, 20, NULL, TRUE),
(503, 'Warehouses',       'database',      '/resources/warehouses', 'SIDEBAR', 5, 30, NULL, TRUE),
(504, 'Assets',           'tool',          '/resources/assets',     'SIDEBAR', 5, 40, NULL, TRUE),
(505, 'Inventory',        'box',           '/resources/inventory',  'SIDEBAR', 5, 50, NULL, TRUE),
(506, 'Vendors',          'shopping-cart', '/resources/vendors',    'SIDEBAR', 5, 60, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Procurement children (parent_id=6)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(601, 'Purchase Requests',     'file-plus',   '/procurement/requests', 'SIDEBAR', 6, 10, NULL, TRUE),
(602, 'RFQs',                  'file-text',   '/procurement/rfqs',     'SIDEBAR', 6, 20, NULL, TRUE),
(603, 'Purchase Orders',       'check-square','/procurement/orders',   'SIDEBAR', 6, 30, NULL, TRUE),
(604, 'Goods Received Notes',  'clipboard',   '/procurement/grns',     'SIDEBAR', 6, 40, NULL, TRUE),
(605, 'Purchase Invoices',     'dollar-sign', '/procurement/invoices', 'SIDEBAR', 6, 50, NULL, TRUE),
(606, 'Vendor Bills',          'file-minus',  '/procurement/bills',    'SIDEBAR', 6, 60, NULL, TRUE),
(607, 'Contract Management',   'shield',      '/procurement/contracts','SIDEBAR', 6, 70, NULL, TRUE),
(608, 'Procurement Reports',   'bar-chart',   '/procurement/reports',  'SIDEBAR', 6, 80, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Finance children (parent_id=7)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(701, 'Invoices',          'file-invoice', '/finance/invoices',    'SIDEBAR', 7, 10, NULL, TRUE),
(702, 'Payments',          'credit-card',  '/finance/payments',    'SIDEBAR', 7, 20, NULL, TRUE),
(703, 'Expenses',          'file-minus',   '/finance/expenses',    'SIDEBAR', 7, 30, NULL, TRUE),
(704, 'Accounting',        'book-open',    '/finance/accounting',  'SIDEBAR', 7, 40, NULL, TRUE),
(705, 'Taxes',             'percent',      '/finance/taxes',       'SIDEBAR', 7, 50, NULL, TRUE),
(706, 'Insurance & Claims','shield',       '/finance/insurance',   'SIDEBAR', 7, 60, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- HR children (parent_id=8)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(801, 'Employees',      'users',      '/hr/employees',   'SIDEBAR', 8, 10, NULL, TRUE),
(802, 'Attendance',     'clock',      '/hr/attendance',  'SIDEBAR', 8, 20, NULL, TRUE),
(803, 'Leave Management','calendar',  '/hr/leave',       'SIDEBAR', 8, 30, NULL, TRUE),
(804, 'Payroll',        'dollar-sign','/hr/payroll',     'SIDEBAR', 8, 40, NULL, TRUE),
(805, 'Recruitment',    'user-plus',  '/hr/recruitment', 'SIDEBAR', 8, 50, NULL, TRUE),
(806, 'Performance',    'award',      '/hr/performance', 'SIDEBAR', 8, 60, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Support children (parent_id=9)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(901, 'Tickets',           'mail',         '/support/tickets',       'SIDEBAR', 9, 10, NULL, TRUE),
(902, 'Complaints',        'frown',        '/support/complaints',    'SIDEBAR', 9, 20, NULL, TRUE),
(903, 'Escalations',       'alert-circle', '/support/escalations',   'SIDEBAR', 9, 30, NULL, TRUE),
(904, 'Customer Feedback', 'star',         '/support/feedback',      'SIDEBAR', 9, 40, NULL, TRUE),
(905, 'Knowledge Base',    'book',         '/support/kb',            'SIDEBAR', 9, 50, NULL, TRUE),
(906, 'Announcements',     'volume-2',     '/support/announcements', 'SIDEBAR', 9, 60, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Reports children (parent_id=10)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1001, 'Sales Reports',      'bar-chart-2', '/reports/sales',       'SIDEBAR', 10, 10,  NULL, TRUE),
(1002, 'Customer Reports',   'users',       '/reports/customers',   'SIDEBAR', 10, 20,  NULL, TRUE),
(1003, 'Lead Reports',       'funnel',      '/reports/leads',       'SIDEBAR', 10, 30,  NULL, TRUE),
(1004, 'Booking Reports',    'calendar',    '/reports/bookings',    'SIDEBAR', 10, 40,  NULL, TRUE),
(1005, 'Move Reports',       'package',     '/reports/moves',       'SIDEBAR', 10, 50,  NULL, TRUE),
(1006, 'Fleet Reports',      'truck',       '/reports/fleet',       'SIDEBAR', 10, 60,  NULL, TRUE),
(1007, 'Warehouse Reports',  'database',    '/reports/warehouses',  'SIDEBAR', 10, 70,  NULL, TRUE),
(1008, 'Asset Reports',      'tool',        '/reports/assets',      'SIDEBAR', 10, 80,  NULL, TRUE),
(1009, 'HR Reports',         'users',       '/reports/hr',          'SIDEBAR', 10, 90,  NULL, TRUE),
(1010, 'Payroll Reports',    'dollar-sign', '/reports/payroll',     'SIDEBAR', 10, 100, NULL, TRUE),
(1011, 'Financial Reports',  'trending-up', '/reports/financial',   'SIDEBAR', 10, 110, NULL, TRUE),
(1012, 'Tax Reports',        'percent',     '/reports/taxes',       'SIDEBAR', 10, 120, NULL, TRUE),
(1013, 'Custom Reports',     'settings',    '/reports/custom',      'SIDEBAR', 10, 130, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Administration children (parent_id=11)
-- NOTE: id=1112 (Roles Management) and id=1113 (old Permissions) are intentionally excluded.
--       RBAC items are consolidated under parent id=2001 below.
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1101, 'User Management',       'users',       '/admin/users',               'SIDEBAR', 11, 10,  NULL, TRUE),
(1102, 'Organization',          'briefcase',   '/admin/organization',         'SIDEBAR', 11, 20,  NULL, TRUE),
(1103, 'Move State Management', 'git-branch',  '/system/move-states',         'SIDEBAR', 11, 30,  NULL, TRUE),
(1104, 'Notifications',         'bell',        '/admin/notifications',        'SIDEBAR', 11, 40,  NULL, TRUE),
(1105, 'Document Management',   'file-text',   '/admin/documents',            'SIDEBAR', 11, 50,  NULL, TRUE),
(1106, 'Integrations',          'sliders',     '/admin/integrations',         'SIDEBAR', 11, 60,  NULL, TRUE),
(1107, 'Audit Logs',            'file-text',   '/admin/audit-logs',           'SIDEBAR', 11, 70,  NULL, TRUE),
(1108, 'System Settings',       'settings',    '/admin/system-settings',      'SIDEBAR', 11, 80,  NULL, TRUE),
(1109, 'Activity Logs',         'activity',    '/admin/activity-logs',        'SIDEBAR', 11, 90,  NULL, TRUE),
(1110, 'Navigation Management', 'menu',        '/system/navigation',          'SIDEBAR', 11, 100, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- CRM sub-children
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(211, 'All Leads',     'list',        '/crm/leads/all',         'SIDEBAR', 201, 10, NULL, TRUE),
(212, 'New Leads',     'plus',        '/crm/leads/new',         'SIDEBAR', 201, 20, NULL, TRUE),
(213, 'Follow Ups',    'phone-call',  '/crm/leads/follow-ups',  'SIDEBAR', 201, 30, NULL, TRUE),
(214, 'Lead Sources',  'globe',       '/crm/leads/sources',     'SIDEBAR', 201, 40, NULL, TRUE),
(215, 'Lead Statuses', 'check-square','/crm/leads/statuses',    'SIDEBAR', 201, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(221, 'All Customers',          'users',    '/crm/customers/all',         'SIDEBAR', 202, 10, NULL, TRUE),
(222, 'Residential Customers',  'home',     '/crm/customers/residential', 'SIDEBAR', 202, 20, NULL, TRUE),
(223, 'Commercial Customers',   'briefcase','/crm/customers/commercial',  'SIDEBAR', 202, 30, NULL, TRUE),
(224, 'Customer Addresses',     'map-pin',  '/crm/customers/addresses',   'SIDEBAR', 202, 40, NULL, TRUE),
(225, 'Customer Documents',     'file',     '/crm/customers/documents',   'SIDEBAR', 202, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(231, 'Survey Requests',   'mail',           '/crm/surveys/requests',  'SIDEBAR', 203, 10, NULL, TRUE),
(232, 'Scheduled Surveys', 'calendar',       '/crm/surveys/scheduled', 'SIDEBAR', 203, 20, NULL, TRUE),
(233, 'Completed Surveys', 'check',          '/crm/surveys/completed', 'SIDEBAR', 203, 30, NULL, TRUE),
(234, 'Virtual Surveys',   'video',          '/crm/surveys/virtual',   'SIDEBAR', 203, 40, NULL, TRUE),
(235, 'Survey Reports',    'file-bar-chart', '/crm/surveys/reports',   'SIDEBAR', 203, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(241, 'Draft Quotes',    'edit',        '/crm/quotations/draft',     'SIDEBAR', 204, 10, NULL, TRUE),
(242, 'Sent Quotes',     'send',        '/crm/quotations/sent',      'SIDEBAR', 204, 20, NULL, TRUE),
(243, 'Approved Quotes', 'thumbs-up',   '/crm/quotations/approved',  'SIDEBAR', 204, 30, NULL, TRUE),
(244, 'Rejected Quotes', 'thumbs-down', '/crm/quotations/rejected',  'SIDEBAR', 204, 40, NULL, TRUE),
(245, 'Expired Quotes',  'clock',       '/crm/quotations/expired',   'SIDEBAR', 204, 50, NULL, TRUE),
(246, 'Quote Templates', 'copy',        '/crm/quotations/templates', 'SIDEBAR', 204, 60, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Operations sub-children
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(311, 'New Bookings',       'plus-circle', '/operations/bookings/new',       'SIDEBAR', 301, 10, NULL, TRUE),
(312, 'Confirmed Bookings', 'check-circle','/operations/bookings/confirmed', 'SIDEBAR', 301, 20, NULL, TRUE),
(313, 'Assigned Bookings',  'user-plus',   '/operations/bookings/assigned',  'SIDEBAR', 301, 30, NULL, TRUE),
(314, 'Completed Bookings', 'archive',     '/operations/bookings/completed', 'SIDEBAR', 301, 40, NULL, TRUE),
(315, 'Cancelled Bookings', 'x-circle',    '/operations/bookings/cancelled', 'SIDEBAR', 301, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(321, 'Active Moves',    'play',       '/operations/moves/active',      'SIDEBAR', 302, 10, NULL, TRUE),
(322, 'Scheduled Moves', 'calendar',   '/operations/moves/scheduled',   'SIDEBAR', 302, 20, NULL, TRUE),
(323, 'In Progress',     'refresh-cw', '/operations/moves/in-progress', 'SIDEBAR', 302, 30, NULL, TRUE),
(324, 'Completed',       'check-all',  '/operations/moves/completed',   'SIDEBAR', 302, 40, NULL, TRUE),
(325, 'Cancelled',       'x',          '/operations/moves/cancelled',   'SIDEBAR', 302, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(331, 'Dispatch Board',   'layers',  '/operations/dispatch/board',          'SIDEBAR', 303, 10, NULL, TRUE),
(332, 'Assign Crew',      'users',   '/operations/dispatch/assign-crew',    'SIDEBAR', 303, 20, NULL, TRUE),
(333, 'Assign Vehicles',  'truck',   '/operations/dispatch/assign-vehicles','SIDEBAR', 303, 30, NULL, TRUE),
(334, 'Route Planning',   'compass', '/operations/dispatch/routes',         'SIDEBAR', 303, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(341, 'Live Tracking',  'eye',            '/operations/tracking/live',     'SIDEBAR', 304, 10, NULL, TRUE),
(342, 'Delayed Jobs',   'alert-triangle', '/operations/tracking/delayed',  'SIDEBAR', 304, 20, NULL, TRUE),
(343, 'Job Timeline',   'list',           '/operations/tracking/timeline', 'SIDEBAR', 304, 30, NULL, TRUE),
(344, 'Activity Logs',  'file-text',      '/operations/tracking/logs',     'SIDEBAR', 304, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(351, 'Property Inventory', 'home',     '/operations/inventory/property', 'SIDEBAR', 305, 10, NULL, TRUE),
(352, 'Room Inventory',     'columns',  '/operations/inventory/room',     'SIDEBAR', 305, 20, NULL, TRUE),
(353, 'Special Items',      'star',     '/operations/inventory/special',  'SIDEBAR', 305, 30, NULL, TRUE),
(354, 'Weight Estimation',  'activity', '/operations/inventory/weight',   'SIDEBAR', 305, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Fleet Management sub-children (parent_id=501)
-- Final paths from V5 applied directly (V4 intermediate paths superseded)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(511, 'Vehicle Types',      'truck',         '/resources/fleet/vehicle-types',  'SIDEBAR', 501, 10,  NULL, TRUE),
(512, 'Vehicle Makes',      'tag',           '/resources/fleet/vehicle-makes',  'SIDEBAR', 501, 20,  NULL, TRUE),
(513, 'Vehicle Models',     'layers',        '/resources/fleet/vehicle-models', 'SIDEBAR', 501, 30,  NULL, TRUE),
(514, 'Vehicles',           'truck',         '/resources/fleet/vehicles',       'SIDEBAR', 501, 40,  NULL, TRUE),
(515, 'Vehicle Categories', 'layers',        '/resources/fleet/categories',     'SIDEBAR', 501, 50,  NULL, TRUE),
(516, 'Vehicle Insurance',  'shield',        '/resources/fleet/insurance',      'SIDEBAR', 501, 60,  NULL, TRUE),
(517, 'Documents',          'file',          '/resources/fleet/documents',      'SIDEBAR', 501, 70,  NULL, TRUE),
(518, 'Fuel Logs',          'droplet',       '/resources/fleet/fuel-logs',      'SIDEBAR', 501, 80,  NULL, TRUE),
(519, 'Inspections',        'check-square',  '/resources/fleet/inspections',    'SIDEBAR', 501, 90,  NULL, TRUE),
(520, 'Maintenance',        'tool',          '/resources/fleet/maintenance',    'SIDEBAR', 501, 100, NULL, TRUE),
(521, 'Assignments',        'user-check',    '/resources/fleet/assignments',    'SIDEBAR', 501, 110, NULL, TRUE),
(522, 'Incidents',          'alert-triangle','/resources/fleet/incidents',      'SIDEBAR', 501, 120, NULL, TRUE),
(523, 'Tracking',           'map-pin',       '/resources/fleet/tracking',       'SIDEBAR', 501, 130, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Crew Management sub-children (parent_id=502)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(531, 'Crew Members',        'user',     '/resources/crew/members',      'SIDEBAR', 502, 10, NULL, TRUE),
(532, 'Teams',               'users',    '/resources/crew/teams',        'SIDEBAR', 502, 20, NULL, TRUE),
(533, 'Crew Schedules',      'calendar', '/resources/crew/schedules',    'SIDEBAR', 502, 30, NULL, TRUE),
(534, 'Availability',        'clock',    '/resources/crew/availability', 'SIDEBAR', 502, 40, NULL, TRUE),
(535, 'Performance Ratings', 'star',     '/resources/crew/performance',  'SIDEBAR', 502, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Warehouses sub-children (parent_id=503)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(541, 'Warehouses',          'database',  '/resources/warehouses/all',       'SIDEBAR', 503, 10, NULL, TRUE),
(542, 'Storage Units',       'layers',    '/resources/warehouses/units',     'SIDEBAR', 503, 20, NULL, TRUE),
(543, 'Stored Items',        'box',       '/resources/warehouses/items',     'SIDEBAR', 503, 30, NULL, TRUE),
(544, 'Storage Contracts',   'file-text', '/resources/warehouses/contracts', 'SIDEBAR', 503, 40, NULL, TRUE),
(545, 'Warehouse Transfers', 'move',      '/resources/warehouses/transfers', 'SIDEBAR', 503, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Assets sub-children (parent_id=504)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(551, 'Asset Registry',    'list',          '/resources/assets/registry',    'SIDEBAR', 504, 10, NULL, TRUE),
(552, 'Asset Categories',  'grid',          '/resources/assets/categories',  'SIDEBAR', 504, 20, NULL, TRUE),
(553, 'Asset Assignment',  'user-check',    '/resources/assets/assignment',  'SIDEBAR', 504, 30, NULL, TRUE),
(554, 'Asset Maintenance', 'settings',      '/resources/assets/maintenance', 'SIDEBAR', 504, 40, NULL, TRUE),
(555, 'Depreciation',      'trending-down', '/resources/assets/depreciation','SIDEBAR', 504, 50, NULL, TRUE),
(556, 'Asset Disposal',    'trash',         '/resources/assets/disposal',    'SIDEBAR', 504, 60, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Inventory sub-children (parent_id=505)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(561, 'Packing Materials',  'archive',    '/resources/inventory/packing',      'SIDEBAR', 505, 10, NULL, TRUE),
(562, 'Consumables',        'box',        '/resources/inventory/consumables',  'SIDEBAR', 505, 20, NULL, TRUE),
(563, 'Stock Levels',       'bar-chart-2','/resources/inventory/stock',        'SIDEBAR', 505, 30, NULL, TRUE),
(564, 'Stock Transfers',    'repeat',     '/resources/inventory/transfers',    'SIDEBAR', 505, 40, NULL, TRUE),
(565, 'Stock Adjustments',  'sliders',    '/resources/inventory/adjustments',  'SIDEBAR', 505, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Vendors sub-children (parent_id=506)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(571, 'Vendors',            'briefcase',  '/resources/vendors/all',         'SIDEBAR', 506, 10, NULL, TRUE),
(572, 'Vendor Contracts',   'file-text',  '/resources/vendors/contracts',   'SIDEBAR', 506, 20, NULL, TRUE),
(573, 'Vendor Performance', 'star',       '/resources/vendors/performance', 'SIDEBAR', 506, 30, NULL, TRUE),
(574, 'Vendor Payments',    'credit-card','/resources/vendors/payments',    'SIDEBAR', 506, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Finance sub-children
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(711, 'Customer Invoices', 'user',          '/finance/invoices/customer', 'SIDEBAR', 701, 10, NULL, TRUE),
(712, 'Draft Invoices',    'edit',          '/finance/invoices/draft',    'SIDEBAR', 701, 20, NULL, TRUE),
(713, 'Paid Invoices',     'check-circle',  '/finance/invoices/paid',     'SIDEBAR', 701, 30, NULL, TRUE),
(714, 'Overdue Invoices',  'alert-circle',  '/finance/invoices/overdue',  'SIDEBAR', 701, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(721, 'Customer Payments', 'arrow-down-left', '/finance/payments/customer', 'SIDEBAR', 702, 10, NULL, TRUE),
(722, 'Vendor Payments',   'arrow-up-right',  '/finance/payments/vendor',   'SIDEBAR', 702, 20, NULL, TRUE),
(723, 'Refunds',           'rotate-ccw',      '/finance/payments/refunds',  'SIDEBAR', 702, 30, NULL, TRUE),
(724, 'Payment Methods',   'settings',        '/finance/payments/methods',  'SIDEBAR', 702, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(731, 'Fuel Expenses',        'droplet',   '/finance/expenses/fuel',        'SIDEBAR', 703, 10, NULL, TRUE),
(732, 'Maintenance Expenses', 'tool',      '/finance/expenses/maintenance', 'SIDEBAR', 703, 20, NULL, TRUE),
(733, 'Payroll Expenses',     'users',     '/finance/expenses/payroll',     'SIDEBAR', 703, 30, NULL, TRUE),
(734, 'Other Expenses',       'file-text', '/finance/expenses/other',       'SIDEBAR', 703, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(741, 'Chart Of Accounts', 'list',     '/finance/accounting/coa',          'SIDEBAR', 701, 10, NULL, TRUE),
(742, 'Journal Entries',   'book',     '/finance/accounting/journal',      'SIDEBAR', 701, 20, NULL, TRUE),
(743, 'General Ledger',    'book-open','/finance/accounting/ledger',       'SIDEBAR', 701, 30, NULL, TRUE),
(744, 'Trial Balance',     'activity', '/finance/accounting/trial-balance','SIDEBAR', 701, 40, NULL, TRUE),
(745, 'Fiscal Years',      'calendar', '/finance/accounting/fiscal-years', 'SIDEBAR', 701, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(751, 'Tax Rates',   'percent',  '/finance/taxes/rates',   'SIDEBAR', 705, 10, NULL, TRUE),
(752, 'Tax Rules',   'settings', '/finance/taxes/rules',   'SIDEBAR', 705, 20, NULL, TRUE),
(753, 'Tax Reports', 'file-text','/finance/taxes/reports', 'SIDEBAR', 705, 30, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(761, 'Insurance Policies', 'file-text',   '/finance/insurance/policies',    'SIDEBAR', 706, 10, NULL, TRUE),
(762, 'Claims',             'file-plus',   '/finance/insurance/claims',      'SIDEBAR', 706, 20, NULL, TRUE),
(763, 'Damaged Items',      'box',         '/finance/insurance/damaged-items','SIDEBAR',706, 30, NULL, TRUE),
(764, 'Claim Settlements',  'check-square','/finance/insurance/settlements', 'SIDEBAR', 706, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- HR sub-children
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(811, 'Employee Directory', 'user',      '/hr/employees/directory',    'SIDEBAR', 801, 10, NULL, TRUE),
(812, 'Designations',       'briefcase', '/hr/employees/designations', 'SIDEBAR', 801, 20, NULL, TRUE),
(813, 'Departments',        'layers',    '/hr/employees/departments',  'SIDEBAR', 801, 30, NULL, TRUE),
(814, 'Contracts',          'file-text', '/hr/employees/contracts',    'SIDEBAR', 801, 40, NULL, TRUE),
(815, 'Documents',          'file',      '/hr/employees/documents',    'SIDEBAR', 801, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(821, 'Daily Attendance',    'check-square', '/hr/attendance/daily',   'SIDEBAR', 802, 10, NULL, TRUE),
(822, 'Attendance Logs',     'list',         '/hr/attendance/logs',    'SIDEBAR', 802, 20, NULL, TRUE),
(823, 'Attendance Reports',  'file-text',    '/hr/attendance/reports', 'SIDEBAR', 802, 30, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(831, 'Leave Requests', 'mail',     '/hr/leave/requests',  'SIDEBAR', 803, 10, NULL, TRUE),
(832, 'Leave Types',    'sliders',  '/hr/leave/types',     'SIDEBAR', 803, 20, NULL, TRUE),
(833, 'Leave Balances', 'activity', '/hr/leave/balances',  'SIDEBAR', 803, 30, NULL, TRUE),
(834, 'Leave Calendar', 'calendar', '/hr/leave/calendar',  'SIDEBAR', 803, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(841, 'Salary Structures',   'layers',     '/hr/payroll/structures', 'SIDEBAR', 804, 10, NULL, TRUE),
(842, 'Payroll Processing',  'refresh-cw', '/hr/payroll/process',    'SIDEBAR', 804, 20, NULL, TRUE),
(843, 'Payslips',            'file-text',  '/hr/payroll/payslips',   'SIDEBAR', 804, 30, NULL, TRUE),
(844, 'Bonuses',             'gift',       '/hr/payroll/bonuses',    'SIDEBAR', 804, 40, NULL, TRUE),
(845, 'Deductions',          'minus-circle','/hr/payroll/deductions','SIDEBAR', 804, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(851, 'Job Positions',   'briefcase', '/hr/recruitment/positions',  'SIDEBAR', 805, 10, NULL, TRUE),
(852, 'Applicants',      'users',     '/hr/recruitment/applicants', 'SIDEBAR', 805, 20, NULL, TRUE),
(853, 'Interviews',      'calendar',  '/hr/recruitment/interviews', 'SIDEBAR', 805, 30, NULL, TRUE),
(854, 'Hiring Pipeline', 'activity',  '/hr/recruitment/pipeline',  'SIDEBAR', 805, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(861, 'Reviews',    'check-square',  '/hr/performance/reviews',    'SIDEBAR', 806, 10, NULL, TRUE),
(862, 'KPIs',       'trending-up',   '/hr/performance/kpis',       'SIDEBAR', 806, 20, NULL, TRUE),
(863, 'Promotions', 'trending-up',   '/hr/performance/promotions', 'SIDEBAR', 806, 30, NULL, TRUE),
(864, 'Warnings',   'alert-octagon', '/hr/performance/warnings',   'SIDEBAR', 806, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Administration sub-children
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1111, 'Users Management', 'user',  '/system/users',        'SIDEBAR', 1101, 10, NULL, TRUE),
-- NOTE: id=1112 (old Roles Management) excluded - replaced by RBAC section below
-- NOTE: id=1113 (old Permissions under user mgmt) excluded - replaced by RBAC section below
(1114, 'User Groups',      'users', '/admin/users/groups',  'SIDEBAR', 1101, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1121, 'Branches',     'home',    '/admin/org/branches',    'SIDEBAR', 1102, 10, NULL, TRUE),
(1122, 'Departments',  'layers',  '/admin/org/departments', 'SIDEBAR', 1102, 20, NULL, TRUE),
(1123, 'Teams',        'users',   '/admin/org/teams',       'SIDEBAR', 1102, 30, NULL, TRUE),
(1124, 'Locations',    'map-pin', '/admin/org/locations',   'SIDEBAR', 1102, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1131, 'Approval Flows',       'check-square', '/admin/workflows/approvals',     'SIDEBAR', 1102, 10, NULL, TRUE),
(1132, 'Booking Workflow',     'calendar',     '/admin/workflows/bookings',      'SIDEBAR', 1102, 20, NULL, TRUE),
(1133, 'Quote Workflow',       'file-text',    '/admin/workflows/quotes',        'SIDEBAR', 1102, 30, NULL, TRUE),
(1134, 'Notification Workflow','bell',         '/admin/workflows/notifications', 'SIDEBAR', 1102, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1141, 'Email Templates',    'mail',           '/admin/notifications/email',    'SIDEBAR', 1104, 10, NULL, TRUE),
(1142, 'SMS Templates',      'phone',          '/admin/notifications/sms',      'SIDEBAR', 1104, 20, NULL, TRUE),
(1143, 'WhatsApp Templates', 'message-square', '/admin/notifications/whatsapp', 'SIDEBAR', 1104, 30, NULL, TRUE),
(1144, 'Push Templates',     'bell',           '/admin/notifications/push',     'SIDEBAR', 1104, 40, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Document Management sub-children (parent_id=1105)
-- NOTE: ids 1151-1153 (old conflicting RBAC-era document items) are excluded.
--       Using new ids to avoid conflicts.
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1156, 'Document Types Management', 'file',     '/system/document-types',       'SIDEBAR', 1105, 10, NULL, TRUE),
(1157, 'Templates',                 'copy',     '/admin/documents/templates',   'SIDEBAR', 1105, 20, NULL, TRUE),
(1158, 'Storage Settings',          'database', '/admin/documents/storage',     'SIDEBAR', 1105, 30, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1161, 'Payment Gateways', 'credit-card', '/admin/integrations/payments',  'SIDEBAR', 1106, 10, NULL, TRUE),
(1162, 'SMS Providers',    'phone',       '/admin/integrations/sms',       'SIDEBAR', 1106, 20, NULL, TRUE),
(1163, 'Email Providers',  'mail',        '/admin/integrations/email',     'SIDEBAR', 1106, 30, NULL, TRUE),
(1164, 'Google Maps',      'map',         '/admin/integrations/maps',      'SIDEBAR', 1106, 40, NULL, TRUE),
(1165, 'Webhooks',         'link',        '/admin/integrations/webhooks',  'SIDEBAR', 1106, 50, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1181, 'Navigation Management',  'menu',        '/system/navigation',   'SIDEBAR', 1108, 10, NULL, TRUE),
(1182, 'Move States Management', 'check-square','/system/move-states',  'SIDEBAR', 1108, 20, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Property Types sub-children (parent_id=405)
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(451, 'Property Categories', 'grid',         '/system/property-types/categories',       'SIDEBAR', 405, 10, NULL, TRUE),
(452, 'Property Sizes',      'maximize',     '/system/property-types/sizes',            'SIDEBAR', 405, 20, NULL, TRUE),
(453, 'Floor Types',         'layers',       '/system/property-types/floor-types',      'SIDEBAR', 405, 30, NULL, TRUE),
(454, 'Building Access',     'door-open',    '/system/property-types/building-access',  'SIDEBAR', 405, 40, NULL, TRUE),
(455, 'Parking Access',      'square',       '/system/property-types/parking-access',   'SIDEBAR', 405, 50, NULL, TRUE),
(456, 'Access Restrictions', 'alert-octagon','/system/property-types/restrictions',     'SIDEBAR', 405, 60, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Topbar & Profile items
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1201, 'Quick Estimation', 'calculator',  '/estimate/quick',  'TOPBAR',   NULL, 10, NULL, TRUE),
(1202, 'Support Chat',     'chat-bubble', '/support/live-chat','TOPBAR',  NULL, 20, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(1301, 'My Profile',        'user-profile', '/user/profile',  'PROFILE', NULL, 10, NULL, TRUE),
(1302, 'Account Settings',  'settings-gear','/user/settings', 'PROFILE', NULL, 20, NULL, TRUE),
(1303, 'Logout',            'sign-out',     '/logout',        'PROFILE', NULL, 30, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- =============================================================================
-- SECTION 1b: RBAC MENU ITEMS (from V11 area)
-- Parent: id=2001 RBAC under Administration (parent_id=11)
-- Children: Modules, Resources, Permissions, Roles, Role Permissions, User Roles
-- NOTE: Old conflicting ids 1112, 1113, 1115, 1151-1155 are excluded.
-- =============================================================================
INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(2001, 'RBAC', 'shield', '/system/rbac', 'SIDEBAR', 11, 25, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

INSERT INTO sec_menu_items (id, name, icon, path, section, parent_id, sort_order, permission_id, active) VALUES
(2011, 'Modules',           'layers',      '/system/rbac/modules',          'SIDEBAR', 2001, 10, NULL, TRUE),
(2012, 'Resources',         'database',    '/system/rbac/resources',        'SIDEBAR', 2001, 20, NULL, TRUE),
(2013, 'Permissions',       'key',         '/system/rbac/permissions',      'SIDEBAR', 2001, 30, NULL, TRUE),
(2014, 'Roles',             'shield',      '/system/rbac/roles',            'SIDEBAR', 2001, 40, NULL, TRUE),
(2015, 'Role Permissions',  'check-square','/system/rbac/role-permissions', 'SIDEBAR', 2001, 50, NULL, TRUE),
(2016, 'User Roles',        'user-check',  '/system/rbac/user-roles',       'SIDEBAR', 2001, 60, NULL, TRUE)
ON CONFLICT (id) DO NOTHING;

-- Reset sequence safely
SELECT setval('sec_menu_items_id_seq', 10000);

-- =============================================================================
-- SECTION 2: PROPERTY MASTER SEEDS
-- =============================================================================
INSERT INTO property_category (id, name, code, active, created_at)
VALUES
('8c0a8270-e4b7-4d92-a1b6-d2ef6eb793ff', 'Residential', 'RESIDENTIAL', true, now()),
('8c0a8270-e4b7-4d92-a1b6-d2ef6eb79302', 'Commercial',  'COMMERCIAL',  true, now()),
('8c0a8270-e4b7-4d92-a1b6-d2ef6eb79303', 'Industrial',  'INDUSTRIAL',  true, now()),
('8c0a8270-e4b7-4d92-a1b6-d2ef6eb79304', 'Storage',     'STORAGE',     true, now())
ON CONFLICT (id) DO NOTHING;

INSERT INTO occupancy_type (id, name, code, active, created_at)
VALUES
('f00df00d-f00d-f00d-f00d-000000000001', 'Owner Occupied',             'OWNER_OCCUPIED',    true, now()),
('f00df00d-f00d-f00d-f00d-000000000002', 'Tenant Occupied',            'TENANT_OCCUPIED',   true, now()),
('f00df00d-f00d-f00d-f00d-000000000003', 'Vacant Unit',                'VACANT',            true, now()),
('f00df00d-f00d-f00d-f00d-000000000004', 'Under Renovation',           'UNDER_RENOVATION',  true, now()),
('f00df00d-f00d-f00d-f00d-000000000005', 'Moving Out in Progress',     'MOVING_OUT',        true, now()),
('f00df00d-f00d-f00d-f00d-000000000006', 'New Move-In',                'NEW_MOVE_IN',       true, now()),
('f00df00d-f00d-f00d-f00d-000000000007', 'Short-Term Rental (Airbnb)', 'SHORT_TERM_RENTAL', true, now())
ON CONFLICT (id) DO NOTHING;

INSERT INTO building_access_type (id, name, code, active, created_at)
VALUES
('27c1a84f-702b-42fa-908d-a417df6ec91f', 'Dedicated Passenger Elevator',          'PASSENGER_ELEVATOR',        true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec902', 'Service Elevator Available',             'SERVICE_ELEVATOR',          true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec903', 'Freight Elevator (Large Capacity)',      'FREIGHT_ELEVATOR',          true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec904', 'No Elevator - Stair Access Only',        'STAIRS_ONLY',               true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec905', 'Shared Elevator (With Booking)',         'SHARED_ELEVATOR_BOOKING',   true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec906', 'Restricted Time Elevator Access',        'TIME_RESTRICTED_ELEVATOR',  true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec907', 'Cargo Lift Available (Industrial)',      'CARGO_LIFT',                true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec908', 'High-Rise Tower with Multiple Elevators','MULTI_ELEVATOR_TOWER',      true, now())
ON CONFLICT (id) DO NOTHING;

INSERT INTO parking_access_type (id, name, code, active, created_at)
VALUES
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1801', 'Direct Building Loading Zone',              'DIRECT_LOADING_ZONE', true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe189d', 'Basement Parking Access (B1/B2)',           'BASEMENT_PARKING',    true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1803', 'Dedicated Loading Bay',                     'LOADING_BAY',         true, now()),
('a1eed4f8-df2a-4318-ab93-ce117fef29df', 'Roadside Temporary Parking',               'ROADSIDE',            true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1805', 'Outdoor Open Parking Area',                'OUTDOOR_PARKING',     true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1806', 'Far Parking (50–100 meters)',               'FAR_PARKING',         true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1807', 'Very Far Parking (100m+)',                  'VERY_FAR_PARKING',    true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1808', 'Parking Permit Required (Community Controlled)','PERMIT_REQUIRED',true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1809', 'Underground Tight Parking (Low Clearance)', 'TIGHT_BASEMENT',      true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1810', 'Mall / Shared Commercial Parking',         'MALL_PARKING',        true, now())
ON CONFLICT (id) DO NOTHING;

INSERT INTO floor_type (id, name, code, active, created_at)
VALUES
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b001', 'Ground Floor',                        'GROUND',         true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b002', 'Mezzanine Level',                     'MEZZANINE',      true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b01a', '1st Floor',                           'FLOOR_1',        true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b004', '2nd Floor',                           'FLOOR_2',        true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b005', '3rd Floor',                           'FLOOR_3',        true, now()),
('41c8fa57-226f-4cd3-a1bf-c2ab6ed8b01e', '4th Floor',                           'FLOOR_4',        true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b007', '5th Floor',                           'FLOOR_5',        true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b008', '6–10 Floors (Mid Rise)',              'MID_RISE',       true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b009', '11–20 Floors (High Rise)',            'HIGH_RISE',      true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b010', '21–40 Floors (Very High Rise)',       'VERY_HIGH_RISE', true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b011', '40+ Floors (Ultra High Rise Dubai Towers)','ULTRA_HIGH_RISE',true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b012', 'Penthouse Level',                     'PENTHOUSE',      true, now())
ON CONFLICT (id) DO NOTHING;

INSERT INTO access_restriction_type (id, name, code, active, created_at)
VALUES
('cda814bf-602c-4ef7-b28f-d6a13dfeb901', 'No Restriction',                                         'NONE',               true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb9a5', 'Security Gate Approval Required',                        'SECURITY_APPROVAL',  true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb903', 'Move-In Permit Required (Community Management)',          'MOVE_IN_PERMIT',     true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb904', 'Move-Out Permit Required',                               'MOVE_OUT_PERMIT',    true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb905', 'Time Window Restricted (9AM–5PM only)',                  'TIME_WINDOW',        true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb906', 'Elevator Booking Mandatory',                             'ELEVATOR_BOOKING',   true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb907', 'Key/Card Access Required',                               'KEY_CARD',           true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb908', 'Owner Presence Required',                                'OWNER_PRESENT',      true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb909', 'Security Escort Required',                               'SECURITY_ESCORT',    true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb910', 'Community Management Approval (Emaar/Nakheel/Damac)',    'COMMUNITY_APPROVAL', true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb911', 'Weekend Moving Restriction',                             'WEEKEND_RESTRICTION',true, now())
ON CONFLICT (id) DO NOTHING;

INSERT INTO property_type (id, category_id, name, code, active, created_at)
VALUES
-- RESIDENTIAL
('76d8b671-55e1-4c60-a249-f0db66ea6f0e', (SELECT id FROM property_category WHERE code = 'RESIDENTIAL'), 'Apartment',       'RES_APARTMENT',     true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f02', (SELECT id FROM property_category WHERE code = 'RESIDENTIAL'), 'Villa',           'RES_VILLA',         true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f03', (SELECT id FROM property_category WHERE code = 'RESIDENTIAL'), 'Townhouse',       'RES_TOWNHOUSE',     true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f04', (SELECT id FROM property_category WHERE code = 'RESIDENTIAL'), 'Compound Villa',  'RES_COMPOUND_VILLA',true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f05', (SELECT id FROM property_category WHERE code = 'RESIDENTIAL'), 'Penthouse',       'RES_PENTHOUSE',     true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f06', (SELECT id FROM property_category WHERE code = 'RESIDENTIAL'), 'Studio Unit',     'RES_STUDIO',        true, now()),
-- COMMERCIAL
('76d8b671-55e1-4c60-a249-f0db66ea6f07', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Office',                  'COM_OFFICE',      true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f08', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Retail Shop',             'COM_RETAIL_SHOP', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f09', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Restaurant / Cafe',       'COM_RESTAURANT',  true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f10', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Clinic / Medical Center', 'COM_CLINIC',      true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f11', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Salon / Spa',             'COM_SALON',       true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f12', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Showroom',                'COM_SHOWROOM',    true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f13', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Warehouse (Commercial Use)','COM_WAREHOUSE',  true, now()),
-- INDUSTRIAL
('76d8b671-55e1-4c60-a249-f0db66ea6f14', (SELECT id FROM property_category WHERE code = 'INDUSTRIAL'), 'Factory',         'IND_FACTORY',    true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f15', (SELECT id FROM property_category WHERE code = 'INDUSTRIAL'), 'Workshop',        'IND_WORKSHOP',   true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f16', (SELECT id FROM property_category WHERE code = 'INDUSTRIAL'), 'Industrial Yard', 'IND_YARD',       true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f17', (SELECT id FROM property_category WHERE code = 'INDUSTRIAL'), 'Production Unit', 'IND_PRODUCTION', true, now()),
-- STORAGE
('76d8b671-55e1-4c60-a249-f0db66ea6f18', (SELECT id FROM property_category WHERE code = 'STORAGE'), 'Self Storage Unit',  'STO_SELF_UNIT',  true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f19', (SELECT id FROM property_category WHERE code = 'STORAGE'), 'Warehouse Storage',  'STO_WAREHOUSE',  true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f20', (SELECT id FROM property_category WHERE code = 'STORAGE'), 'Container Storage',  'STO_CONTAINER',  true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f21', (SELECT id FROM property_category WHERE code = 'STORAGE'), 'Cold Storage Unit',  'STO_COLD',       true, now())
ON CONFLICT (id) DO NOTHING;

INSERT INTO property_size (id, type_id, name, code, unit_type, active, created_at)
VALUES
-- APARTMENTS
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d01', (SELECT id FROM property_type WHERE code = 'RES_APARTMENT'), 'Studio',             'SIZE_STUDIO',   'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d05', (SELECT id FROM property_type WHERE code = 'RES_APARTMENT'), '1 Bedroom (1BR)',    'SIZE_1BR',      'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d03', (SELECT id FROM property_type WHERE code = 'RES_APARTMENT'), '2 Bedroom (2BR)',    'SIZE_2BR',      'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d04', (SELECT id FROM property_type WHERE code = 'RES_APARTMENT'), '3 Bedroom (3BR)',    'SIZE_3BR',      'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d06', (SELECT id FROM property_type WHERE code = 'RES_APARTMENT'), '4 Bedroom (4BR)',    'SIZE_4BR',      'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d07', (SELECT id FROM property_type WHERE code = 'RES_APARTMENT'), '5+ Bedroom Luxury',  'SIZE_5BR_PLUS', 'UNIT', true, now()),
-- VILLAS
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d08', (SELECT id FROM property_type WHERE code = 'RES_VILLA'), 'Small Villa (2–3 BR)',       'SIZE_SMALL_VILLA',  'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d09', (SELECT id FROM property_type WHERE code = 'RES_VILLA'), 'Medium Villa (4–5 BR)',      'SIZE_MEDIUM_VILLA', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d10', (SELECT id FROM property_type WHERE code = 'RES_VILLA'), 'Large Villa (6+ BR)',        'SIZE_LARGE_VILLA',  'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d11', (SELECT id FROM property_type WHERE code = 'RES_VILLA'), 'Luxury Villa with Majlis',   'SIZE_LUXURY_VILLA', 'UNIT', true, now()),
-- TOWNHOUSES
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d12', (SELECT id FROM property_type WHERE code = 'RES_TOWNHOUSE'), '2 Bedroom Townhouse', 'SIZE_TOWN_2BR', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d13', (SELECT id FROM property_type WHERE code = 'RES_TOWNHOUSE'), '3 Bedroom Townhouse', 'SIZE_TOWN_3BR', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d14', (SELECT id FROM property_type WHERE code = 'RES_TOWNHOUSE'), '4 Bedroom Townhouse', 'SIZE_TOWN_4BR', 'UNIT', true, now()),
-- OFFICES
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d15', (SELECT id FROM property_type WHERE code = 'COM_OFFICE'), 'Small Office (1–5 desks)',    'SIZE_OFF_SMALL',  'DESK', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d16', (SELECT id FROM property_type WHERE code = 'COM_OFFICE'), 'Medium Office (6–20 desks)',   'SIZE_OFF_MEDIUM', 'DESK', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d17', (SELECT id FROM property_type WHERE code = 'COM_OFFICE'), 'Large Office (20–50 desks)',   'SIZE_OFF_LARGE',  'DESK', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d18', (SELECT id FROM property_type WHERE code = 'COM_OFFICE'), 'Corporate Office (50+ desks)', 'SIZE_OFF_CORP',   'DESK', true, now()),
-- RETAIL
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d19', (SELECT id FROM property_type WHERE code = 'COM_RETAIL_SHOP'), 'Small Shop',            'SIZE_SHOP_SMALL',  'SQFT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d20', (SELECT id FROM property_type WHERE code = 'COM_RETAIL_SHOP'), 'Medium Retail Store',   'SIZE_SHOP_MEDIUM', 'SQFT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d21', (SELECT id FROM property_type WHERE code = 'COM_RETAIL_SHOP'), 'Large Showroom Retail', 'SIZE_SHOP_LARGE',  'SQFT', true, now()),
-- WAREHOUSE
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d22', (SELECT id FROM property_type WHERE code = 'COM_WAREHOUSE'), 'Small Warehouse',  'SIZE_WH_SMALL',  'CBM', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d23', (SELECT id FROM property_type WHERE code = 'COM_WAREHOUSE'), 'Medium Warehouse', 'SIZE_WH_MEDIUM', 'CBM', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d24', (SELECT id FROM property_type WHERE code = 'COM_WAREHOUSE'), 'Large Warehouse',  'SIZE_WH_LARGE',  'CBM', true, now()),
-- STORAGE
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d25', (SELECT id FROM property_type WHERE code = 'STO_SELF_UNIT'), 'Locker Size',   'SIZE_STORAGE_LOCKER', 'CBM', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d26', (SELECT id FROM property_type WHERE code = 'STO_SELF_UNIT'), 'Small Unit',    'SIZE_STORAGE_SMALL',  'CBM', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d27', (SELECT id FROM property_type WHERE code = 'STO_SELF_UNIT'), 'Medium Unit',   'SIZE_STORAGE_MEDIUM', 'CBM', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d28', (SELECT id FROM property_type WHERE code = 'STO_SELF_UNIT'), 'Large Unit',    'SIZE_STORAGE_LARGE',  'CBM', true, now())
ON CONFLICT (id) DO NOTHING;

-- =============================================================================
-- SECTION 3: MOVE PHASE & STATUS SEEDS
-- =============================================================================
INSERT INTO move_phase (id, version, code, name, sequence_no, active, created_at)
VALUES
('02868ff1-7443-4e44-8d48-356a7c36a4be', 0, 'REQUEST',    'Request Phase',              1, true, now()),
('f5dc2118-0524-4f81-a9f8-cf9bb1112443', 0, 'QUOTATION',  'Quotation Phase',            2, true, now()),
('4c207d57-3a1d-44a3-a006-2580c85025d5', 0, 'BOOKING',    'Booking Phase',              3, true, now()),
('9df848b5-bb4e-4f76-880f-90e85f4f895c', 0, 'OPERATIONS', 'Operations Phase',           4, true, now()),
('d7bb24f3-dc90-410a-8bf8-2a2be10c7931', 0, 'PICKUP',     'Pickup Phase',               5, true, now()),
('99fa2f33-b18f-4cb1-80a5-f8ea55ebcde2', 0, 'TRANSIT',    'Transit Phase',              6, true, now()),
('6df926c4-ee80-496a-84fb-4b5fb4e81561', 0, 'DELIVERY',   'Delivery Phase',             7, true, now()),
('ea68e22d-6215-4c6e-821f-eb3b1c67675f', 0, 'COMPLETION', 'Completion Phase',           8, true, now()),
('79be40de-8d06-444d-8cf8-410fb556be4f', 0, 'EXCEPTION',  'Exception / Failure States', 9, true, now())
ON CONFLICT (id) DO NOTHING;

INSERT INTO move_status (id, version, code, name, phase_id, description, sequence_no, is_final, active, color_code, customer_visible, internal_only, created_at)
VALUES
-- Request Phase (1)
('fd9a693a-ba36-4054-859a-ab739783d170', 0, 'REQUESTED',         'Customer submitted move request',   (SELECT id FROM move_phase WHERE code = 'REQUEST'),    'Customer submitted move request',   1, false, true, '#3B82F6', true,  false, now()),
('4660f8f9-5e66-4e9d-bc43-5f673bb22433', 0, 'UNDER_REVIEW',      'Operations reviewing request',      (SELECT id FROM move_phase WHERE code = 'REQUEST'),    'Operations reviewing request',      2, false, true, '#3B82F6', true,  false, now()),
('479daad9-23ac-40b4-8a68-487b26485a80', 0, 'AWAITING_DETAILS',  'Waiting for customer info/photos',  (SELECT id FROM move_phase WHERE code = 'REQUEST'),    'Waiting for customer info/photos',  3, false, true, '#3B82F6', true,  false, now()),
('d3db6e69-b164-419c-b287-a9adcc8b4137', 0, 'SURVEY_SCHEDULED',  'Physical/virtual survey planned',   (SELECT id FROM move_phase WHERE code = 'REQUEST'),    'Physical/virtual survey planned',   4, false, true, '#3B82F6', true,  false, now()),
('f7cdf1c6-a562-4a6f-be09-c51072813716', 0, 'SURVEY_COMPLETED',  'Survey completed',                  (SELECT id FROM move_phase WHERE code = 'REQUEST'),    'Survey completed',                  5, false, true, '#3B82F6', true,  false, now()),
-- Quotation Phase (2)
('d9a1bbfa-0f8b-4602-8dc9-691c2dcdde3f', 0, 'QUOTATION_IN_PROGRESS', 'Pricing being prepared',       (SELECT id FROM move_phase WHERE code = 'QUOTATION'),  'Pricing being prepared',            1, false, true, '#F59E0B', false, true,  now()),
('2eca3410-2fbe-417c-9bbb-a45ea149ab39', 0, 'QUOTATION_SENT',        'Quote sent to customer',        (SELECT id FROM move_phase WHERE code = 'QUOTATION'),  'Quote sent to customer',            2, false, true, '#F59E0B', true,  false, now()),
('165f5215-e1dd-42f8-ac13-796363f7b6a2', 0, 'QUOTATION_VIEWED',      'Customer viewed quotation',     (SELECT id FROM move_phase WHERE code = 'QUOTATION'),  'Customer viewed quotation',         3, false, true, '#F59E0B', false, true,  now()),
('c293a003-ddd9-4bcd-9772-11f56cb6226c', 0, 'NEGOTIATION_IN_PROGRESS','Pricing negotiation ongoing',  (SELECT id FROM move_phase WHERE code = 'QUOTATION'),  'Pricing negotiation ongoing',       4, false, true, '#F59E0B', true,  false, now()),
('91d86eba-584d-411a-9e52-b1874aedb0bb', 0, 'QUOTATION_APPROVED',    'Customer approved quote',       (SELECT id FROM move_phase WHERE code = 'QUOTATION'),  'Customer approved quote',           5, false, true, '#F59E0B', true,  false, now()),
('24992591-1f25-4ee5-bae1-9877102c7897', 0, 'QUOTATION_REJECTED',    'Customer rejected quote',       (SELECT id FROM move_phase WHERE code = 'QUOTATION'),  'Customer rejected quote',           6, false, true, '#F59E0B', true,  false, now()),
-- Booking Phase (3)
('e86ce797-f8e8-4ac0-8896-eb5b5cb59baa', 0, 'CONFIRMED',        'Booking confirmed',            (SELECT id FROM move_phase WHERE code = 'BOOKING'), 'Booking confirmed',            1, false, true, '#10B981', true,  false, now()),
('678ab4b1-e0f2-4f08-bef0-348a41b32913', 0, 'PENDING_PAYMENT',  'Awaiting advance payment',     (SELECT id FROM move_phase WHERE code = 'BOOKING'), 'Awaiting advance payment',     2, false, true, '#10B981', true,  false, now()),
('f7d08540-2858-46ad-9f86-a28b3f2608e7', 0, 'PARTIALLY_PAID',   'Partial payment received',     (SELECT id FROM move_phase WHERE code = 'BOOKING'), 'Partial payment received',     3, false, true, '#10B981', true,  false, now()),
('602287d0-a299-48c3-a693-4a577bbeeb2c', 0, 'PAID',             'Full payment received',         (SELECT id FROM move_phase WHERE code = 'BOOKING'), 'Full payment received',         4, false, true, '#10B981', true,  false, now()),
('695cefa9-3b48-426c-8c83-cf17eb5b84ef', 0, 'INVOICE_GENERATED','Invoice created',               (SELECT id FROM move_phase WHERE code = 'BOOKING'), 'Invoice created',               5, false, true, '#10B981', true,  false, now()),
-- Operations Phase (4)
('bc6b9db7-8f8c-45bf-9d3f-24f4ed637c36', 0, 'ASSIGNMENT_PENDING',    'Waiting for mover assignment', (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Waiting for mover assignment', 1, false, true, '#F97316', true,  false, now()),
('33a1f74d-8c38-480b-b7aa-3de22d8a9aa0', 0, 'ASSIGNED',              'Team/driver assigned',         (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Team/driver assigned',         2, false, true, '#F97316', true,  false, now()),
('57dc0bad-3828-4762-8a4a-dfe56c18d448', 0, 'VEHICLE_ASSIGNED',      'Truck assigned',               (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Truck assigned',               3, false, true, '#F97316', false, true,  now()),
('e53a496d-b391-4df6-8f4d-30d3e142204d', 0, 'CREW_ASSIGNED',         'Movers assigned',              (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Movers assigned',              4, false, true, '#F97316', false, true,  now()),
('9f6b2d81-8f5c-404d-9e85-7e91ea345002', 0, 'SCHEDULED',             'Move officially scheduled',    (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Move officially scheduled',    5, false, true, '#F97316', true,  false, now()),
('9f6ba7ed-9869-4aee-9328-eac6dcb96ba5', 0, 'RESCHEDULE_REQUESTED',  'Customer requested new date',  (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Customer requested new date',  6, false, true, '#F97316', true,  false, now()),
('746f7475-61fe-4b2a-b18c-b7a906b03dac', 0, 'RESCHEDULED',           'Move rescheduled',             (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Move rescheduled',             7, false, true, '#F97316', true,  false, now()),
-- Pickup Phase (5)
('fe73627e-4978-4e35-aa92-d0c036d0e23c', 0, 'TEAM_EN_ROUTE_PICKUP', 'Team going to pickup',     (SELECT id FROM move_phase WHERE code = 'PICKUP'), 'Team going to pickup',     1, false, true, '#8B5CF6', true,  false, now()),
('2fa4bc51-7345-4ed8-98fa-7441c22ad661', 0, 'ARRIVED_AT_PICKUP',    'Team arrived',              (SELECT id FROM move_phase WHERE code = 'PICKUP'), 'Team arrived',              2, false, true, '#8B5CF6', true,  false, now()),
('e3276133-7be8-42f6-88ad-0de11de11d64', 0, 'PICKUP_STARTED',       'Packing/loading started',   (SELECT id FROM move_phase WHERE code = 'PICKUP'), 'Packing/loading started',   3, false, true, '#8B5CF6', true,  false, now()),
('36e385f8-1dc0-4035-a265-d10d8ab687ba', 0, 'PACKING_IN_PROGRESS',  'Packing ongoing',           (SELECT id FROM move_phase WHERE code = 'PICKUP'), 'Packing ongoing',           4, false, true, '#8B5CF6', true,  false, now()),
('0b0c7054-bd61-4dd6-942c-c5f997e9f9ad', 0, 'LOADING_IN_PROGRESS',  'Truck loading ongoing',     (SELECT id FROM move_phase WHERE code = 'PICKUP'), 'Truck loading ongoing',     5, false, true, '#8B5CF6', true,  false, now()),
('67136489-f8e3-4b8f-b516-1e36dbcd7bef', 0, 'PICKUP_COMPLETED',     'Pickup finished',           (SELECT id FROM move_phase WHERE code = 'PICKUP'), 'Pickup finished',           6, false, true, '#8B5CF6', true,  false, now()),
-- Transit Phase (6)
('f0b674f6-30a4-4b97-b2fb-bc7562aae69a', 0, 'IN_TRANSIT',          'Truck moving to destination', (SELECT id FROM move_phase WHERE code = 'TRANSIT'), 'Truck moving to destination', 1, false, true, '#6366F1', true,  false, now()),
('d0135594-7074-4d3c-8c04-bf2521d7db1b', 0, 'DELAYED',             'Move delayed',                (SELECT id FROM move_phase WHERE code = 'TRANSIT'), 'Move delayed',                2, false, true, '#6366F1', true,  false, now()),
('23ba8b63-a419-4191-a0e7-f47a3d227555', 0, 'STOPPED_TEMPORARILY', 'Temporary operational stop',  (SELECT id FROM move_phase WHERE code = 'TRANSIT'), 'Temporary operational stop',  3, false, true, '#6366F1', false, true,  now()),
-- Delivery Phase (7)
('708c9b06-7fa7-4f58-96ad-2c6fb20e7d73', 0, 'ARRIVED_AT_DROPOFF',       'Team reached destination',      (SELECT id FROM move_phase WHERE code = 'DELIVERY'), 'Team reached destination',      1, false, true, '#D97706', true,  false, now()),
('9b46467a-affe-433f-b0db-d663565a4edf', 0, 'UNLOADING_IN_PROGRESS',    'Unloading ongoing',             (SELECT id FROM move_phase WHERE code = 'DELIVERY'), 'Unloading ongoing',             2, false, true, '#D97706', true,  false, now()),
('eeab7f21-337f-46d1-abc5-243624005249', 0, 'UNPACKING_IN_PROGRESS',    'Unpacking ongoing',             (SELECT id FROM move_phase WHERE code = 'DELIVERY'), 'Unpacking ongoing',             3, false, true, '#D97706', true,  false, now()),
('37538247-cd3f-4614-a988-bbf06d493e23', 0, 'ASSEMBLY_IN_PROGRESS',     'Furniture assembly ongoing',    (SELECT id FROM move_phase WHERE code = 'DELIVERY'), 'Furniture assembly ongoing',    4, false, true, '#D97706', true,  false, now()),
('fa70f318-79a6-4efe-8389-fceb8420f3b8', 0, 'DELIVERY_COMPLETED',       'Delivery completed',            (SELECT id FROM move_phase WHERE code = 'DELIVERY'), 'Delivery completed',            5, false, true, '#D97706', true,  false, now()),
-- Completion Phase (8)
('3f3b73cb-2520-46b6-b813-07fab30f5b56', 0, 'CUSTOMER_CONFIRMATION_PENDING','Awaiting customer confirmation',(SELECT id FROM move_phase WHERE code = 'COMPLETION'), 'Awaiting customer confirmation', 1, false, true, '#059669', true,  false, now()),
('b2a108af-5af9-49ab-ba22-4502b61ed63f', 0, 'COMPLETED',                'Move completed successfully',   (SELECT id FROM move_phase WHERE code = 'COMPLETION'), 'Move completed successfully',   2, true,  true, '#059669', true,  false, now()),
('469c122f-3a16-4f14-be17-c83ebdb80819', 0, 'FEEDBACK_PENDING',         'Waiting for review',            (SELECT id FROM move_phase WHERE code = 'COMPLETION'), 'Waiting for review',            3, false, true, '#059669', true,  false, now()),
('a553c8d1-5dab-4436-81e8-14e8bcead23b', 0, 'CLOSED',                   'Fully closed operationally',    (SELECT id FROM move_phase WHERE code = 'COMPLETION'), 'Fully closed operationally',    4, true,  true, '#059669', false, true,  now()),
-- Exception Phase (9)
('cd92e2d4-1edd-4040-b0a2-2af1d4fa10b0', 0, 'ON_HOLD',            'Temporarily paused',            (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Temporarily paused',            1, false, true, '#EF4444', true, false, now()),
('f74f44a1-5f57-41be-a430-26b50e52b608', 0, 'CANCELLED',          'Cancelled',                     (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Cancelled',                     2, true,  true, '#EF4444', true, false, now()),
('7bbc4657-4447-4972-a4a8-62c5bdc34ea2', 0, 'FAILED',             'Operational failure',           (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Operational failure',           3, true,  true, '#EF4444', true, false, now()),
('7735c08e-82ff-42b9-9feb-d88a25f86208', 0, 'NO_SHOW_CUSTOMER',   'Customer unavailable',          (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Customer unavailable',          4, false, true, '#EF4444', true, false, now()),
('ff3b7010-c37d-40f5-84b9-143009464ac7', 0, 'NO_SHOW_TEAM',       'Team unavailable',              (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Team unavailable',              5, false, true, '#EF4444', true, false, now()),
('176fe951-3217-4a0f-a4f2-7b3ea53288c2', 0, 'PAYMENT_FAILED',     'Payment issue',                 (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Payment issue',                 6, false, true, '#EF4444', true, false, now()),
('f1d70872-b9e0-441a-bf0e-6095d19e7200', 0, 'DISPUTE_OPENED',     'Customer complaint/dispute',    (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Customer complaint/dispute',    7, false, true, '#EF4444', true, false, now()),
('3988153e-4d3f-4690-8196-3e5dde115e5a', 0, 'CLAIM_OPENED',       'Damage/missing item claim',     (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Damage/missing item claim',     8, false, true, '#EF4444', true, false, now()),
('168a6427-a7fb-44ab-8fb7-a3cb34e7cb55', 0, 'REFUND_IN_PROGRESS', 'Refund processing',             (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Refund processing',             9, false, true, '#EF4444', true, false, now()),
('cc7d0595-ef36-4235-bacc-57e5a537a8d3', 0, 'REFUNDED',           'Refund completed',              (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Refund completed',             10, true,  true, '#EF4444', true, false, now())
ON CONFLICT (id) DO NOTHING;

-- =============================================================================
-- SECTION 4: USER PROFILE SEEDS
-- =============================================================================
INSERT INTO tbl_user_profiles (user_id, created_at)
SELECT id, created_at FROM tbl_users
ON CONFLICT (user_id) DO NOTHING;

UPDATE tbl_user_profiles
SET first_name   = 'Admin',
    last_name    = 'User',
    phone_number = '+123456789',
    address      = 'Admin HQ'
WHERE user_id = (SELECT id FROM tbl_users WHERE username = 'admin');

UPDATE tbl_user_profiles
SET first_name   = 'Customer',
    last_name    = 'User',
    phone_number = '+987654321',
    address      = 'Customer Address'
WHERE user_id = (SELECT id FROM tbl_users WHERE username = 'customer');

-- =============================================================================
-- SECTION 5: VEHICLE MAINTENANCE TYPES
-- =============================================================================
INSERT INTO vehicle_maintenance_types (id, code, name, description) VALUES
-- Preventive Maintenance
(gen_random_uuid(), 'OIL_CHANGE',              'Engine Oil Change',                'Replace engine oil and oil filter according to service schedule'),
(gen_random_uuid(), 'PERIODIC_SERVICE',        'Periodic Service',                 'Scheduled preventive maintenance as per manufacturer guidelines'),
(gen_random_uuid(), 'LUBRICATION',             'Lubrication Service',              'Lubrication of moving components and joints'),
(gen_random_uuid(), 'FLUID_TOPUP',             'Fluid Top-Up',                     'Top-up engine oil, coolant, brake fluid, power steering fluid, and washer fluid'),
(gen_random_uuid(), 'AIR_FILTER',              'Air Filter Replacement',           'Replacement of engine air filter'),
(gen_random_uuid(), 'CABIN_FILTER',            'Cabin Air Filter Replacement',     'Replacement of cabin or AC filter'),
(gen_random_uuid(), 'FUEL_FILTER',             'Fuel Filter Replacement',          'Replacement of fuel filter'),
-- Engine & Drivetrain
(gen_random_uuid(), 'ENGINE_REPAIR',           'Engine Repair',                    'Engine diagnostics, repair, or overhaul'),
(gen_random_uuid(), 'TIMING_BELT',             'Timing Belt Replacement',          'Replacement of timing belt or timing chain components'),
(gen_random_uuid(), 'TRANSMISSION_SERVICE',    'Transmission Service',             'Transmission inspection, repair, or fluid replacement'),
(gen_random_uuid(), 'CLUTCH_REPAIR',           'Clutch Repair',                    'Clutch maintenance, repair, or replacement'),
(gen_random_uuid(), 'DIFFERENTIAL_SERVICE',    'Differential Service',             'Inspection and servicing of differential components'),
-- Brake System
(gen_random_uuid(), 'BRAKE_SERVICE',           'Brake Service',                    'Inspection and maintenance of braking system'),
(gen_random_uuid(), 'BRAKE_PAD_REPLACEMENT',   'Brake Pad Replacement',            'Replacement of brake pads'),
(gen_random_uuid(), 'BRAKE_DISC_REPLACEMENT',  'Brake Disc Replacement',           'Replacement of brake rotors/discs'),
(gen_random_uuid(), 'BRAKE_FLUID_CHANGE',      'Brake Fluid Change',               'Replacement of brake fluid'),
-- Tires & Wheels
(gen_random_uuid(), 'TIRE_REPLACEMENT',        'Tire Replacement',                 'Replacement of worn or damaged tires'),
(gen_random_uuid(), 'TIRE_ROTATION',           'Tire Rotation',                    'Rotation of tires to ensure even wear'),
(gen_random_uuid(), 'WHEEL_ALIGNMENT',         'Wheel Alignment',                  'Adjustment of wheel alignment'),
(gen_random_uuid(), 'WHEEL_BALANCING',         'Wheel Balancing',                  'Wheel balancing service'),
(gen_random_uuid(), 'PUNCTURE_REPAIR',         'Puncture Repair',                  'Repair of punctured tires'),
-- Battery & Electrical
(gen_random_uuid(), 'BATTERY_REPLACEMENT',     'Battery Replacement',              'Replacement of vehicle battery'),
(gen_random_uuid(), 'BATTERY_SERVICE',         'Battery Service',                  'Battery inspection, testing, and maintenance'),
(gen_random_uuid(), 'ALTERNATOR_REPAIR',       'Alternator Repair',                'Repair or replacement of alternator'),
(gen_random_uuid(), 'STARTER_MOTOR_REPAIR',    'Starter Motor Repair',             'Repair or replacement of starter motor'),
(gen_random_uuid(), 'ELECTRICAL_REPAIR',       'Electrical Repair',                'General electrical diagnostics and repairs'),
(gen_random_uuid(), 'LIGHTING_REPAIR',         'Lighting Repair',                  'Repair or replacement of vehicle lights'),
-- Cooling & AC
(gen_random_uuid(), 'COOLING_SYSTEM_SERVICE',  'Cooling System Service',           'Inspection and maintenance of cooling system'),
(gen_random_uuid(), 'RADIATOR_REPAIR',         'Radiator Repair',                  'Repair or replacement of radiator'),
(gen_random_uuid(), 'COOLANT_FLUSH',           'Coolant Flush',                    'Drain and replace coolant'),
(gen_random_uuid(), 'AC_SERVICE',              'Air Conditioning Service',         'Inspection and servicing of air conditioning system'),
(gen_random_uuid(), 'AC_GAS_REFILL',           'AC Gas Refill',                    'Refrigerant recharge for air conditioning system'),
-- Suspension & Steering
(gen_random_uuid(), 'SUSPENSION_REPAIR',       'Suspension Repair',                'Repair or replacement of suspension components'),
(gen_random_uuid(), 'SHOCK_ABSORBER_REPLACEMENT','Shock Absorber Replacement',     'Replacement of shock absorbers'),
(gen_random_uuid(), 'STEERING_REPAIR',         'Steering Repair',                  'Repair of steering system'),
(gen_random_uuid(), 'POWER_STEERING_SERVICE',  'Power Steering Service',           'Power steering maintenance and fluid replacement'),
-- Exhaust & Emissions
(gen_random_uuid(), 'EXHAUST_REPAIR',          'Exhaust System Repair',            'Repair of exhaust system components'),
(gen_random_uuid(), 'EMISSION_TEST',           'Emission Test',                    'Vehicle emission testing and compliance'),
(gen_random_uuid(), 'CATALYTIC_CONVERTER_REPAIR','Catalytic Converter Repair',     'Repair or replacement of catalytic converter'),
-- Inspection & Compliance
(gen_random_uuid(), 'VEHICLE_INSPECTION',      'Vehicle Inspection',               'General vehicle inspection'),
(gen_random_uuid(), 'SAFETY_INSPECTION',       'Safety Inspection',                'Roadworthiness and safety inspection'),
(gen_random_uuid(), 'PRE_TRIP_INSPECTION',     'Pre-Trip Inspection',              'Inspection before vehicle deployment'),
(gen_random_uuid(), 'POST_TRIP_INSPECTION',    'Post-Trip Inspection',             'Inspection after vehicle return'),
(gen_random_uuid(), 'REGISTRATION_RENEWAL',    'Registration Renewal',             'Vehicle registration compliance activity'),
(gen_random_uuid(), 'FITNESS_CERTIFICATE',     'Fitness Certificate',              'Vehicle fitness certification process'),
-- Body & Exterior
(gen_random_uuid(), 'BODY_REPAIR',             'Body Repair',                      'Body damage repair'),
(gen_random_uuid(), 'PAINT_WORK',              'Paint Work',                       'Vehicle painting and touch-ups'),
(gen_random_uuid(), 'WINDSHIELD_REPLACEMENT',  'Windshield Replacement',           'Replacement of damaged windshield'),
(gen_random_uuid(), 'GLASS_REPAIR',            'Glass Repair',                     'Repair of vehicle windows and glass'),
(gen_random_uuid(), 'DENT_REMOVAL',            'Dent Removal',                     'Removal of dents and body imperfections'),
-- Cleaning & Detailing
(gen_random_uuid(), 'VEHICLE_WASH',            'Vehicle Wash',                     'Exterior cleaning service'),
(gen_random_uuid(), 'INTERIOR_CLEANING',       'Interior Cleaning',                'Interior cleaning and sanitization'),
(gen_random_uuid(), 'DETAILING',               'Vehicle Detailing',                'Comprehensive vehicle detailing service'),
-- Emergency & Miscellaneous
(gen_random_uuid(), 'BREAKDOWN_REPAIR',        'Breakdown Repair',                 'Emergency roadside breakdown repair'),
(gen_random_uuid(), 'TOWING_SERVICE',          'Towing Service',                   'Vehicle towing and recovery'),
(gen_random_uuid(), 'ACCIDENT_REPAIR',         'Accident Repair',                  'Repair following accident damage'),
(gen_random_uuid(), 'DIAGNOSTIC_SERVICE',      'Diagnostic Service',               'Computerized diagnostics and fault analysis'),
(gen_random_uuid(), 'OTHER',                   'Other Maintenance',                'Maintenance activity not covered by predefined categories')
ON CONFLICT (code) DO NOTHING;

-- =============================================================================
-- SECTION 6: VEHICLE TYPES (from V2)
-- =============================================================================
INSERT INTO vehicle_types (id, version, created_at, created_by, updated_at, updated_by, deleted, deleted_at, deleted_by, code, name, active, description) VALUES
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'PICKUP',              'Pickup',              true, 'Small utility vehicle used for light moves and local deliveries'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'VAN',                 'Van',                 true, 'Medium-sized vehicle used for household relocation and small cargo'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'MINI_TRUCK',          'Mini Truck',          true, 'Light commercial truck used for urban and short-distance transport'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'TRUCK',               'Truck',               true, 'Standard heavy vehicle used for medium to large relocations'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'HEAVY_TRUCK',         'Heavy Truck',         true, 'Large capacity truck used for long-distance and bulk transport'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'TRAILER',             'Trailer',             true, 'High-capacity trailer used for industrial or large-scale logistics'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'CONTAINER_TRUCK',     'Container Truck',     true, 'Specialized truck used for container transport and shipping logistics'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'REFRIGERATED_VEHICLE','Refrigerated Vehicle', true,'Temperature-controlled vehicle used for sensitive goods and food transport')
ON CONFLICT (code) DO NOTHING;

-- =============================================================================
-- SECTION 7: DOCUMENT TYPES
-- =============================================================================
INSERT INTO document_types (id, version, created_at, created_by, updated_at, updated_by, deleted, code, name, active, mandatory, expiry_required, description)
VALUES
-- CORPORATE & LEGAL DOCUMENTS
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'CERTIFICATE_OF_INCORPORATION', 'Certificate of Incorporation',          true, true,  false, 'Official legal document confirming company registration'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'MEMORANDUM_OF_ASSOCIATION',    'Memorandum of Association (MoA)',        true, true,  false, 'Legal document establishing the company structure'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'ARTICLES_OF_ASSOCIATION',      'Articles of Association (AoA)',          true, true,  false, 'Rules governing internal management of the company'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'TRADE_LICENSE',                'Trade License',                          true, true,  true,  'Government permission to conduct specific business activities'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'TAX_IDENTIFICATION_CERT',      'Tax Identification Certificate',         true, true,  false, 'Official TIN/VAT registration document'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'NON_DISCLOSURE_AGREEMENT',     'Non-Disclosure Agreement (NDA)',         true, false, false, 'Confidentiality agreement with partners or clients'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'BOARD_RESOLUTION',             'Board Resolution',                       true, false, false, 'Formal record of decisions made by the Board of Directors'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'POWER_OF_ATTORNEY',            'Power of Attorney',                      true, false, true,  'Legal authorization to act on behalf of the company'),
-- HUMAN RESOURCES & EMPLOYEE DOCUMENTS
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'EMPLOYEE_ID_CARD',             'Employee ID Card',                       true, true,  false, 'Internal company identification card'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'OFFER_LETTER',                 'Offer Letter',                           true, false, false, 'Initial employment offer letter'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'EMPLOYMENT_AGREEMENT',         'Employment Agreement',                   true, true,  false, 'Formal employment contract detailing terms and conditions'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'EMPLOYEE_HANDBOOK_ACK',        'Employee Handbook Acknowledgement',      true, false, false, 'Signed receipt of company policies and rules'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'PAYSLIP',                      'Payroll Slip / Payslip',                 true, false, false, 'Monthly record of employee salary and deductions'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'EMPLOYEE_TAX_DECLARATION',     'Tax Declaration Form',                   true, false, false, 'Employee income tax and investment declarations'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'PERFORMANCE_APPRAISAL',        'Performance Appraisal Report',           true, false, false, 'Periodic employee performance review'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'DISCIPLINARY_ACTION_REPORT',   'Disciplinary Action Report',             true, false, false, 'Record of warnings or disciplinary actions taken'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'RESIGNATION_LETTER',           'Resignation Letter',                     true, false, false, 'Formal notice of employee resignation'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'EXPERIENCE_CERTIFICATE',       'Experience/Relieving Certificate',       true, false, false, 'Certificate issued to employee upon leaving the company'),
-- FINANCE & ACCOUNTING DOCUMENTS
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'PURCHASE_ORDER',               'Purchase Order (PO)',                    true, false, false, 'Official order issued to a vendor for goods or services'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'SALES_INVOICE',                'Sales Invoice',                          true, false, false, 'Invoice issued to clients for services rendered'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'VENDOR_CONTRACT',              'Vendor Contract',                        true, false, true,  'Agreement between the company and suppliers/subcontractors'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'BANK_STATEMENT',               'Bank Statement',                         true, false, false, 'Official summary of financial transactions'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'AUDIT_REPORT',                 'Audit Report',                           true, false, false, 'Internal or external financial audit summary'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'TAX_RETURN',                   'Tax Return',                             true, false, false, 'Annual or quarterly tax filing document'),
-- OPERATIONS & ADMINISTRATION DOCUMENTS
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'OFFICE_LEASE_AGREEMENT',       'Office Lease Agreement',                 true, false, true,  'Rental agreement for corporate or branch offices'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'UTILITY_BILL',                 'Utility Bill',                           true, false, false, 'Electricity, water, or internet bills for operations'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'ASSET_HANDOVER_FORM',          'Asset Handover Form',                    true, false, false, 'Record of physical assets (laptops, phones) given to staff'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'VISITOR_LOG',                  'Visitor Log',                            true, false, false, 'Record of external visitors to the premises'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'PROPERTY_INSURANCE',           'Property/Office Insurance',              true, false, true,  'Insurance covering company premises and assets'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'SOFTWARE_LICENSE',             'Software License Agreement',             true, false, true,  'Licensing agreement for IT infrastructure and tools'),
-- VEHICLE LEGAL DOCUMENTS
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'VEHICLE_REGISTRATION',         'Vehicle Registration',                   true, true,  true,  'Official government registration document of vehicle'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'VEHICLE_INSURANCE',            'Vehicle Insurance',                      true, true,  true,  'Insurance policy covering vehicle damage and liability'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'VEHICLE_FITNESS_CERTIFICATE',  'Fitness Certificate',                    true, true,  true,  'Roadworthiness and safety compliance certificate'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'ROAD_PERMIT',                  'Road Permit',                            true, false, true,  'Permit to operate vehicle on public roads and inter-state travel'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'VEHICLE_TAX_TOKEN',            'Tax Token',                              true, false, true,  'Proof of vehicle tax payment'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'EMISSION_CERTIFICATE',         'Emission/PUC Certificate',               true, false, true,  'Environmental emission compliance certificate'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'CHASSIS_VERIFICATION',         'Chassis Verification',                   true, false, false, 'Verification of vehicle chassis authenticity'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'VEHICLE_TITLE_DEED',           'Vehicle Title Deed',                     true, false, false, 'Official proof of ownership for the vehicle'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'VEHICLE_LEASE_AGREEMENT',      'Vehicle Lease Agreement',                true, false, true,  'Contract for leased vehicles indicating terms and validity'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'CUSTOMS_CLEARANCE',            'Customs Clearance',                      true, false, true,  'Required clearance documentation for cross-border transit'),
-- DRIVER DOCUMENTS
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'DRIVER_LICENSE',               'Driver License',                         true, true,  true,  'Valid commercial driving license issued by authority'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'DRIVER_ID_CARD',               'Driver ID Card',                         true, true,  false, 'Government issued national identification card or passport'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'DRIVER_MEDICAL_CERTIFICATE',   'Medical Certificate',                    true, false, true,  'Medical and vision fitness certificate for driver'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'DRIVER_POLICE_VERIFICATION',   'Police Verification',                    true, false, true,  'Background check and clearance from law enforcement'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'DRIVER_TRAINING_CERTIFICATE',  'Training Certificate',                   true, false, false, 'Professional driving and safety training certification'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'DRIVER_DRUG_TEST',             'Drug & Alcohol Test Report',             true, false, true,  'Mandatory periodic substance abuse testing results'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'DRIVER_HAZMAT_ENDORSEMENT',    'HAZMAT Endorsement',                     true, false, true,  'Special certification for transporting hazardous materials'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'DRIVER_EMPLOYMENT_CONTRACT',   'Driver Employment Contract',             true, false, false, 'Signed employment agreement with the logistics company'),
-- VEHICLE MAINTENANCE DOCUMENTS
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'VEHICLE_INSPECTION_REPORT',    'Inspection Report',                      true, false, true,  'Periodic DOT or internal vehicle inspection report'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'MAINTENANCE_INVOICE',          'Maintenance Invoice',                    true, false, false, 'Invoice for vehicle repair or routine service'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'BREAKDOWN_REPORT',             'Breakdown Report',                       true, false, false, 'Incident report detailing vehicle breakdown events'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'PREVENTATIVE_MAINTENANCE_LOG', 'Preventative Maint. Log',                true, false, false, 'Log sheet of routine oil changes, tire rotations, etc.'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'PARTS_WARRANTY_CERT',          'Parts Warranty Certificate',             true, false, true,  'Warranty documentation for newly installed major parts'),
-- LOGISTICS FINANCIAL & TRIP DOCUMENTS
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'FUEL_INVOICE',                 'Fuel Invoice',                           true, false, false, 'Receipt of fuel purchase during a trip'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'TOLL_RECEIPT',                 'Toll Receipt',                           true, false, false, 'Road, bridge, or tunnel toll payment receipt'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'PARKING_RECEIPT',              'Parking Receipt',                        true, false, false, 'Parking fee receipt for overnight or secure parking'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'WEIGH_STATION_RECEIPT',        'Weigh Station Ticket',                   true, false, false, 'Certified scale ticket proving legal axle weights'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'TRIP_EXPENSE_CLAIM',           'Trip Expense Claim Form',                true, false, false, 'Reimbursement claim form for out-of-pocket trip expenses'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'FINE_PENALTY_RECEIPT',         'Fine/Penalty Receipt',                   true, false, false, 'Receipt for traffic violations or compliance fines paid'),
-- MOVING / RELOCATION DOCUMENTS
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'MOVE_JOB_CONTRACT',            'Move Job Contract',                      true, true,  false, 'Contract between company and customer for relocation'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'GOODS_MANIFEST',               'Goods Manifest / Inventory',             true, true,  false, 'Detailed list of items, boxes, and furniture being moved'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'BILL_OF_LADING',               'Bill of Lading (BOL)',                   true, true,  false, 'Legally binding document detailing the type, quantity, and destination of goods'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'DELIVERY_CONFIRMATION',        'Proof of Delivery (POD)',                true, true,  false, 'Signed proof of successful delivery by the customer'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'DAMAGED_ITEM_REPORT',          'Damage/Loss Report',                     true, false, false, 'Report of damaged or missing items during relocation'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'VALUABLES_DECLARATION',        'High-Value Declaration',                 true, false, false, 'Special inventory form declaring high-value items for insurance'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, 'CUSTOMER_FEEDBACK_FORM',       'Customer Feedback Form',                 true, false, false, 'Post-move survey and quality of service sign-off')
ON CONFLICT (code) DO NOTHING;

-- =============================================================================
-- SECTION 8: VEHICLE MAKES (from V2)
-- =============================================================================
INSERT INTO vehicle_makes (id, version, created_at, created_by, updated_at, updated_by, deleted, deleted_at, deleted_by, code, name, active, country)
VALUES
-- JAPAN
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'TOYOTA',          'Toyota',          true, 'Japan'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'HINO',            'Hino',            true, 'Japan'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'ISUZU',           'Isuzu',           true, 'Japan'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'MITSUBISHI_FUSO', 'Mitsubishi Fuso', true, 'Japan'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'NISSAN_DIESEL',   'Nissan Diesel',   true, 'Japan'),
-- EUROPE
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'MERCEDES_BENZ',   'Mercedes-Benz',   true, 'Germany'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'VOLVO',           'Volvo Trucks',    true, 'Sweden'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'SCANIA',          'Scania',          true, 'Sweden'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'MAN',             'MAN Truck & Bus', true, 'Germany'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'IVECO',           'Iveco',           true, 'Italy'),
-- USA
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'FORD',            'Ford',            true, 'USA'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'CHEVROLET',       'Chevrolet',       true, 'USA'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'FREIGHTLINER',    'Freightliner',    true, 'USA'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'PETERBILT',       'Peterbilt',       true, 'USA'),
-- CHINA
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'FOTON',           'Foton',           true, 'China'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'FAW',             'FAW Group',       true, 'China'),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL, 'DONGFENG',        'Dongfeng',        true, 'China')
ON CONFLICT (code) DO NOTHING;

-- =============================================================================
-- SECTION 9: VEHICLE MODELS (from V6)
-- =============================================================================
INSERT INTO vehicle_models (id, version, created_at, created_by, updated_at, updated_by, deleted, make_id, vehicle_type_id, code, name, active, capacity_kg, capacity_m3, length_m, width_m, height_m)
VALUES
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    (SELECT id FROM vehicle_makes WHERE code = 'TOYOTA' LIMIT 1),
    (SELECT id FROM vehicle_types WHERE code = 'PICKUP' LIMIT 1),
    'TOYOTA_HILUX', 'Toyota Hilux', true, 1000.00, 2.50, 5.30, 1.85, 1.80
),
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    (SELECT id FROM vehicle_makes WHERE code = 'FORD' LIMIT 1),
    (SELECT id FROM vehicle_types WHERE code = 'VAN' LIMIT 1),
    'FORD_TRANSIT', 'Ford Transit', true, 1500.00, 6.00, 5.90, 2.00, 2.50
),
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    (SELECT id FROM vehicle_makes WHERE code = 'HINO' LIMIT 1),
    (SELECT id FROM vehicle_types WHERE code = 'MINI_TRUCK' LIMIT 1),
    'HINO_300', 'Hino 300 Series', true, 3500.00, 12.00, 6.10, 2.10, 2.20
),
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    (SELECT id FROM vehicle_makes WHERE code = 'MERCEDES_BENZ' LIMIT 1),
    (SELECT id FROM vehicle_types WHERE code = 'TRUCK' LIMIT 1),
    'MERCEDES_ACTROS', 'Mercedes-Benz Actros', true, 18000.00, 45.00, 12.00, 2.50, 4.00
),
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    (SELECT id FROM vehicle_makes WHERE code = 'VOLVO' LIMIT 1),
    (SELECT id FROM vehicle_types WHERE code = 'HEAVY_TRUCK' LIMIT 1),
    'VOLVO_FH16', 'Volvo FH16', true, 25000.00, 60.00, 16.50, 2.60, 4.20
)
ON CONFLICT (code) DO NOTHING;

-- =============================================================================
-- SECTION 10: VEHICLES (from V7)
-- =============================================================================
INSERT INTO vehicles (id, version, created_at, created_by, updated_at, updated_by, deleted, vehicle_code, registration_no, vehicle_model_id, manufacture_year, ownership_type, status, current_odometer_km, insurance_expiry_date, fitness_expiry_date, acquisition_date, active, remarks)
VALUES
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    'V-101', 'REG-TOYOTA-01',
    (SELECT id FROM vehicle_models WHERE code = 'TOYOTA_HILUX' LIMIT 1),
    2023, 'COMPANY', 'AVAILABLE', 12500.50, '2027-06-01', '2027-06-01', '2023-05-15', true,
    'Standard company utility pickup'
),
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    'V-102', 'REG-FORD-02',
    (SELECT id FROM vehicle_models WHERE code = 'FORD_TRANSIT' LIMIT 1),
    2022, 'COMPANY', 'AVAILABLE', 35400.00, '2027-04-10', '2027-04-10', '2022-07-20', true,
    'Cargo delivery van'
),
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    'V-103', 'REG-HINO-03',
    (SELECT id FROM vehicle_models WHERE code = 'HINO_300' LIMIT 1),
    2021, 'COMPANY', 'AVAILABLE', 58000.75, '2027-08-15', '2027-08-15', '2021-10-12', true,
    'Light distribution truck'
),
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    'V-104', 'REG-MERC-04',
    (SELECT id FROM vehicle_models WHERE code = 'MERCEDES_ACTROS' LIMIT 1),
    2024, 'RENTAL', 'IN_TRANSIT', 5600.00, '2027-12-31', '2027-12-31', '2024-02-01', true,
    'Long haul heavy relocation truck'
),
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    'V-105', 'REG-VOLVO-05',
    (SELECT id FROM vehicle_models WHERE code = 'VOLVO_FH16' LIMIT 1),
    2023, 'CONTRACTOR', 'MAINTENANCE', 42000.20, '2027-05-22', '2027-05-22', '2023-09-18', true,
    'Contractor-operated heavy relocation truck'
)
ON CONFLICT (vehicle_code) DO NOTHING;

-- =============================================================================
-- SECTION 11: MODULES (from V9)
-- =============================================================================
INSERT INTO modules (code, name) VALUES
('DASHBOARD',     'Dashboard & Analytics'),
('CRM',           'Customer Relationship Management'),
('OPERATIONS',    'Operations & Move Management'),
('SERVICES',      'Service Catalog & Pricing'),
('RESOURCES',     'Resource Management (Fleet, Crew, Warehouse, Assets, Inventory, Vendors)'),
('PROCUREMENT',   'Procurement & Vendor Management'),
('FINANCE',       'Finance & Accounting'),
('HR',            'Human Resource Management'),
('SUPPORT',       'Customer Support & Tickets'),
('REPORTS',       'Reports & Analytics'),
('ADMIN',         'Administration & System Settings'),
('ESTIMATION',    'Quick Estimation & Tools'),
('COMMUNICATION', 'Notifications & Messaging')
ON CONFLICT (code) DO UPDATE SET name = EXCLUDED.name;

-- =============================================================================
-- SECTION 12: RBAC RESOURCES (from V11)
-- =============================================================================
INSERT INTO sec_resources (code, name, module_id, created_at) VALUES
('DASHBOARD',              'Dashboard Overview',           (SELECT id FROM modules WHERE code = 'DASHBOARD'),     CURRENT_TIMESTAMP),
('CRM_CUSTOMERS',          'Customers Management',         (SELECT id FROM modules WHERE code = 'CRM'),           CURRENT_TIMESTAMP),
('CRM_LEADS',              'Leads Management',             (SELECT id FROM modules WHERE code = 'CRM'),           CURRENT_TIMESTAMP),
('CRM_PIPELINES',          'Pipelines Management',         (SELECT id FROM modules WHERE code = 'CRM'),           CURRENT_TIMESTAMP),
('OPERATIONS_MOVES',       'Moves Management',             (SELECT id FROM modules WHERE code = 'OPERATIONS'),    CURRENT_TIMESTAMP),
('OPERATIONS_TASKS',       'Tasks Management',             (SELECT id FROM modules WHERE code = 'OPERATIONS'),    CURRENT_TIMESTAMP),
('OPERATIONS_SCHEDULES',   'Schedules Management',         (SELECT id FROM modules WHERE code = 'OPERATIONS'),    CURRENT_TIMESTAMP),
('SERVICES_CATALOG',       'Service Catalog',              (SELECT id FROM modules WHERE code = 'SERVICES'),      CURRENT_TIMESTAMP),
('SERVICES_PRICING',       'Pricing Management',           (SELECT id FROM modules WHERE code = 'SERVICES'),      CURRENT_TIMESTAMP),
('RESOURCES_FLEET',        'Fleet Management',             (SELECT id FROM modules WHERE code = 'RESOURCES'),     CURRENT_TIMESTAMP),
('RESOURCES_CREW',         'Crew Management',              (SELECT id FROM modules WHERE code = 'RESOURCES'),     CURRENT_TIMESTAMP),
('RESOURCES_WAREHOUSE',    'Warehouse Management',         (SELECT id FROM modules WHERE code = 'RESOURCES'),     CURRENT_TIMESTAMP),
('RESOURCES_ASSETS',       'Assets Management',            (SELECT id FROM modules WHERE code = 'RESOURCES'),     CURRENT_TIMESTAMP),
('RESOURCES_VENDORS',      'Vendors Management',           (SELECT id FROM modules WHERE code = 'RESOURCES'),     CURRENT_TIMESTAMP),
('PROCUREMENT_ORDERS',     'Procurement Orders',           (SELECT id FROM modules WHERE code = 'PROCUREMENT'),   CURRENT_TIMESTAMP),
('PROCUREMENT_VENDORS',    'Procurement Vendors',          (SELECT id FROM modules WHERE code = 'PROCUREMENT'),   CURRENT_TIMESTAMP),
('FINANCE_INVOICES',       'Invoices Management',          (SELECT id FROM modules WHERE code = 'FINANCE'),       CURRENT_TIMESTAMP),
('FINANCE_PAYMENTS',       'Payments Management',          (SELECT id FROM modules WHERE code = 'FINANCE'),       CURRENT_TIMESTAMP),
('FINANCE_EXPENSES',       'Expenses Management',          (SELECT id FROM modules WHERE code = 'FINANCE'),       CURRENT_TIMESTAMP),
('HR_EMPLOYEES',           'Employees Management',         (SELECT id FROM modules WHERE code = 'HR'),            CURRENT_TIMESTAMP),
('HR_PAYROLL',             'Payroll Management',           (SELECT id FROM modules WHERE code = 'HR'),            CURRENT_TIMESTAMP),
('SUPPORT_TICKETS',        'Support Tickets',              (SELECT id FROM modules WHERE code = 'SUPPORT'),       CURRENT_TIMESTAMP),
('REPORTS_ANALYTICS',      'Analytics Reports',            (SELECT id FROM modules WHERE code = 'REPORTS'),       CURRENT_TIMESTAMP),
('ADMIN_USERS',            'Users Administration',         (SELECT id FROM modules WHERE code = 'ADMIN'),         CURRENT_TIMESTAMP),
('ADMIN_ROLES',            'Roles Administration',         (SELECT id FROM modules WHERE code = 'ADMIN'),         CURRENT_TIMESTAMP),
('ADMIN_SETTINGS',         'System Settings',              (SELECT id FROM modules WHERE code = 'ADMIN'),         CURRENT_TIMESTAMP),
('ESTIMATION_QUOTES',      'Quotes Estimation',            (SELECT id FROM modules WHERE code = 'ESTIMATION'),    CURRENT_TIMESTAMP),
('COMMUNICATION_MESSAGES', 'Messages & Notifications',     (SELECT id FROM modules WHERE code = 'COMMUNICATION'), CURRENT_TIMESTAMP)
ON CONFLICT (code) DO UPDATE SET name = EXCLUDED.name, module_id = EXCLUDED.module_id;

-- =============================================================================
-- SEQUENCE RESET
-- =============================================================================
SELECT setval('sec_menu_items_id_seq', 10000);
