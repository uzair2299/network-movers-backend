-- =============================================================================
-- V3__Move_Status_Transitions.sql
-- Database schema additions for Move Status Transitions Workflow engine
-- =============================================================================

CREATE TABLE IF NOT EXISTS move_status_layout (
    id          UUID            PRIMARY KEY,
    version     BIGINT          NOT NULL DEFAULT 0,
    status_id   UUID            NOT NULL,
    position_x  INT,
    position_y  INT,
    deleted     BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by  BIGINT,
    updated_at  TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by  BIGINT,
    CONSTRAINT fk_layout_status FOREIGN KEY (status_id) REFERENCES move_status(id) ON DELETE CASCADE
);

-- Partial unique index to support unique active status layouts while allowing soft-deleted ones
CREATE UNIQUE INDEX IF NOT EXISTS uq_move_status_layout_status ON move_status_layout(status_id) WHERE deleted = false;
CREATE INDEX IF NOT EXISTS idx_move_status_layout_status ON move_status_layout(status_id);

CREATE TABLE IF NOT EXISTS move_status_transition (
    id                 UUID            PRIMARY KEY,
    version            BIGINT          NOT NULL DEFAULT 0,
    from_status_id     UUID            NOT NULL,
    to_status_id       UUID            NOT NULL,
    transition_name    VARCHAR(255),
    allowed_role_id    UUID,
    requires_approval  BOOLEAN         NOT NULL DEFAULT FALSE,
    customer_visible   BOOLEAN         NOT NULL DEFAULT TRUE,
    active             BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted            BOOLEAN         NOT NULL DEFAULT FALSE,
    deleted_at         TIMESTAMP,
    deleted_by         BIGINT,
    created_at         TIMESTAMP       NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_by         BIGINT,
    updated_at         TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    updated_by         BIGINT,
    CONSTRAINT fk_transition_from_status FOREIGN KEY (from_status_id) REFERENCES move_status(id) ON DELETE CASCADE,
    CONSTRAINT fk_transition_to_status   FOREIGN KEY (to_status_id)   REFERENCES move_status(id) ON DELETE CASCADE,
    CONSTRAINT fk_transition_allowed_role FOREIGN KEY (allowed_role_id) REFERENCES sec_roles(id) ON DELETE SET NULL
);

CREATE INDEX IF NOT EXISTS idx_transition_from_status ON move_status_transition(from_status_id);
CREATE INDEX IF NOT EXISTS idx_transition_to_status   ON move_status_transition(to_status_id);
CREATE INDEX IF NOT EXISTS idx_transition_allowed_role ON move_status_transition(allowed_role_id);
