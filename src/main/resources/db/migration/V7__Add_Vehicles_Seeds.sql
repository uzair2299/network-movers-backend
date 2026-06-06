-- Migration to seed 5 vehicles dynamically referencing vehicle models
INSERT INTO vehicles (
    id, version, created_at, created_by, updated_at, updated_by, deleted,
    vehicle_code, registration_no, vehicle_model_id, manufacture_year, ownership_type, status,
    current_odometer_km, insurance_expiry_date, fitness_expiry_date, acquisition_date, active, remarks
) VALUES
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    'V-101', 'REG-TOYOTA-01', (SELECT id FROM vehicle_models WHERE code = 'TOYOTA_HILUX' LIMIT 1),
    2023, 'COMPANY', 'AVAILABLE', 12500.50, '2027-06-01', '2027-06-01', '2023-05-15', true, 'Standard company utility pickup'
),
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    'V-102', 'REG-FORD-02', (SELECT id FROM vehicle_models WHERE code = 'FORD_TRANSIT' LIMIT 1),
    2022, 'COMPANY', 'AVAILABLE', 35400.00, '2027-04-10', '2027-04-10', '2022-07-20', true, 'Cargo delivery van'
),
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    'V-103', 'REG-HINO-03', (SELECT id FROM vehicle_models WHERE code = 'HINO_300' LIMIT 1),
    2021, 'COMPANY', 'AVAILABLE', 58000.75, '2027-08-15', '2027-08-15', '2021-10-12', true, 'Light distribution truck'
),
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    'V-104', 'REG-MERC-04', (SELECT id FROM vehicle_models WHERE code = 'MERCEDES_ACTROS' LIMIT 1),
    2024, 'RENTAL', 'IN_TRANSIT', 5600.00, '2027-12-31', '2027-12-31', '2024-02-01', true, 'Long haul heavy relocation truck'
),
(
    gen_random_uuid(), 0, NOW(), 1, NOW(), 1, false,
    'V-105', 'REG-VOLVO-05', (SELECT id FROM vehicle_models WHERE code = 'VOLVO_FH16' LIMIT 1),
    2023, 'CONTRACTOR', 'MAINTENANCE', 42000.20, '2027-05-22', '2027-05-22', '2023-09-18', true, 'Contractor-operated heavy relocation truck'
);
