-- =============================================================================
-- V19__Add_Booking_History_Table.sql
-- Create table for tracking booking status lifecycle
-- =============================================================================

CREATE TABLE IF NOT EXISTS tbl_booking_history (
    id                 BIGSERIAL PRIMARY KEY,
    booking_id         BIGINT NOT NULL,
    previous_status_id UUID,
    new_status_id      UUID NOT NULL,
    notes              VARCHAR(1000),
    created_at         TIMESTAMP WITH TIME ZONE NOT NULL,
    created_by         BIGINT,
    updated_at         TIMESTAMP WITH TIME ZONE,
    updated_by         BIGINT,
    
    CONSTRAINT fk_bh_booking FOREIGN KEY (booking_id) REFERENCES tbl_booking(id) ON DELETE CASCADE,
    CONSTRAINT fk_bh_previous_status FOREIGN KEY (previous_status_id) REFERENCES move_status(id),
    CONSTRAINT fk_bh_new_status FOREIGN KEY (new_status_id) REFERENCES move_status(id)
);

CREATE INDEX IF NOT EXISTS idx_booking_history_booking_created 
    ON tbl_booking_history (booking_id, created_at DESC);
