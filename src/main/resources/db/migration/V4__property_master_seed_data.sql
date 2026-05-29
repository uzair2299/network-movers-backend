-- =============================================================================
-- V4__property_master_seed_data.sql
-- Property Master Module Seed Data with Static Deterministic UUIDs
-- =============================================================================

INSERT INTO property_category (id, name, code, active, created_at)
VALUES
('8c0a8270-e4b7-4d92-a1b6-d2ef6eb793ff', 'Residential', 'RESIDENTIAL', true, now()),
('8c0a8270-e4b7-4d92-a1b6-d2ef6eb79302', 'Commercial', 'COMMERCIAL', true, now()),
('8c0a8270-e4b7-4d92-a1b6-d2ef6eb79303', 'Industrial', 'INDUSTRIAL', true, now()),
('8c0a8270-e4b7-4d92-a1b6-d2ef6eb79304', 'Storage', 'STORAGE', true, now());


INSERT INTO occupancy_type (id, name, code, active, created_at)
VALUES
('f00df00d-f00d-f00d-f00d-000000000001', 'Owner Occupied', 'OWNER_OCCUPIED', true, now()),
('f00df00d-f00d-f00d-f00d-000000000002', 'Tenant Occupied', 'TENANT_OCCUPIED', true, now()),
('f00df00d-f00d-f00d-f00d-000000000003', 'Vacant Unit', 'VACANT', true, now()),
('f00df00d-f00d-f00d-f00d-000000000004', 'Under Renovation', 'UNDER_RENOVATION', true, now()),
('f00df00d-f00d-f00d-f00d-000000000005', 'Moving Out in Progress', 'MOVING_OUT', true, now()),
('f00df00d-f00d-f00d-f00d-000000000006', 'New Move-In', 'NEW_MOVE_IN', true, now()),
('f00df00d-f00d-f00d-f00d-000000000007', 'Short-Term Rental (Airbnb)', 'SHORT_TERM_RENTAL', true, now());


INSERT INTO building_access_type (id, name, code, active, created_at)
VALUES
('27c1a84f-702b-42fa-908d-a417df6ec91f', 'Dedicated Passenger Elevator', 'PASSENGER_ELEVATOR', true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec902', 'Service Elevator Available', 'SERVICE_ELEVATOR', true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec903', 'Freight Elevator (Large Capacity)', 'FREIGHT_ELEVATOR', true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec904', 'No Elevator - Stair Access Only', 'STAIRS_ONLY', true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec905', 'Shared Elevator (With Booking)', 'SHARED_ELEVATOR_BOOKING', true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec906', 'Restricted Time Elevator Access', 'TIME_RESTRICTED_ELEVATOR', true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec907', 'Cargo Lift Available (Industrial)', 'CARGO_LIFT', true, now()),
('27c1a84f-702b-42fa-908d-a417df6ec908', 'High-Rise Tower with Multiple Elevators', 'MULTI_ELEVATOR_TOWER', true, now());


INSERT INTO parking_access_type (id, name, code, active, created_at)
VALUES
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1801', 'Direct Building Loading Zone', 'DIRECT_LOADING_ZONE', true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe189d', 'Basement Parking Access (B1/B2)', 'BASEMENT_PARKING', true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1803', 'Dedicated Loading Bay', 'LOADING_BAY', true, now()),
('a1eed4f8-df2a-4318-ab93-ce117fef29df', 'Roadside Temporary Parking', 'ROADSIDE', true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1805', 'Outdoor Open Parking Area', 'OUTDOOR_PARKING', true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1806', 'Far Parking (50–100 meters)', 'FAR_PARKING', true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1807', 'Very Far Parking (100m+)', 'VERY_FAR_PARKING', true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1808', 'Parking Permit Required (Community Controlled)', 'PERMIT_REQUIRED', true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1809', 'Underground Tight Parking (Low Clearance)', 'TIGHT_BASEMENT', true, now()),
('f5bbd3e6-bfd1-41f2-bf89-ce9123fe1810', 'Mall / Shared Commercial Parking', 'MALL_PARKING', true, now());


INSERT INTO floor_type (id, name, code, active, created_at)
VALUES
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b001', 'Ground Floor', 'GROUND', true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b002', 'Mezzanine Level', 'MEZZANINE', true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b01a', '1st Floor', 'FLOOR_1', true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b004', '2nd Floor', 'FLOOR_2', true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b005', '3rd Floor', 'FLOOR_3', true, now()),
('41c8fa57-226f-4cd3-a1bf-c2ab6ed8b01e', '4th Floor', 'FLOOR_4', true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b007', '5th Floor', 'FLOOR_5', true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b008', '6–10 Floors (Mid Rise)', 'MID_RISE', true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b009', '11–20 Floors (High Rise)', 'HIGH_RISE', true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b010', '21–40 Floors (Very High Rise)', 'VERY_HIGH_RISE', true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b011', '40+ Floors (Ultra High Rise Dubai Towers)', 'ULTRA_HIGH_RISE', true, now()),
('18f97a51-925f-4cd2-8fb1-e2cb6ed8b012', 'Penthouse Level', 'PENTHOUSE', true, now());


INSERT INTO access_restriction_type (id, name, code, active, created_at)
VALUES
('cda814bf-602c-4ef7-b28f-d6a13dfeb901', 'No Restriction', 'NONE', true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb9a5', 'Security Gate Approval Required', 'SECURITY_APPROVAL', true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb903', 'Move-In Permit Required (Community Management)', 'MOVE_IN_PERMIT', true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb904', 'Move-Out Permit Required', 'MOVE_OUT_PERMIT', true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb905', 'Time Window Restricted (9AM–5PM only)', 'TIME_WINDOW', true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb906', 'Elevator Booking Mandatory', 'ELEVATOR_BOOKING', true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb907', 'Key/Card Access Required', 'KEY_CARD', true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb908', 'Owner Presence Required', 'OWNER_PRESENT', true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb909', 'Security Escort Required', 'SECURITY_ESCORT', true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb910', 'Community Management Approval (Emaar/Nakheel/Damac)', 'COMMUNITY_APPROVAL', true, now()),
('cda814bf-602c-4ef7-b28f-d6a13dfeb911', 'Weekend Moving Restriction', 'WEEKEND_RESTRICTION', true, now());


INSERT INTO property_type (id, category_id, name, code, active, created_at)
VALUES
-- =========================
-- RESIDENTIAL
-- =========================
('76d8b671-55e1-4c60-a249-f0db66ea6f0e', (SELECT id FROM property_category WHERE code = 'RESIDENTIAL'), 'Apartment', 'RES_APARTMENT', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f02', (SELECT id FROM property_category WHERE code = 'RESIDENTIAL'), 'Villa', 'RES_VILLA', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f03', (SELECT id FROM property_category WHERE code = 'RESIDENTIAL'), 'Townhouse', 'RES_TOWNHOUSE', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f04', (SELECT id FROM property_category WHERE code = 'RESIDENTIAL'), 'Compound Villa', 'RES_COMPOUND_VILLA', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f05', (SELECT id FROM property_category WHERE code = 'RESIDENTIAL'), 'Penthouse', 'RES_PENTHOUSE', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f06', (SELECT id FROM property_category WHERE code = 'RESIDENTIAL'), 'Studio Unit', 'RES_STUDIO', true, now()),

-- =========================
-- COMMERCIAL
-- =========================
('76d8b671-55e1-4c60-a249-f0db66ea6f07', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Office', 'COM_OFFICE', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f08', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Retail Shop', 'COM_RETAIL_SHOP', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f09', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Restaurant / Cafe', 'COM_RESTAURANT', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f10', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Clinic / Medical Center', 'COM_CLINIC', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f11', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Salon / Spa', 'COM_SALON', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f12', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Showroom', 'COM_SHOWROOM', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f13', (SELECT id FROM property_category WHERE code = 'COMMERCIAL'), 'Warehouse (Commercial Use)', 'COM_WAREHOUSE', true, now()),

-- =========================
-- INDUSTRIAL
-- =========================
('76d8b671-55e1-4c60-a249-f0db66ea6f14', (SELECT id FROM property_category WHERE code = 'INDUSTRIAL'), 'Factory', 'IND_FACTORY', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f15', (SELECT id FROM property_category WHERE code = 'INDUSTRIAL'), 'Workshop', 'IND_WORKSHOP', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f16', (SELECT id FROM property_category WHERE code = 'INDUSTRIAL'), 'Industrial Yard', 'IND_YARD', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f17', (SELECT id FROM property_category WHERE code = 'INDUSTRIAL'), 'Production Unit', 'IND_PRODUCTION', true, now()),

-- =========================
-- STORAGE
-- =========================
('76d8b671-55e1-4c60-a249-f0db66ea6f18', (SELECT id FROM property_category WHERE code = 'STORAGE'), 'Self Storage Unit', 'STO_SELF_UNIT', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f19', (SELECT id FROM property_category WHERE code = 'STORAGE'), 'Warehouse Storage', 'STO_WAREHOUSE', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f20', (SELECT id FROM property_category WHERE code = 'STORAGE'), 'Container Storage', 'STO_CONTAINER', true, now()),
('76d8b671-55e1-4c60-a249-f0db66ea6f21', (SELECT id FROM property_category WHERE code = 'STORAGE'), 'Cold Storage Unit', 'STO_COLD', true, now());


INSERT INTO property_size (id, type_id, name, code, unit_type, active, created_at)
VALUES
-- =========================
-- APARTMENTS
-- =========================
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d01', (SELECT id FROM property_type WHERE code = 'RES_APARTMENT'), 'Studio', 'SIZE_STUDIO', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d05', (SELECT id FROM property_type WHERE code = 'RES_APARTMENT'), '1 Bedroom (1BR)', 'SIZE_1BR', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d03', (SELECT id FROM property_type WHERE code = 'RES_APARTMENT'), '2 Bedroom (2BR)', 'SIZE_2BR', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d04', (SELECT id FROM property_type WHERE code = 'RES_APARTMENT'), '3 Bedroom (3BR)', 'SIZE_3BR', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d06', (SELECT id FROM property_type WHERE code = 'RES_APARTMENT'), '4 Bedroom (4BR)', 'SIZE_4BR', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d07', (SELECT id FROM property_type WHERE code = 'RES_APARTMENT'), '5+ Bedroom Luxury', 'SIZE_5BR_PLUS', 'UNIT', true, now()),

-- =========================
-- VILLAS
-- =========================
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d08', (SELECT id FROM property_type WHERE code = 'RES_VILLA'), 'Small Villa (2–3 BR)', 'SIZE_SMALL_VILLA', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d09', (SELECT id FROM property_type WHERE code = 'RES_VILLA'), 'Medium Villa (4–5 BR)', 'SIZE_MEDIUM_VILLA', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d10', (SELECT id FROM property_type WHERE code = 'RES_VILLA'), 'Large Villa (6+ BR)', 'SIZE_LARGE_VILLA', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d11', (SELECT id FROM property_type WHERE code = 'RES_VILLA'), 'Luxury Villa with Majlis', 'SIZE_LUXURY_VILLA', 'UNIT', true, now()),

-- =========================
-- TOWNHOUSES
-- =========================
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d12', (SELECT id FROM property_type WHERE code = 'RES_TOWNHOUSE'), '2 Bedroom Townhouse', 'SIZE_TOWN_2BR', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d13', (SELECT id FROM property_type WHERE code = 'RES_TOWNHOUSE'), '3 Bedroom Townhouse', 'SIZE_TOWN_3BR', 'UNIT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d14', (SELECT id FROM property_type WHERE code = 'RES_TOWNHOUSE'), '4 Bedroom Townhouse', 'SIZE_TOWN_4BR', 'UNIT', true, now()),

-- =========================
-- OFFICES
-- =========================
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d15', (SELECT id FROM property_type WHERE code = 'COM_OFFICE'), 'Small Office (1–5 desks)', 'SIZE_OFF_SMALL', 'DESK', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d16', (SELECT id FROM property_type WHERE code = 'COM_OFFICE'), 'Medium Office (6–20 desks)', 'SIZE_OFF_MEDIUM', 'DESK', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d17', (SELECT id FROM property_type WHERE code = 'COM_OFFICE'), 'Large Office (20–50 desks)', 'SIZE_OFF_LARGE', 'DESK', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d18', (SELECT id FROM property_type WHERE code = 'COM_OFFICE'), 'Corporate Office (50+ desks)', 'SIZE_OFF_CORP', 'DESK', true, now()),

-- =========================
-- RETAIL
-- =========================
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d19', (SELECT id FROM property_type WHERE code = 'COM_RETAIL_SHOP'), 'Small Shop', 'SIZE_SHOP_SMALL', 'SQFT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d20', (SELECT id FROM property_type WHERE code = 'COM_RETAIL_SHOP'), 'Medium Retail Store', 'SIZE_SHOP_MEDIUM', 'SQFT', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d21', (SELECT id FROM property_type WHERE code = 'COM_RETAIL_SHOP'), 'Large Showroom Retail', 'SIZE_SHOP_LARGE', 'SQFT', true, now()),

-- =========================
-- WAREHOUSE
-- =========================
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d22', (SELECT id FROM property_type WHERE code = 'COM_WAREHOUSE'), 'Small Warehouse', 'SIZE_WH_SMALL', 'CBM', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d23', (SELECT id FROM property_type WHERE code = 'COM_WAREHOUSE'), 'Medium Warehouse', 'SIZE_WH_MEDIUM', 'CBM', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d24', (SELECT id FROM property_type WHERE code = 'COM_WAREHOUSE'), 'Large Warehouse', 'SIZE_WH_LARGE', 'CBM', true, now()),

-- =========================
-- STORAGE
-- =========================
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d25', (SELECT id FROM property_type WHERE code = 'STO_SELF_UNIT'), 'Locker Size', 'SIZE_STORAGE_LOCKER', 'CBM', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d26', (SELECT id FROM property_type WHERE code = 'STO_SELF_UNIT'), 'Small Unit', 'SIZE_STORAGE_SMALL', 'CBM', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d27', (SELECT id FROM property_type WHERE code = 'STO_SELF_UNIT'), 'Medium Unit', 'SIZE_STORAGE_MEDIUM', 'CBM', true, now()),
('bd05a769-cf7a-40a1-9a99-b1d6e1fa2d28', (SELECT id FROM property_type WHERE code = 'STO_SELF_UNIT'), 'Large Unit', 'SIZE_STORAGE_LARGE', 'CBM', true, now());