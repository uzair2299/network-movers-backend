-- Seed Vehicle Models (combining makes and types)
INSERT INTO vehicle_models (
    id, version, created_at, created_by, updated_at, updated_by, deleted, deleted_at, deleted_by,
    make_id, vehicle_type_id, code, name
) VALUES
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
    (SELECT id FROM vehicle_makes WHERE code = 'TOYOTA'), 
    (SELECT id FROM vehicle_types WHERE code = 'PICKUP'),
    'HILUX', 'Toyota Hilux'
),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
    (SELECT id FROM vehicle_makes WHERE code = 'TOYOTA'), 
    (SELECT id FROM vehicle_types WHERE code = 'VAN'),
    'HIACE', 'Toyota Hiace'
),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
    (SELECT id FROM vehicle_makes WHERE code = 'ISUZU'), 
    (SELECT id FROM vehicle_types WHERE code = 'TRUCK'),
    'NPR', 'Isuzu NPR Box'
),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
    (SELECT id FROM vehicle_makes WHERE code = 'MITSUBISHI_FUSO'), 
    (SELECT id FROM vehicle_types WHERE code = 'TRUCK'),
    'CANTER', 'Fuso Canter'
),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
    (SELECT id FROM vehicle_makes WHERE code = 'VOLVO'), 
    (SELECT id FROM vehicle_types WHERE code = 'HEAVY_TRUCK'),
    'FH16', 'Volvo FH16 Tractor'
),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
    (SELECT id FROM vehicle_makes WHERE code = 'MERCEDES_BENZ'), 
    (SELECT id FROM vehicle_types WHERE code = 'HEAVY_TRUCK'),
    'ACTROS', 'Mercedes Actros Flatbed'
);


-- Seed Vehicles (dynamic foreign keys via subquery)
INSERT INTO vehicles (
    id, version, created_at, created_by, updated_at, updated_by, deleted, deleted_at, deleted_by,
    vehicle_code, registration_no, vehicle_model_id,
    manufacture_year, ownership_type, status,
    current_odometer_km, insurance_expiry_date, fitness_expiry_date, acquisition_date,
    active, remarks
) VALUES 
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'V-1001', 'DXB-12345', 
 (SELECT id FROM vehicle_models WHERE code = 'HILUX'), 
 2022, 'OWNED', 'AVAILABLE', 
 15000.50, CURRENT_DATE + INTERVAL '6 months', CURRENT_DATE + INTERVAL '1 year', CURRENT_DATE - INTERVAL '2 years', 
 true, 'Used for small scale city moves'
),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'V-1002', 'AUH-67890', 
 (SELECT id FROM vehicle_models WHERE code = 'HIACE'), 
 2023, 'OWNED', 'IN_USE', 
 8500.00, CURRENT_DATE + INTERVAL '8 months', CURRENT_DATE + INTERVAL '8 months', CURRENT_DATE - INTERVAL '1 year', 
 true, 'Delivery cargo van'
),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'V-1003', 'SHJ-54321', 
 (SELECT id FROM vehicle_models WHERE code = 'NPR'), 
 2021, 'LEASED', 'AVAILABLE', 
 45000.00, CURRENT_DATE + INTERVAL '3 months', CURRENT_DATE + INTERVAL '6 months', CURRENT_DATE - INTERVAL '3 years', 
 true, 'Box truck for standard residential moves'
),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'V-1004', 'AJM-98765', 
 (SELECT id FROM vehicle_models WHERE code = 'CANTER'), 
 2020, 'OWNED', 'MAINTENANCE', 
 80000.00, CURRENT_DATE + INTERVAL '1 month', CURRENT_DATE + INTERVAL '2 months', CURRENT_DATE - INTERVAL '4 years', 
 true, 'Temperature controlled moves'
),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'V-1005', 'DXB-55555', 
 (SELECT id FROM vehicle_models WHERE code = 'FH16'), 
 2023, 'OWNED', 'AVAILABLE', 
 25000.00, CURRENT_DATE + INTERVAL '11 months', CURRENT_DATE + INTERVAL '1 year', CURRENT_DATE - INTERVAL '6 months', 
 true, 'Heavy duty trailer tractor for industrial logistics'
),
(gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false, NULL, NULL,
 'V-1006', 'AUH-11111', 
 (SELECT id FROM vehicle_models WHERE code = 'ACTROS'), 
 2019, 'LEASED', 'OUT_OF_SERVICE', 
 120000.00, CURRENT_DATE - INTERVAL '1 month', CURRENT_DATE - INTERVAL '2 months', CURRENT_DATE - INTERVAL '5 years', 
 false, 'Pending lease return'
);
