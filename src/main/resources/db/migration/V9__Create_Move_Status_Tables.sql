-- =============================================================================
-- V9__Create_Move_Status_Tables.sql
-- Create move_phase and move_status master tables and seed values
-- =============================================================================

CREATE TABLE IF NOT EXISTS move_phase (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    code        VARCHAR(50)     UNIQUE NOT NULL,
    name        VARCHAR(100)    NOT NULL,
    sequence_no INT             NOT NULL,
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);

CREATE TABLE IF NOT EXISTS move_status (
    id               UUID            PRIMARY KEY,
    version          BIGINT          NOT NULL DEFAULT 0,
    code             VARCHAR(100)    UNIQUE NOT NULL,
    name             VARCHAR(150)    NOT NULL,
    phase_id         UUID            NOT NULL,
    description      TEXT,
    sequence_no      INT,
    is_final         BOOLEAN         NOT NULL DEFAULT FALSE,
    active           BOOLEAN         NOT NULL DEFAULT TRUE,
    color_code       VARCHAR(20),
    customer_visible BOOLEAN         NOT NULL DEFAULT TRUE,
    internal_only    BOOLEAN         NOT NULL DEFAULT FALSE,
    created_at       TIMESTAMP       NOT NULL,
    created_by       BIGINT,
    updated_at       TIMESTAMP,
    updated_by       BIGINT,
    CONSTRAINT fk_phase FOREIGN KEY (phase_id) REFERENCES move_phase(id)
);

-- Seed Move Phases
INSERT INTO move_phase (id, version, code, name, sequence_no, active, created_at)
VALUES
('11111111-1111-1111-1111-111111111111', 0, 'REQUEST', 'Request Phase', 1, true, now()),
('22222222-2222-2222-2222-222222222222', 0, 'QUOTATION', 'Quotation Phase', 2, true, now()),
('33333333-3333-3333-3333-333333333333', 0, 'BOOKING', 'Booking Phase', 3, true, now()),
('44444444-4444-4444-4444-444444444444', 0, 'OPERATIONS', 'Operations Phase', 4, true, now()),
('55555555-5555-5555-5555-555555555555', 0, 'PICKUP', 'Pickup Phase', 5, true, now()),
('66666666-6666-6666-6666-666666666666', 0, 'TRANSIT', 'Transit Phase', 6, true, now()),
('77777777-7777-7777-7777-777777777777', 0, 'DELIVERY', 'Delivery Phase', 7, true, now()),
('88888888-8888-8888-8888-888888888888', 0, 'COMPLETION', 'Completion Phase', 8, true, now()),
('99999999-9999-9999-9999-999999999999', 0, 'EXCEPTION', 'Exception / Failure States', 9, true, now());

-- Seed Move Statuses
INSERT INTO move_status (id, version, code, name, phase_id, description, sequence_no, is_final, active, color_code, customer_visible, internal_only, created_at)
VALUES
-- 🟦 Request Phase (1)
(gen_random_uuid(), 0, 'REQUESTED', 'Customer submitted move request', '11111111-1111-1111-1111-111111111111', 'Customer submitted move request', 1, false, true, '#3B82F6', true, false, now()),
(gen_random_uuid(), 0, 'UNDER_REVIEW', 'Operations reviewing request', '11111111-1111-1111-1111-111111111111', 'Operations reviewing request', 2, false, true, '#3B82F6', true, false, now()),
(gen_random_uuid(), 0, 'AWAITING_DETAILS', 'Waiting for customer info/photos', '11111111-1111-1111-1111-111111111111', 'Waiting for customer info/photos', 3, false, true, '#3B82F6', true, false, now()),
(gen_random_uuid(), 0, 'SURVEY_SCHEDULED', 'Physical/virtual survey planned', '11111111-1111-1111-1111-111111111111', 'Physical/virtual survey planned', 4, false, true, '#3B82F6', true, false, now()),
(gen_random_uuid(), 0, 'SURVEY_COMPLETED', 'Survey completed', '11111111-1111-1111-1111-111111111111', 'Survey completed', 5, false, true, '#3B82F6', true, false, now()),

-- 💰 Quotation Phase (2)
(gen_random_uuid(), 0, 'QUOTATION_IN_PROGRESS', 'Pricing being prepared', '22222222-2222-2222-2222-222222222222', 'Pricing being prepared', 1, false, true, '#F59E0B', false, true, now()),
(gen_random_uuid(), 0, 'QUOTATION_SENT', 'Quote sent to customer', '22222222-2222-2222-2222-222222222222', 'Quote sent to customer', 2, false, true, '#F59E0B', true, false, now()),
(gen_random_uuid(), 0, 'QUOTATION_VIEWED', 'Customer viewed quotation', '22222222-2222-2222-2222-222222222222', 'Customer viewed quotation', 3, false, true, '#F59E0B', false, true, now()),
(gen_random_uuid(), 0, 'NEGOTIATION_IN_PROGRESS', 'Pricing negotiation ongoing', '22222222-2222-2222-2222-222222222222', 'Pricing negotiation ongoing', 4, false, true, '#F59E0B', true, false, now()),
(gen_random_uuid(), 0, 'QUOTATION_APPROVED', 'Customer approved quote', '22222222-2222-2222-2222-222222222222', 'Customer approved quote', 5, false, true, '#F59E0B', true, false, now()),
(gen_random_uuid(), 0, 'QUOTATION_REJECTED', 'Customer rejected quote', '22222222-2222-2222-2222-222222222222', 'Customer rejected quote', 6, false, true, '#F59E0B', true, false, now()),

-- ✅ Booking Phase (3)
(gen_random_uuid(), 0, 'CONFIRMED', 'Booking confirmed', '33333333-3333-3333-3333-333333333333', 'Booking confirmed', 1, false, true, '#10B981', true, false, now()),
(gen_random_uuid(), 0, 'PENDING_PAYMENT', 'Awaiting advance payment', '33333333-3333-3333-3333-333333333333', 'Awaiting advance payment', 2, false, true, '#10B981', true, false, now()),
(gen_random_uuid(), 0, 'PARTIALLY_PAID', 'Partial payment received', '33333333-3333-3333-3333-333333333333', 'Partial payment received', 3, false, true, '#10B981', true, false, now()),
(gen_random_uuid(), 0, 'PAID', 'Full payment received', '33333333-3333-3333-3333-333333333333', 'Full payment received', 4, false, true, '#10B981', true, false, now()),
(gen_random_uuid(), 0, 'INVOICE_GENERATED', 'Invoice created', '33333333-3333-3333-3333-333333333333', 'Invoice created', 5, false, true, '#10B981', true, false, now()),

-- 👷 Operations Phase (4)
(gen_random_uuid(), 0, 'ASSIGNMENT_PENDING', 'Waiting for mover assignment', '44444444-4444-4444-4444-444444444444', 'Waiting for mover assignment', 1, false, true, '#F97316', true, false, now()),
(gen_random_uuid(), 0, 'ASSIGNED', 'Team/driver assigned', '44444444-4444-4444-4444-444444444444', 'Team/driver assigned', 2, false, true, '#F97316', true, false, now()),
(gen_random_uuid(), 0, 'VEHICLE_ASSIGNED', 'Truck assigned', '44444444-4444-4444-4444-444444444444', 'Truck assigned', 3, false, true, '#F97316', false, true, now()),
(gen_random_uuid(), 0, 'CREW_ASSIGNED', 'Movers assigned', '44444444-4444-4444-4444-444444444444', 'Movers assigned', 4, false, true, '#F97316', false, true, now()),
(gen_random_uuid(), 0, 'SCHEDULED', 'Move officially scheduled', '44444444-4444-4444-4444-444444444444', 'Move officially scheduled', 5, false, true, '#F97316', true, false, now()),
(gen_random_uuid(), 0, 'RESCHEDULE_REQUESTED', 'Customer requested new date', '44444444-4444-4444-4444-444444444444', 'Customer requested new date', 6, false, true, '#F97316', true, false, now()),
(gen_random_uuid(), 0, 'RESCHEDULED', 'Move rescheduled', '44444444-4444-4444-4444-444444444444', 'Move rescheduled', 7, false, true, '#F97316', true, false, now()),

-- 📦 Pickup Phase (5)
(gen_random_uuid(), 0, 'TEAM_EN_ROUTE_PICKUP', 'Team going to pickup', '55555555-5555-5555-5555-555555555555', 'Team going to pickup', 1, false, true, '#8B5CF6', true, false, now()),
(gen_random_uuid(), 0, 'ARRIVED_AT_PICKUP', 'Team arrived', '55555555-5555-5555-5555-555555555555', 'Team arrived', 2, false, true, '#8B5CF6', true, false, now()),
(gen_random_uuid(), 0, 'PICKUP_STARTED', 'Packing/loading started', '55555555-5555-5555-5555-555555555555', 'Packing/loading started', 3, false, true, '#8B5CF6', true, false, now()),
(gen_random_uuid(), 0, 'PACKING_IN_PROGRESS', 'Packing ongoing', '55555555-5555-5555-5555-555555555555', 'Packing ongoing', 4, false, true, '#8B5CF6', true, false, now()),
(gen_random_uuid(), 0, 'LOADING_IN_PROGRESS', 'Truck loading ongoing', '55555555-5555-5555-5555-555555555555', 'Truck loading ongoing', 5, false, true, '#8B5CF6', true, false, now()),
(gen_random_uuid(), 0, 'PICKUP_COMPLETED', 'Pickup finished', '55555555-5555-5555-5555-555555555555', 'Pickup finished', 6, false, true, '#8B5CF6', true, false, now()),

-- 🚛 Transit Phase (6)
(gen_random_uuid(), 0, 'IN_TRANSIT', 'Truck moving to destination', '66666666-6666-6666-6666-666666666666', 'Truck moving to destination', 1, false, true, '#6366F1', true, false, now()),
(gen_random_uuid(), 0, 'DELAYED', 'Move delayed', '66666666-6666-6666-6666-666666666666', 'Move delayed', 2, false, true, '#6366F1', true, false, now()),
(gen_random_uuid(), 0, 'STOPPED_TEMPORARILY', 'Temporary operational stop', '66666666-6666-6666-6666-666666666666', 'Temporary operational stop', 3, false, true, '#6366F1', false, true, now()),

-- 🏠 Delivery Phase (7)
(gen_random_uuid(), 0, 'ARRIVED_AT_DROPOFF', 'Team reached destination', '77777777-7777-7777-7777-777777777777', 'Team reached destination', 1, false, true, '#D97706', true, false, now()),
(gen_random_uuid(), 0, 'UNLOADING_IN_PROGRESS', 'Unloading ongoing', '77777777-7777-7777-7777-777777777777', 'Unloading ongoing', 2, false, true, '#D97706', true, false, now()),
(gen_random_uuid(), 0, 'UNPACKING_IN_PROGRESS', 'Unpacking ongoing', '77777777-7777-7777-7777-777777777777', 'Unpacking ongoing', 3, false, true, '#D97706', true, false, now()),
(gen_random_uuid(), 0, 'ASSEMBLY_IN_PROGRESS', 'Furniture assembly ongoing', '77777777-7777-7777-7777-777777777777', 'Furniture assembly ongoing', 4, false, true, '#D97706', true, false, now()),
(gen_random_uuid(), 0, 'DELIVERY_COMPLETED', 'Delivery completed', '77777777-7777-7777-7777-777777777777', 'Delivery completed', 5, false, true, '#D97706', true, false, now()),

-- ✅ Completion Phase (8)
(gen_random_uuid(), 0, 'CUSTOMER_CONFIRMATION_PENDING', 'Awaiting customer confirmation', '88888888-8888-8888-8888-888888888888', 'Awaiting customer confirmation', 1, false, true, '#059669', true, false, now()),
(gen_random_uuid(), 0, 'COMPLETED', 'Move completed successfully', '88888888-8888-8888-8888-888888888888', 'Move completed successfully', 2, true, true, '#059669', true, false, now()),
(gen_random_uuid(), 0, 'FEEDBACK_PENDING', 'Waiting for review', '88888888-8888-8888-8888-888888888888', 'Waiting for review', 3, false, true, '#059669', true, false, now()),
(gen_random_uuid(), 0, 'CLOSED', 'Fully closed operationally', '88888888-8888-8888-8888-888888888888', 'Fully closed operationally', 4, true, true, '#059669', false, true, now()),

-- ⚠ Exception Phase (9)
(gen_random_uuid(), 0, 'ON_HOLD', 'Temporarily paused', '99999999-9999-9999-9999-999999999999', 'Temporarily paused', 1, false, true, '#EF4444', true, false, now()),
(gen_random_uuid(), 0, 'CANCELLED', 'Cancelled', '99999999-9999-9999-9999-999999999999', 'Cancelled', 2, true, true, '#EF4444', true, false, now()),
(gen_random_uuid(), 0, 'FAILED', 'Operational failure', '99999999-9999-9999-9999-999999999999', 'Operational failure', 3, true, true, '#EF4444', true, false, now()),
(gen_random_uuid(), 0, 'NO_SHOW_CUSTOMER', 'Customer unavailable', '99999999-9999-9999-9999-999999999999', 'Customer unavailable', 4, false, true, '#EF4444', true, false, now()),
(gen_random_uuid(), 0, 'NO_SHOW_TEAM', 'Team unavailable', '99999999-9999-9999-9999-999999999999', 'Team unavailable', 5, false, true, '#EF4444', true, false, now()),
(gen_random_uuid(), 0, 'PAYMENT_FAILED', 'Payment issue', '99999999-9999-9999-9999-999999999999', 'Payment issue', 6, false, true, '#EF4444', true, false, now()),
(gen_random_uuid(), 0, 'DISPUTE_OPENED', 'Customer complaint/dispute', '99999999-9999-9999-9999-999999999999', 'Customer complaint/dispute', 7, false, true, '#EF4444', true, false, now()),
(gen_random_uuid(), 0, 'CLAIM_OPENED', 'Damage/missing item claim', '99999999-9999-9999-9999-999999999999', 'Damage/missing item claim', 8, false, true, '#EF4444', true, false, now()),
(gen_random_uuid(), 0, 'REFUND_IN_PROGRESS', 'Refund processing', '99999999-9999-9999-9999-999999999999', 'Refund processing', 9, false, true, '#EF4444', true, false, now()),
(gen_random_uuid(), 0, 'REFUNDED', 'Refund completed', '99999999-9999-9999-9999-999999999999', 'Refund completed', 10, true, true, '#EF4444', true, false, now());

-- Add tracking state field in tbl_booking
ALTER TABLE tbl_booking
    ADD COLUMN IF NOT EXISTS current_status_id UUID;

ALTER TABLE tbl_booking
    ADD CONSTRAINT fk_booking_current_status FOREIGN KEY (current_status_id) REFERENCES move_status(id);
