const fs = require('fs');

const sqlPath = 'src/main/resources/db/migration/V1__Initial_Schema.sql';
let content = fs.readFileSync(sqlPath, 'utf8');

const moduleReplacement = `(
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    code        VARCHAR(255)    NOT NULL UNIQUE,
    name        VARCHAR(255)    NOT NULL,
    description VARCHAR(255),
    active      BOOLEAN         NOT NULL DEFAULT true,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);`;

// Replace all tbl_* module tables except tbl_users
content = content.replace(/CREATE TABLE IF NOT EXISTS tbl_([a-z_]+)\s*\([\s\S]*?\);/g, (match, tableName) => {
    if (tableName === 'users') return match;
    return `CREATE TABLE IF NOT EXISTS tbl_${tableName} ${moduleReplacement}`;
});

// Update sec_permissions and sec_resources
content = content.replace(/CREATE TABLE IF NOT EXISTS sec_permissions\s*\([\s\S]*?\);/, `CREATE TABLE IF NOT EXISTS sec_permissions ${moduleReplacement}`);
content = content.replace(/CREATE TABLE IF NOT EXISTS sec_resources\s*\([\s\S]*?\);/, `CREATE TABLE IF NOT EXISTS sec_resources ${moduleReplacement}`);

// Update tbl_users to use UUID and soft delete fields
content = content.replace(/CREATE TABLE IF NOT EXISTS tbl_users\s*\([\s\S]*?\);/, `CREATE TABLE IF NOT EXISTS tbl_users (
    id          UUID            PRIMARY KEY DEFAULT gen_random_uuid(),
    version     BIGINT,
    username    VARCHAR(255)    NOT NULL UNIQUE,
    email       VARCHAR(255)    NOT NULL UNIQUE,
    password    VARCHAR(255)    NOT NULL,
    enabled     BOOLEAN         NOT NULL DEFAULT TRUE,
    deleted     BOOLEAN         NOT NULL DEFAULT false,
    deleted_at  TIMESTAMP,
    deleted_by  BIGINT,
    created_at  TIMESTAMP       NOT NULL,
    created_by  BIGINT,
    updated_at  TIMESTAMP,
    updated_by  BIGINT
);`);

fs.writeFileSync(sqlPath, content, 'utf8');
console.log('Schema refactored successfully.');
