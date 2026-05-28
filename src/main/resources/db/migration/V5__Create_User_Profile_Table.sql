-- =============================================================================
-- V5__Create_User_Profile_Table.sql
-- Create tbl_user_profiles table and link with tbl_users
-- =============================================================================

CREATE TABLE IF NOT EXISTS tbl_user_profiles (
    id                  BIGSERIAL       PRIMARY KEY,
    version             BIGINT,
    user_id             BIGINT          NOT NULL UNIQUE,
    first_name          VARCHAR(50),
    last_name           VARCHAR(50),
    phone_number        VARCHAR(20),
    profile_picture_url VARCHAR(255),
    address             VARCHAR(255),
    created_at          TIMESTAMP       NOT NULL,
    created_by          BIGINT,
    updated_at          TIMESTAMP,
    updated_by          BIGINT,
    CONSTRAINT fk_user_profile_user FOREIGN KEY (user_id) REFERENCES tbl_users(id) ON DELETE CASCADE
);

-- Seed default empty profile records for existing users to maintain 1:1 integrity
INSERT INTO tbl_user_profiles (user_id, created_at)
SELECT id, created_at FROM tbl_users
ON CONFLICT (user_id) DO NOTHING;
