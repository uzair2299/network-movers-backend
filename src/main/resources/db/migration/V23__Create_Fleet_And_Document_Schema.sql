CREATE TABLE document_types (
    id                    UUID PRIMARY KEY,
    version               BIGINT DEFAULT 0,
    created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by            BIGINT,
    updated_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by            BIGINT,
    deleted               BOOLEAN DEFAULT FALSE,
    deleted_at            TIMESTAMP,
    deleted_by            BIGINT,
    
    code                  VARCHAR(50) UNIQUE NOT NULL,
    name                  VARCHAR(100) NOT NULL,
    active                BOOLEAN NOT NULL DEFAULT TRUE,

    mandatory             BOOLEAN DEFAULT FALSE,
    expiry_required       BOOLEAN DEFAULT TRUE,
    description           TEXT
);

CREATE TABLE vehicle_types (
    id                    UUID PRIMARY KEY,
    version               BIGINT DEFAULT 0,
    created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by            BIGINT,
    updated_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by            BIGINT,
    deleted               BOOLEAN DEFAULT FALSE,
    deleted_at            TIMESTAMP,
    deleted_by            BIGINT,

    code                  VARCHAR(50) UNIQUE NOT NULL,
    name                  VARCHAR(100) NOT NULL,
    active                BOOLEAN NOT NULL DEFAULT TRUE,
    
    description           TEXT
);

CREATE TABLE vehicle_makes (
    id                    UUID PRIMARY KEY,
    version               BIGINT DEFAULT 0,
    created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by            BIGINT,
    updated_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by            BIGINT,
    deleted               BOOLEAN DEFAULT FALSE,
    deleted_at            TIMESTAMP,
    deleted_by            BIGINT,

    code                  VARCHAR(50) UNIQUE NOT NULL,
    name                  VARCHAR(100) NOT NULL,
    active                BOOLEAN NOT NULL DEFAULT TRUE,
    
    country               VARCHAR(100)
);

CREATE TABLE vehicle_models (
    id                      UUID PRIMARY KEY,
    version                 BIGINT DEFAULT 0,
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by              BIGINT,
    updated_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by              BIGINT,
    deleted                 BOOLEAN DEFAULT FALSE,
    deleted_at              TIMESTAMP,
    deleted_by              BIGINT,

    make_id                 UUID NOT NULL,
    vehicle_type_id         UUID NOT NULL,

    code                    VARCHAR(50) UNIQUE NOT NULL,
    name                    VARCHAR(100) NOT NULL,
    active                  BOOLEAN NOT NULL DEFAULT TRUE,

    capacity_kg             NUMERIC(10,2),
    capacity_m3             NUMERIC(10,2),

    length_m                NUMERIC(10,2),
    width_m                 NUMERIC(10,2),
    height_m                NUMERIC(10,2),

    CONSTRAINT fk_vm_make
        FOREIGN KEY (make_id)
        REFERENCES vehicle_makes(id),

    CONSTRAINT fk_vm_type
        FOREIGN KEY (vehicle_type_id)
        REFERENCES vehicle_types(id)
);

CREATE TABLE vehicles (
    id                          UUID PRIMARY KEY,
    version                     BIGINT DEFAULT 0,
    created_at                  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by                  BIGINT,
    updated_at                  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by                  BIGINT,
    deleted                     BOOLEAN DEFAULT FALSE,
    deleted_at                  TIMESTAMP,
    deleted_by                  BIGINT,

    vehicle_code                VARCHAR(50) UNIQUE NOT NULL,
    registration_no             VARCHAR(100) UNIQUE NOT NULL,

    vehicle_model_id            UUID NOT NULL,

    manufacture_year            INTEGER,
    ownership_type              VARCHAR(50) NOT NULL,
    status                      VARCHAR(50) NOT NULL,

    current_odometer_km         NUMERIC(12,2),
    insurance_expiry_date       DATE,
    fitness_expiry_date         DATE,
    acquisition_date            DATE,

    active                      BOOLEAN NOT NULL DEFAULT TRUE,
    remarks                     TEXT,

    CONSTRAINT fk_vehicle_model
        FOREIGN KEY(vehicle_model_id)
        REFERENCES vehicle_models(id)
);

CREATE TABLE vehicle_documents (
    id                          UUID PRIMARY KEY,
    version                     BIGINT DEFAULT 0,
    created_at                  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by                  BIGINT,
    updated_at                  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by                  BIGINT,
    deleted                     BOOLEAN DEFAULT FALSE,
    deleted_at                  TIMESTAMP,
    deleted_by                  BIGINT,

    vehicle_id                  UUID NOT NULL,
    document_type_id            UUID NOT NULL,

    document_number             VARCHAR(255),
    issue_date                  DATE,
    expiry_date                 DATE,
    file_url                    TEXT,
    verified                    BOOLEAN DEFAULT FALSE,
    remarks                     TEXT,

    CONSTRAINT fk_vd_vehicle
        FOREIGN KEY(vehicle_id)
        REFERENCES vehicles(id),

    CONSTRAINT fk_vd_doc_type
        FOREIGN KEY(document_type_id)
        REFERENCES document_types(id)
);

CREATE TABLE vehicle_maintenance_types (
    id                    UUID PRIMARY KEY,
    version               BIGINT DEFAULT 0,
    created_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by            BIGINT,
    updated_at            TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by            BIGINT,
    deleted               BOOLEAN DEFAULT FALSE,
    deleted_at            TIMESTAMP,
    deleted_by            BIGINT,

    code                  VARCHAR(50) UNIQUE NOT NULL,
    name                  VARCHAR(100) NOT NULL,
    active                BOOLEAN NOT NULL DEFAULT TRUE,
    
    description           TEXT
);

CREATE TABLE vehicle_maintenance (
    id                      UUID PRIMARY KEY,
    version                 BIGINT DEFAULT 0,
    created_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by              BIGINT,
    updated_at              TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by              BIGINT,
    deleted                 BOOLEAN DEFAULT FALSE,
    deleted_at              TIMESTAMP,
    deleted_by              BIGINT,

    vehicle_id              UUID NOT NULL,
    maintenance_type_id     UUID NOT NULL,

    maintenance_date        DATE NOT NULL,
    odometer_km             NUMERIC(12,2),
    cost                    NUMERIC(12,2),

    vendor_name             VARCHAR(255),
    next_service_date       DATE,
    next_service_km         NUMERIC(12,2),

    remarks                 TEXT,

    CONSTRAINT fk_vmaint_vehicle
        FOREIGN KEY (vehicle_id) REFERENCES vehicles(id),
    CONSTRAINT fk_vmaint_type
        FOREIGN KEY (maintenance_type_id) REFERENCES vehicle_maintenance_types(id)
);

CREATE TABLE vehicle_fuel_logs (
    id                          UUID PRIMARY KEY,
    version                     BIGINT DEFAULT 0,
    created_at                  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by                  BIGINT,
    updated_at                  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_by                  BIGINT,
    deleted                     BOOLEAN DEFAULT FALSE,
    deleted_at                  TIMESTAMP,
    deleted_by                  BIGINT,

    vehicle_id                  UUID NOT NULL,

    fuel_date                   TIMESTAMP NOT NULL,
    fuel_quantity_liters        NUMERIC(10,2),
    cost_amount                 NUMERIC(12,2),
    odometer_km                 NUMERIC(12,2),
    fuel_station                VARCHAR(255),
    remarks                     TEXT,

    CONSTRAINT fk_vfl_vehicle
        FOREIGN KEY(vehicle_id)
        REFERENCES vehicles(id)
);
