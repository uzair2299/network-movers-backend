INSERT INTO property_category (id, name, code, active, created_at)
VALUES
(gen_random_uuid(), 'Residential', 'RESIDENTIAL', true, now()),
(gen_random_uuid(), 'Commercial', 'COMMERCIAL', true, now()),
(gen_random_uuid(), 'Industrial', 'INDUSTRIAL', true, now()),
(gen_random_uuid(), 'Storage', 'STORAGE', true, now());



INSERT INTO occupancy_type (id, name, code, active, created_at)
VALUES
(gen_random_uuid(), 'Owner Occupied', 'OWNER_OCCUPIED', true, now()),
(gen_random_uuid(), 'Tenant Occupied', 'TENANT_OCCUPIED', true, now()),
(gen_random_uuid(), 'Vacant Unit', 'VACANT', true, now()),
(gen_random_uuid(), 'Under Renovation', 'UNDER_RENOVATION', true, now()),
(gen_random_uuid(), 'Moving Out in Progress', 'MOVING_OUT', true, now()),
(gen_random_uuid(), 'New Move-In', 'NEW_MOVE_IN', true, now()),
(gen_random_uuid(), 'Short-Term Rental (Airbnb)', 'SHORT_TERM_RENTAL', true, now());



INSERT INTO building_access_type (id, name, code, active, created_at)
VALUES
(gen_random_uuid(), 'Dedicated Passenger Elevator', 'PASSENGER_ELEVATOR', true, now()),
(gen_random_uuid(), 'Service Elevator Available', 'SERVICE_ELEVATOR', true, now()),
(gen_random_uuid(), 'Freight Elevator (Large Capacity)', 'FREIGHT_ELEVATOR', true, now()),
(gen_random_uuid(), 'No Elevator - Stair Access Only', 'STAIRS_ONLY', true, now()),
(gen_random_uuid(), 'Shared Elevator (With Booking)', 'SHARED_ELEVATOR_BOOKING', true, now()),
(gen_random_uuid(), 'Restricted Time Elevator Access', 'TIME_RESTRICTED_ELEVATOR', true, now()),
(gen_random_uuid(), 'Cargo Lift Available (Industrial)', 'CARGO_LIFT', true, now()),
(gen_random_uuid(), 'High-Rise Tower with Multiple Elevators', 'MULTI_ELEVATOR_TOWER', true, now());


INSERT INTO parking_access_type (id, name, code, active, created_at)
VALUES
(gen_random_uuid(), 'Direct Building Loading Zone', 'DIRECT_LOADING_ZONE', true, now()),
(gen_random_uuid(), 'Basement Parking Access (B1/B2)', 'BASEMENT_PARKING', true, now()),
(gen_random_uuid(), 'Dedicated Loading Bay', 'LOADING_BAY', true, now()),
(gen_random_uuid(), 'Roadside Temporary Parking', 'ROADSIDE', true, now()),
(gen_random_uuid(), 'Outdoor Open Parking Area', 'OUTDOOR_PARKING', true, now()),
(gen_random_uuid(), 'Far Parking (50–100 meters)', 'FAR_PARKING', true, now()),
(gen_random_uuid(), 'Very Far Parking (100m+)', 'VERY_FAR_PARKING', true, now()),
(gen_random_uuid(), 'Parking Permit Required (Community Controlled)', 'PERMIT_REQUIRED', true, now()),
(gen_random_uuid(), 'Underground Tight Parking (Low Clearance)', 'TIGHT_BASEMENT', true, now()),
(gen_random_uuid(), 'Mall / Shared Commercial Parking', 'MALL_PARKING', true, now());




INSERT INTO floor_type (id, name, code, active, created_at)
VALUES
(gen_random_uuid(), 'Ground Floor', 'GROUND', true, now()),
(gen_random_uuid(), 'Mezzanine Level', 'MEZZANINE', true, now()),
(gen_random_uuid(), '1st Floor', 'FLOOR_1', true, now()),
(gen_random_uuid(), '2nd Floor', 'FLOOR_2', true, now()),
(gen_random_uuid(), '3rd Floor', 'FLOOR_3', true, now()),
(gen_random_uuid(), '4th Floor', 'FLOOR_4', true, now()),
(gen_random_uuid(), '5th Floor', 'FLOOR_5', true, now()),
(gen_random_uuid(), '6–10 Floors (Mid Rise)', 'MID_RISE', true, now()),
(gen_random_uuid(), '11–20 Floors (High Rise)', 'HIGH_RISE', true, now()),
(gen_random_uuid(), '21–40 Floors (Very High Rise)', 'VERY_HIGH_RISE', true, now()),
(gen_random_uuid(), '40+ Floors (Ultra High Rise Dubai Towers)', 'ULTRA_HIGH_RISE', true, now()),
(gen_random_uuid(), 'Penthouse Level', 'PENTHOUSE', true, now());



INSERT INTO access_restriction_type (id, name, code, active, created_at)
VALUES
(gen_random_uuid(), 'No Restriction', 'NONE', true, now()),
(gen_random_uuid(), 'Security Gate Approval Required', 'SECURITY_APPROVAL', true, now()),
(gen_random_uuid(), 'Move-In Permit Required (Community Management)', 'MOVE_IN_PERMIT', true, now()),
(gen_random_uuid(), 'Move-Out Permit Required', 'MOVE_OUT_PERMIT', true, now()),
(gen_random_uuid(), 'Time Window Restricted (9AM–5PM only)', 'TIME_WINDOW', true, now()),
(gen_random_uuid(), 'Elevator Booking Mandatory', 'ELEVATOR_BOOKING', true, now()),
(gen_random_uuid(), 'Key/Card Access Required', 'KEY_CARD', true, now()),
(gen_random_uuid(), 'Owner Presence Required', 'OWNER_PRESENT', true, now()),
(gen_random_uuid(), 'Security Escort Required', 'SECURITY_ESCORT', true, now()),
(gen_random_uuid(), 'Community Management Approval (Emaar/Nakheel/Damac)', 'COMMUNITY_APPROVAL', true, now()),
(gen_random_uuid(), 'Weekend Moving Restriction', 'WEEKEND_RESTRICTION', true, now());




INSERT INTO property_type (id, category_id, name, code, active, created_at)
VALUES
-- =========================
-- RESIDENTIAL
-- =========================
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='RESIDENTIAL'), 'Apartment', 'RES_APARTMENT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='RESIDENTIAL'), 'Villa', 'RES_VILLA', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='RESIDENTIAL'), 'Townhouse', 'RES_TOWNHOUSE', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='RESIDENTIAL'), 'Compound Villa', 'RES_COMPOUND_VILLA', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='RESIDENTIAL'), 'Penthouse', 'RES_PENTHOUSE', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='RESIDENTIAL'), 'Studio Unit', 'RES_STUDIO', true, now()),

-- =========================
-- COMMERCIAL
-- =========================
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='COMMERCIAL'), 'Office', 'COM_OFFICE', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='COMMERCIAL'), 'Retail Shop', 'COM_RETAIL_SHOP', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='COMMERCIAL'), 'Restaurant / Cafe', 'COM_RESTAURANT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='COMMERCIAL'), 'Clinic / Medical Center', 'COM_CLINIC', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='COMMERCIAL'), 'Salon / Spa', 'COM_SALON', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='COMMERCIAL'), 'Showroom', 'COM_SHOWROOM', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='COMMERCIAL'), 'Warehouse (Commercial Use)', 'COM_WAREHOUSE', true, now()),

-- =========================
-- INDUSTRIAL
-- =========================
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='INDUSTRIAL'), 'Factory', 'IND_FACTORY', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='INDUSTRIAL'), 'Workshop', 'IND_WORKSHOP', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='INDUSTRIAL'), 'Industrial Yard', 'IND_YARD', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='INDUSTRIAL'), 'Production Unit', 'IND_PRODUCTION', true, now()),

-- =========================
-- STORAGE
-- =========================
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='STORAGE'), 'Self Storage Unit', 'STO_SELF_UNIT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='STORAGE'), 'Warehouse Storage', 'STO_WAREHOUSE', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='STORAGE'), 'Container Storage', 'STO_CONTAINER', true, now()),
(gen_random_uuid(), (SELECT id FROM property_category WHERE code='STORAGE'), 'Cold Storage Unit', 'STO_COLD', true, now());




INSERT INTO property_size (id, type_id, name, code, unit_type, active, created_at)
VALUES

-- =========================
-- APARTMENTS
-- =========================
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_APARTMENT'), 'Studio', 'SIZE_STUDIO', 'UNIT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_APARTMENT'), '1 Bedroom (1BR)', 'SIZE_1BR', 'UNIT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_APARTMENT'), '2 Bedroom (2BR)', 'SIZE_2BR', 'UNIT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_APARTMENT'), '3 Bedroom (3BR)', 'SIZE_3BR', 'UNIT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_APARTMENT'), '4 Bedroom (4BR)', 'SIZE_4BR', 'UNIT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_APARTMENT'), '5+ Bedroom Luxury', 'SIZE_5BR_PLUS', 'UNIT', true, now()),

-- =========================
-- VILLAS
-- =========================
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_VILLA'), 'Small Villa (2–3 BR)', 'SIZE_SMALL_VILLA', 'UNIT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_VILLA'), 'Medium Villa (4–5 BR)', 'SIZE_MEDIUM_VILLA', 'UNIT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_VILLA'), 'Large Villa (6+ BR)', 'SIZE_LARGE_VILLA', 'UNIT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_VILLA'), 'Luxury Villa with Majlis', 'SIZE_LUXURY_VILLA', 'UNIT', true, now()),

-- =========================
-- TOWNHOUSES
-- =========================
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_TOWNHOUSE'), '2 Bedroom Townhouse', 'SIZE_TOWN_2BR', 'UNIT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_TOWNHOUSE'), '3 Bedroom Townhouse', 'SIZE_TOWN_3BR', 'UNIT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='RES_TOWNHOUSE'), '4 Bedroom Townhouse', 'SIZE_TOWN_4BR', 'UNIT', true, now()),

-- =========================
-- OFFICES
-- =========================
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='COM_OFFICE'), 'Small Office (1–5 desks)', 'SIZE_OFF_SMALL', 'DESK', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='COM_OFFICE'), 'Medium Office (6–20 desks)', 'SIZE_OFF_MEDIUM', 'DESK', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='COM_OFFICE'), 'Large Office (20–50 desks)', 'SIZE_OFF_LARGE', 'DESK', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='COM_OFFICE'), 'Corporate Office (50+ desks)', 'SIZE_OFF_CORP', 'DESK', true, now()),

-- =========================
-- RETAIL
-- =========================
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='COM_RETAIL_SHOP'), 'Small Shop', 'SIZE_SHOP_SMALL', 'SQFT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='COM_RETAIL_SHOP'), 'Medium Retail Store', 'SIZE_SHOP_MEDIUM', 'SQFT', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='COM_RETAIL_SHOP'), 'Large Showroom Retail', 'SIZE_SHOP_LARGE', 'SQFT', true, now()),

-- =========================
-- WAREHOUSE
-- =========================
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='COM_WAREHOUSE'), 'Small Warehouse', 'SIZE_WH_SMALL', 'CBM', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='COM_WAREHOUSE'), 'Medium Warehouse', 'SIZE_WH_MEDIUM', 'CBM', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='COM_WAREHOUSE'), 'Large Warehouse', 'SIZE_WH_LARGE', 'CBM', true, now()),

-- =========================
-- STORAGE
-- =========================
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='STO_SELF_UNIT'), 'Locker Size', 'SIZE_STORAGE_LOCKER', 'CBM', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='STO_SELF_UNIT'), 'Small Unit', 'SIZE_STORAGE_SMALL', 'CBM', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='STO_SELF_UNIT'), 'Medium Unit', 'SIZE_STORAGE_MEDIUM', 'CBM', true, now()),
(gen_random_uuid(), (SELECT id FROM property_type WHERE code='STO_SELF_UNIT'), 'Large Unit', 'SIZE_STORAGE_LARGE', 'CBM', true, now());