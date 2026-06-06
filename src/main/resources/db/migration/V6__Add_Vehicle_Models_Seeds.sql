-- Migration to seed 5 vehicle models dynamically referencing vehicle makes and vehicle types
INSERT INTO vehicle_models (
    id, version, created_at, created_by, updated_at, updated_by, deleted,
    make_id, vehicle_type_id, code, name, active, capacity_kg, capacity_m3, length_m, width_m, height_m
) VALUES
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
);
