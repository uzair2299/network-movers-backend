-- =============================================================================
-- V8__Add_User_Id_To_Booking.sql
-- Add user_id reference column to tbl_booking linking to tbl_users
-- =============================================================================

ALTER TABLE tbl_booking
    ADD COLUMN IF NOT EXISTS user_id BIGINT;

ALTER TABLE tbl_booking
    ADD CONSTRAINT fk_booking_user FOREIGN KEY (user_id) REFERENCES tbl_users(id);
