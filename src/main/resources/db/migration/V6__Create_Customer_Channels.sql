CREATE TABLE IF NOT EXISTS tbl_customer_channels (
    id                  UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version             BIGINT,
    customer_id         UUID            NOT NULL,
    source              VARCHAR(50)     NOT NULL CHECK (source IN ('FACEBOOK', 'WHATSAPP', 'INSTAGRAM', 'WEBCHAT')),
    external_user_id    VARCHAR(255)    NOT NULL,
    active              BOOLEAN         NOT NULL DEFAULT true,
    deleted             BOOLEAN         NOT NULL DEFAULT false,
    deleted_at          TIMESTAMP,
    deleted_by          BIGINT,
    created_at          TIMESTAMP       NOT NULL,
    created_by          BIGINT,
    updated_at          TIMESTAMP,
    updated_by          BIGINT,

    UNIQUE (source, external_user_id),
    FOREIGN KEY (customer_id) REFERENCES tbl_customer(id)
);
