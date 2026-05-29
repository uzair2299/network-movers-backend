-- =============================================================================
-- V7__Add_Booking_Details.sql
-- Update tbl_booking to support complex move specifications and routing
-- =============================================================================

-- Make existing name column nullable
ALTER TABLE tbl_booking ALTER COLUMN name DROP NOT NULL;

-- Add new columns to tbl_booking
ALTER TABLE tbl_booking
    ADD COLUMN IF NOT EXISTS pickup_address VARCHAR(500),
    ADD COLUMN IF NOT EXISTS pickup_latitude NUMERIC(10, 8),
    ADD COLUMN IF NOT EXISTS pickup_longitude NUMERIC(11, 8),
    ADD COLUMN IF NOT EXISTS destination_address VARCHAR(500),
    ADD COLUMN IF NOT EXISTS destination_latitude NUMERIC(10, 8),
    ADD COLUMN IF NOT EXISTS destination_longitude NUMERIC(11, 8),
    ADD COLUMN IF NOT EXISTS distance_km NUMERIC(10, 2),
    ADD COLUMN IF NOT EXISTS duration_minutes INT,
    ADD COLUMN IF NOT EXISTS schedule_type VARCHAR(50),
    ADD COLUMN IF NOT EXISTS scheduled_date TIMESTAMP WITH TIME ZONE,
    ADD COLUMN IF NOT EXISTS time_slot VARCHAR(100),
    ADD COLUMN IF NOT EXISTS property_category_id UUID,
    ADD COLUMN IF NOT EXISTS property_type_id UUID,
    ADD COLUMN IF NOT EXISTS property_size_id UUID,
    ADD COLUMN IF NOT EXISTS pickup_floor_type_id UUID,
    ADD COLUMN IF NOT EXISTS pickup_building_access_id UUID,
    ADD COLUMN IF NOT EXISTS pickup_parking_access_id UUID,
    ADD COLUMN IF NOT EXISTS destination_floor_type_id UUID,
    ADD COLUMN IF NOT EXISTS destination_building_access_id UUID,
    ADD COLUMN IF NOT EXISTS destination_parking_access_id UUID;

-- Add foreign key constraints to tbl_booking
ALTER TABLE tbl_booking
    ADD CONSTRAINT fk_booking_property_category FOREIGN KEY (property_category_id) REFERENCES property_category(id),
    ADD CONSTRAINT fk_booking_property_type FOREIGN KEY (property_type_id) REFERENCES property_type(id),
    ADD CONSTRAINT fk_booking_property_size FOREIGN KEY (property_size_id) REFERENCES property_size(id),
    ADD CONSTRAINT fk_booking_pickup_floor_type FOREIGN KEY (pickup_floor_type_id) REFERENCES floor_type(id),
    ADD CONSTRAINT fk_booking_pickup_building_access FOREIGN KEY (pickup_building_access_id) REFERENCES building_access_type(id),
    ADD CONSTRAINT fk_booking_pickup_parking_access FOREIGN KEY (pickup_parking_access_id) REFERENCES parking_access_type(id),
    ADD CONSTRAINT fk_booking_destination_floor_type FOREIGN KEY (destination_floor_type_id) REFERENCES floor_type(id),
    ADD CONSTRAINT fk_booking_destination_building_access FOREIGN KEY (destination_building_access_id) REFERENCES building_access_type(id),
    ADD CONSTRAINT fk_booking_destination_parking_access FOREIGN KEY (destination_parking_access_id) REFERENCES parking_access_type(id);

-- Create join tables for pickup and destination access restrictions
CREATE TABLE IF NOT EXISTS tbl_booking_pickup_restrictions (
    booking_id BIGINT NOT NULL,
    restriction_id UUID NOT NULL,
    PRIMARY KEY (booking_id, restriction_id),
    CONSTRAINT fk_tbpr_booking FOREIGN KEY (booking_id) REFERENCES tbl_booking(id) ON DELETE CASCADE,
    CONSTRAINT fk_tbpr_restriction FOREIGN KEY (restriction_id) REFERENCES access_restriction_type(id)
);

CREATE TABLE IF NOT EXISTS tbl_booking_destination_restrictions (
    booking_id BIGINT NOT NULL,
    restriction_id UUID NOT NULL,
    PRIMARY KEY (booking_id, restriction_id),
    CONSTRAINT fk_tbdr_booking FOREIGN KEY (booking_id) REFERENCES tbl_booking(id) ON DELETE CASCADE,
    CONSTRAINT fk_tbdr_restriction FOREIGN KEY (restriction_id) REFERENCES access_restriction_type(id)
);
