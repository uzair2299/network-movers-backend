# network-movers-backend

Spring Boot Enterprise Moving Services platform.

## Swagger Docs & OpenAPI Specification

- **Local Development** (when running locally on port 8081):
  - Swagger UI: [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
  - OpenAPI JSON: [http://localhost:8081/v3/api-docs](http://localhost:8081/v3/api-docs)

- **Production Server (Railway)**:
  - Base URL: `https://network-movers-backend-production.up.railway.app`
  - Swagger UI: [https://network-movers-backend-production.up.railway.app/swagger-ui/index.html](https://network-movers-backend-production.up.railway.app/swagger-ui/index.html)
  - OpenAPI JSON: [https://network-movers-backend-production.up.railway.app/v3/api-docs](https://network-movers-backend-production.up.railway.app/v3/api-docs)

## Actuator Endpoints

- Local health: [http://localhost:8081/actuator/health](http://localhost:8081/actuator/health)
- Local info: [http://localhost:8081/actuator/info](http://localhost:8081/actuator/info)
- Local Prometheus metrics: [http://localhost:8081/actuator/prometheus](http://localhost:8081/actuator/prometheus)

Health and info endpoints are permitted anonymously; all other API routes are protected.

## Project Directory Structure

```text
network-movers/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/company/networkmovers/
│   │   │       ├── bootstrap/          # Startup classes and seed data logic
│   │   │       │   └── seeder/         # Seeding default roles/users (e.g. SecurityDataSeeder.java)
│   │   │       ├── config/             # Configuration beans
│   │   │       │   ├── database/       # Database & JPA auditing configurations
│   │   │       │   ├── openapi/        # OpenAPI/Swagger API doc customization
│   │   │       │   └── security/       # Spring Security filters and configurations
│   │   │       ├── modules/            # Enterprise modules (Features & Domains)
│   │   │       │   ├── identity/       # Identity management (Users, Roles, Permissions)
│   │   │       │   ├── accounting/     # Financial ledger & accounting module
│   │   │       │   ├── mover/          # Movers and personnel details
│   │   │       │   ├── vehicle/        # Fleet vehicles and status
│   │   │       │   └── ...             # 40+ other business domain packages
│   │   │       │       # (Each module uses standard JPA layering: Controller, Service, DTO, Repository, Entity)
│   │   │       │       ├── controller/
│   │   │       │       │   ├── admin/  # Secured operations (requires ROLE_ADMIN)
│   │   │       │       │   └── open/   # Unsecured public operations
│   │   │       │       ├── service/
│   │   │       │       ├── dto/
│   │   │       │       ├── repository/
│   │   │       │       └── entity/
│   │   │       ├── security/           # JWT infrastructure and RBAC handlers
│   │   │       │   ├── controller/     # AuthController (Login / Register)
│   │   │       │   ├── jwt/            # Token provider, validation, filters
│   │   │       │   ├── rbac/           # Role-Based Access Control logic
│   │   │       │   └── handler/        # Entry point for access-denied handling
│   │   │       └── shared/             # Audit bases, shared annotations/utils
│   │   └── resources/
│   │       ├── db/migration/           # Flyway DB migration SQL scripts (V1__Initial_Schema.sql, etc.)
│   │       ├── templates/              # View/HTML rendering templates
│   │       ├── application.properties   # Shared base properties
│   │       └── application-*.properties # Profile properties (dev, stage, prod, qa)
├── pom.xml                             # Maven build configuration
└── README.md                           # Documentation
```


## Database Configuration

This application uses **Flyway** for database migrations instead of Hibernate's auto DDL generation. This ensures version-controlled, reproducible schema changes across all environments.

### Database Setup

**Prerequisites:**
- PostgreSQL 12+ installed and running
- Database created: `network_movers`

**Connection Details by Profile:**

| Profile | Host | Port | Database | Username | Password |
|---------|------|------|----------|----------|----------|
| dev | localhost | 5432 | network_movers | postgres | 12345 |
| qa | postgres-qa | 5432 | network_movers | postgres | 12345 |
| prod | ${DB_HOST} | ${DB_PORT} | ${DB_NAME} | ${DB_USER} | ${DB_PASSWORD} |
| stage | ${PGHOST} | ${PGPORT} | ${PGDATABASE} | ${PGUSER} | ${PGPASSWORD} |

### Flyway Migrations

**Location:** `src/main/resources/db/migration/`

Flyway automatically executes SQL migrations in version order on application startup.

**Migration Naming Convention:**
- `V<version>__<description>.sql`
- Example: `V1__Initial_Schema.sql`, `V2__Add_Procedures.sql`

**Creating a New Migration:**

1. Create a new SQL file in `src/main/resources/db/migration/`:
   ```sql
   -- V3__Add_New_Table.sql
   CREATE TABLE IF NOT EXISTS new_table (
       id BIGSERIAL PRIMARY KEY,
       name VARCHAR(255) NOT NULL,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   );
   CREATE INDEX idx_new_table_name ON new_table(name);
   ```

2. On next application startup, Flyway will:
   - Execute the migration
   - Record it in `flyway_schema_history` table
   - Skip it on future runs

**Migration Tracking:**
- Flyway maintains a `flyway_schema_history` table with executed migrations
- Each migration record includes: version, description, type, install time, execution time, success status

### Database Directory Structure

**`database/` directory** (Manual reference scripts — NOT auto-executed):
- `migration/` — Historical migration scripts, reference documentation
- `procedure/` — Stored procedure definitions
- `function/` — Database function definitions
- `trigger/` — Trigger definitions
- `seed/` — Test/seed data scripts
- `script/` — Utility scripts (backups, maintenance, etc.)

**`src/main/resources/db/migration/` (Flyway auto-executed):**
- `V1__Initial_Schema.sql` — Initial database schema
- `V2__*.sql` — Incremental changes

### Hibernate Configuration by Profile

| Profile | ddl-auto | Purpose |
|---------|----------|---------|
| dev | validate | Validates schema matches entities |
| qa | validate | Validates schema matches entities |
| stage | validate | Validates schema matches entities |
| prod | validate | Validates schema matches entities |

**Note:** `ddl-auto=validate` ensures Hibernate does NOT modify the schema. Schema changes are managed exclusively by Flyway.

### Running Migrations

**Option 1: Automatic (on application startup)**
```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=dev"
```

**Option 2: Manual Flyway execution**
```bash
mvn flyway:migrate -Dspring.profiles.active=dev
```

**Option 3: Check migration status**
```bash
mvn flyway:info -Dspring.profiles.active=dev
```

### Exporting Current Schema to Flyway

If you have an existing database, export it to Flyway format:

```bash
# Export schema DDL
pg_dump --schema-only -d network_movers -U postgres > schema_dump.sql

# Export data (optional)
pg_dump -d network_movers -U postgres > data_dump.sql
```

Then create a Flyway migration file with the exported schema:
```sql
-- V1__Initial_Schema.sql
-- [paste schema_dump.sql content here]
```

### Troubleshooting

**Migration already applied error:**
- Flyway tracks applied migrations in `flyway_schema_history`
- Do NOT modify or delete this table
- For development only, you can rebuild: `mvn flyway:clean flyway:migrate`

**Schema validation error:**
- Error: "Schema does not match entities"
- Solution: Update Flyway migration or JPA entity definitions to match
- Run: `mvn spring-boot:run` to see full error details

**Baseline migrations (existing database):**
- If importing existing database: `spring.flyway.baseline-on-migrate=true`
- This allows Flyway to work with pre-existing schemas
