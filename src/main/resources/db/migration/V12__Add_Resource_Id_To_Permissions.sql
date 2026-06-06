-- V12: Add resource_id FK to sec_permissions
ALTER TABLE sec_permissions
    ADD COLUMN IF NOT EXISTS resource_id UUID,
    ADD CONSTRAINT fk_permissions_resource
        FOREIGN KEY (resource_id) REFERENCES sec_resources(id);
