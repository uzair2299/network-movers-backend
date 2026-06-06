ALTER TABLE sec_resources
ADD COLUMN module_id UUID;

ALTER TABLE sec_resources
ADD CONSTRAINT fk_resources_module
FOREIGN KEY (module_id) REFERENCES modules(id)
ON DELETE SET NULL;
