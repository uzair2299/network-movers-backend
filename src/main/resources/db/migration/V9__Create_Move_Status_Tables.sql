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
('02868ff1-7443-4e44-8d48-356a7c36a4be', 0, 'REQUEST', 'Request Phase', 1, true, now()),
('f5dc2118-0524-4f81-a9f8-cf9bb1112443', 0, 'QUOTATION', 'Quotation Phase', 2, true, now()),
('4c207d57-3a1d-44a3-a006-2580c85025d5', 0, 'BOOKING', 'Booking Phase', 3, true, now()),
('9df848b5-bb4e-4f76-880f-90e85f4f895c', 0, 'OPERATIONS', 'Operations Phase', 4, true, now()),
('d7bb24f3-dc90-410a-8bf8-2a2be10c7931', 0, 'PICKUP', 'Pickup Phase', 5, true, now()),
('99fa2f33-b18f-4cb1-80a5-f8ea55ebcde2', 0, 'TRANSIT', 'Transit Phase', 6, true, now()),
('6df926c4-ee80-496a-84fb-4b5fb4e81561', 0, 'DELIVERY', 'Delivery Phase', 7, true, now()),
('ea68e22d-6215-4c6e-821f-eb3b1c67675f', 0, 'COMPLETION', 'Completion Phase', 8, true, now()),
('79be40de-8d06-444d-8cf8-410fb556be4f', 0, 'EXCEPTION', 'Exception / Failure States', 9, true, now());

-- Seed Move Statuses
INSERT INTO move_status (id, version, code, name, phase_id, description, sequence_no, is_final, active, color_code, customer_visible, internal_only, created_at)
VALUES
-- 🟦 Request Phase (1)
('fd9a693a-ba36-4054-859a-ab739783d170', 0, 'REQUESTED', 'Customer submitted move request', (SELECT id FROM move_phase WHERE code = 'REQUEST'), 'Customer submitted move request', 1, false, true, '#3B82F6', true, false, now()),
('4660f8f9-5e66-4e9d-bc43-5f673bb22433', 0, 'UNDER_REVIEW', 'Operations reviewing request', (SELECT id FROM move_phase WHERE code = 'REQUEST'), 'Operations reviewing request', 2, false, true, '#3B82F6', true, false, now()),
('479daad9-23ac-40b4-8a68-487b26485a80', 0, 'AWAITING_DETAILS', 'Waiting for customer info/photos', (SELECT id FROM move_phase WHERE code = 'REQUEST'), 'Waiting for customer info/photos', 3, false, true, '#3B82F6', true, false, now()),
('d3db6e69-b164-419c-b287-a9adcc8b4137', 0, 'SURVEY_SCHEDULED', 'Physical/virtual survey planned', (SELECT id FROM move_phase WHERE code = 'REQUEST'), 'Physical/virtual survey planned', 4, false, true, '#3B82F6', true, false, now()),
('f7cdf1c6-a562-4a6f-be09-c51072813716', 0, 'SURVEY_COMPLETED', 'Survey completed', (SELECT id FROM move_phase WHERE code = 'REQUEST'), 'Survey completed', 5, false, true, '#3B82F6', true, false, now()),

-- 💰 Quotation Phase (2)
('d9a1bbfa-0f8b-4602-8dc9-691c2dcdde3f', 0, 'QUOTATION_IN_PROGRESS', 'Pricing being prepared', (SELECT id FROM move_phase WHERE code = 'QUOTATION'), 'Pricing being prepared', 1, false, true, '#F59E0B', false, true, now()),
('2eca3410-2fbe-417c-9bbb-a45ea149ab39', 0, 'QUOTATION_SENT', 'Quote sent to customer', (SELECT id FROM move_phase WHERE code = 'QUOTATION'), 'Quote sent to customer', 2, false, true, '#F59E0B', true, false, now()),
('165f5215-e1dd-42f8-ac13-796363f7b6a2', 0, 'QUOTATION_VIEWED', 'Customer viewed quotation', (SELECT id FROM move_phase WHERE code = 'QUOTATION'), 'Customer viewed quotation', 3, false, true, '#F59E0B', false, true, now()),
('c293a003-ddd9-4bcd-9772-11f56cb6226c', 0, 'NEGOTIATION_IN_PROGRESS', 'Pricing negotiation ongoing', (SELECT id FROM move_phase WHERE code = 'QUOTATION'), 'Pricing negotiation ongoing', 4, false, true, '#F59E0B', true, false, now()),
('91d86eba-584d-411a-9e52-b1874aedb0bb', 0, 'QUOTATION_APPROVED', 'Customer approved quote', (SELECT id FROM move_phase WHERE code = 'QUOTATION'), 'Customer approved quote', 5, false, true, '#F59E0B', true, false, now()),
('24992591-1f25-4ee5-bae1-9877102c7897', 0, 'QUOTATION_REJECTED', 'Customer rejected quote', (SELECT id FROM move_phase WHERE code = 'QUOTATION'), 'Customer rejected quote', 6, false, true, '#F59E0B', true, false, now()),

-- ✅ Booking Phase (3)
('e86ce797-f8e8-4ac0-8896-eb5b5cb59baa', 0, 'CONFIRMED', 'Booking confirmed', (SELECT id FROM move_phase WHERE code = 'BOOKING'), 'Booking confirmed', 1, false, true, '#10B981', true, false, now()),
('678ab4b1-e0f2-4f08-bef0-348a41b32913', 0, 'PENDING_PAYMENT', 'Awaiting advance payment', (SELECT id FROM move_phase WHERE code = 'BOOKING'), 'Awaiting advance payment', 2, false, true, '#10B981', true, false, now()),
('f7d08540-2858-46ad-9f86-a28b3f2608e7', 0, 'PARTIALLY_PAID', 'Partial payment received', (SELECT id FROM move_phase WHERE code = 'BOOKING'), 'Partial payment received', 3, false, true, '#10B981', true, false, now()),
('602287d0-a299-48c3-a693-4a577bbeeb2c', 0, 'PAID', 'Full payment received', (SELECT id FROM move_phase WHERE code = 'BOOKING'), 'Full payment received', 4, false, true, '#10B981', true, false, now()),
('695cefa9-3b48-426c-8c83-cf17eb5b84ef', 0, 'INVOICE_GENERATED', 'Invoice created', (SELECT id FROM move_phase WHERE code = 'BOOKING'), 'Invoice created', 5, false, true, '#10B981', true, false, now()),

-- 👷 Operations Phase (4)
('bc6b9db7-8f8c-45bf-9d3f-24f4ed637c36', 0, 'ASSIGNMENT_PENDING', 'Waiting for mover assignment', (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Waiting for mover assignment', 1, false, true, '#F97316', true, false, now()),
('33a1f74d-8c38-480b-b7aa-3de22d8a9aa0', 0, 'ASSIGNED', 'Team/driver assigned', (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Team/driver assigned', 2, false, true, '#F97316', true, false, now()),
('57dc0bad-3828-4762-8a4a-dfe56c18d448', 0, 'VEHICLE_ASSIGNED', 'Truck assigned', (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Truck assigned', 3, false, true, '#F97316', false, true, now()),
('e53a496d-b391-4df6-8f4d-30d3e142204d', 0, 'CREW_ASSIGNED', 'Movers assigned', (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Movers assigned', 4, false, true, '#F97316', false, true, now()),
('9f6b2d81-8f5c-404d-9e85-7e91ea345002', 0, 'SCHEDULED', 'Move officially scheduled', (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Move officially scheduled', 5, false, true, '#F97316', true, false, now()),
('9f6ba7ed-9869-4aee-9328-eac6dcb96ba5', 0, 'RESCHEDULE_REQUESTED', 'Customer requested new date', (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Customer requested new date', 6, false, true, '#F97316', true, false, now()),
('746f7475-61fe-4b2a-b18c-b7a906b03dac', 0, 'RESCHEDULED', 'Move rescheduled', (SELECT id FROM move_phase WHERE code = 'OPERATIONS'), 'Move rescheduled', 7, false, true, '#F97316', true, false, now()),

-- 📦 Pickup Phase (5)
('fe73627e-4978-4e35-aa92-d0c036d0e23c', 0, 'TEAM_EN_ROUTE_PICKUP', 'Team going to pickup', (SELECT id FROM move_phase WHERE code = 'PICKUP'), 'Team going to pickup', 1, false, true, '#8B5CF6', true, false, now()),
('2fa4bc51-7345-4ed8-98fa-7441c22ad661', 0, 'ARRIVED_AT_PICKUP', 'Team arrived', (SELECT id FROM move_phase WHERE code = 'PICKUP'), 'Team arrived', 2, false, true, '#8B5CF6', true, false, now()),
('e3276133-7be8-42f6-88ad-0de11de11d64', 0, 'PICKUP_STARTED', 'Packing/loading started', (SELECT id FROM move_phase WHERE code = 'PICKUP'), 'Packing/loading started', 3, false, true, '#8B5CF6', true, false, now()),
('36e385f8-1dc0-4035-a265-d10d8ab687ba', 0, 'PACKING_IN_PROGRESS', 'Packing ongoing', (SELECT id FROM move_phase WHERE code = 'PICKUP'), 'Packing ongoing', 4, false, true, '#8B5CF6', true, false, now()),
('0b0c7054-bd61-4dd6-942c-c5f997e9f9ad', 0, 'LOADING_IN_PROGRESS', 'Truck loading ongoing', (SELECT id FROM move_phase WHERE code = 'PICKUP'), 'Truck loading ongoing', 5, false, true, '#8B5CF6', true, false, now()),
('67136489-f8e3-4b8f-b516-1e36dbcd7bef', 0, 'PICKUP_COMPLETED', 'Pickup finished', (SELECT id FROM move_phase WHERE code = 'PICKUP'), 'Pickup finished', 6, false, true, '#8B5CF6', true, false, now()),

-- 🚛 Transit Phase (6)
('f0b674f6-30a4-4b97-b2fb-bc7562aae69a', 0, 'IN_TRANSIT', 'Truck moving to destination', (SELECT id FROM move_phase WHERE code = 'TRANSIT'), 'Truck moving to destination', 1, false, true, '#6366F1', true, false, now()),
('d0135594-7074-4d3c-8c04-bf2521d7db1b', 0, 'DELAYED', 'Move delayed', (SELECT id FROM move_phase WHERE code = 'TRANSIT'), 'Move delayed', 2, false, true, '#6366F1', true, false, now()),
('23ba8b63-a419-4191-a0e7-f47a3d227555', 0, 'STOPPED_TEMPORARILY', 'Temporary operational stop', (SELECT id FROM move_phase WHERE code = 'TRANSIT'), 'Temporary operational stop', 3, false, true, '#6366F1', false, true, now()),

-- 🏠 Delivery Phase (7)
('708c9b06-7fa7-4f58-96ad-2c6fb20e7d73', 0, 'ARRIVED_AT_DROPOFF', 'Team reached destination', (SELECT id FROM move_phase WHERE code = 'DELIVERY'), 'Team reached destination', 1, false, true, '#D97706', true, false, now()),
('9b46467a-affe-433f-b0db-d663565a4edf', 0, 'UNLOADING_IN_PROGRESS', 'Unloading ongoing', (SELECT id FROM move_phase WHERE code = 'DELIVERY'), 'Unloading ongoing', 2, false, true, '#D97706', true, false, now()),
('eeab7f21-337f-46d1-abc5-243624005249', 0, 'UNPACKING_IN_PROGRESS', 'Unpacking ongoing', (SELECT id FROM move_phase WHERE code = 'DELIVERY'), 'Unpacking ongoing', 3, false, true, '#D97706', true, false, now()),
('37538247-cd3f-4614-a988-bbf06d493e23', 0, 'ASSEMBLY_IN_PROGRESS', 'Furniture assembly ongoing', (SELECT id FROM move_phase WHERE code = 'DELIVERY'), 'Furniture assembly ongoing', 4, false, true, '#D97706', true, false, now()),
('fa70f318-79a6-4efe-8389-fceb8420f3b8', 0, 'DELIVERY_COMPLETED', 'Delivery completed', (SELECT id FROM move_phase WHERE code = 'DELIVERY'), 'Delivery completed', 5, false, true, '#D97706', true, false, now()),

-- ✅ Completion Phase (8)
('3f3b73cb-2520-46b6-b813-07fab30f5b56', 0, 'CUSTOMER_CONFIRMATION_PENDING', 'Awaiting customer confirmation', (SELECT id FROM move_phase WHERE code = 'COMPLETION'), 'Awaiting customer confirmation', 1, false, true, '#059669', true, false, now()),
('b2a108af-5af9-49ab-ba22-4502b61ed63f', 0, 'COMPLETED', 'Move completed successfully', (SELECT id FROM move_phase WHERE code = 'COMPLETION'), 'Move completed successfully', 2, true, true, '#059669', true, false, now()),
('469c122f-3a16-4f14-be17-c83ebdb80819', 0, 'FEEDBACK_PENDING', 'Waiting for review', (SELECT id FROM move_phase WHERE code = 'COMPLETION'), 'Waiting for review', 3, false, true, '#059669', true, false, now()),
('a553c8d1-5dab-4436-81e8-14e8bcead23b', 0, 'CLOSED', 'Fully closed operationally', (SELECT id FROM move_phase WHERE code = 'COMPLETION'), 'Fully closed operationally', 4, true, true, '#059669', false, true, now()),

-- ⚠ Exception Phase (9)
('cd92e2d4-1edd-4040-b0a2-2af1d4fa10b0', 0, 'ON_HOLD', 'Temporarily paused', (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Temporarily paused', 1, false, true, '#EF4444', true, false, now()),
('f74f44a1-5f57-41be-a430-26b50e52b608', 0, 'CANCELLED', 'Cancelled', (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Cancelled', 2, true, true, '#EF4444', true, false, now()),
('7bbc4657-4447-4972-a4a8-62c5bdc34ea2', 0, 'FAILED', 'Operational failure', (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Operational failure', 3, true, true, '#EF4444', true, false, now()),
('7735c08e-82ff-42b9-9feb-d88a25f86208', 0, 'NO_SHOW_CUSTOMER', 'Customer unavailable', (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Customer unavailable', 4, false, true, '#EF4444', true, false, now()),
('ff3b7010-c37d-40f5-84b9-143009464ac7', 0, 'NO_SHOW_TEAM', 'Team unavailable', (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Team unavailable', 5, false, true, '#EF4444', true, false, now()),
('176fe951-3217-4a0f-a4f2-7b3ea53288c2', 0, 'PAYMENT_FAILED', 'Payment issue', (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Payment issue', 6, false, true, '#EF4444', true, false, now()),
('f1d70872-b9e0-441a-bf0e-6095d19e7200', 0, 'DISPUTE_OPENED', 'Customer complaint/dispute', (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Customer complaint/dispute', 7, false, true, '#EF4444', true, false, now()),
('3988153e-4d3f-4690-8196-3e5dde115e5a', 0, 'CLAIM_OPENED', 'Damage/missing item claim', (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Damage/missing item claim', 8, false, true, '#EF4444', true, false, now()),
('168a6427-a7fb-44ab-8fb7-a3cb34e7cb55', 0, 'REFUND_IN_PROGRESS', 'Refund processing', (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Refund processing', 9, false, true, '#EF4444', true, false, now()),
('cc7d0595-ef36-4235-bacc-57e5a537a8d3', 0, 'REFUNDED', 'Refund completed', (SELECT id FROM move_phase WHERE code = 'EXCEPTION'), 'Refund completed', 10, true, true, '#EF4444', true, false, now());

-- Add tracking state field in tbl_booking
ALTER TABLE tbl_booking
    ADD COLUMN IF NOT EXISTS current_status_id UUID;

ALTER TABLE tbl_booking
    ADD CONSTRAINT fk_booking_current_status FOREIGN KEY (current_status_id) REFERENCES move_status(id);
