-- =============================================================================
-- V6__Seed_User_Profiles.sql
-- Seed / update profiles for default users (admin & customer)
-- =============================================================================

UPDATE tbl_user_profiles
SET first_name = 'Admin', 
    last_name = 'User', 
    phone_number = '+123456789', 
    address = 'Admin HQ'
WHERE user_id = (SELECT id FROM tbl_users WHERE username = 'admin');

UPDATE tbl_user_profiles
SET first_name = 'Customer', 
    last_name = 'User', 
    phone_number = '+987654321', 
    address = 'Customer Address'
WHERE user_id = (SELECT id FROM tbl_users WHERE username = 'customer');
