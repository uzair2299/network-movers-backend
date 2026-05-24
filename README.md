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


## Dynamic Navigation & Sidebar Workflow

To manage the comprehensive enterprise modular sidebar and other layouts (Sidebar, Topbar, Profile), the system uses a scalable, recursive, database-driven navigation design that filters visibility dynamically based on user permissions and UI layout placement.

### Classifications
Every menu category, subsection, and clickable page is stored in a single table `sec_menu_items` using a self-referencing relationship:
- **Modules (Level 1 - Categories)**: Form the main sections in the layout. Defined by having `parent_id = NULL`.
- **Menus (Level 2 - Headers)**: Form the collapsible headers under a module. `parent_id` references a Level 1 module.
- **Submenus (Level 3 - Links)**: Clickable leaf links leading to frontend routes. `parent_id` references a Level 2 menu.
- **Layout Sections**: Distinguishes where the menu should be rendered:
  - `SIDEBAR`: The main sidebar navigation tree.
  - `TOPBAR`: The quick utility action links on the top menu header.
  - `PROFILE`: Settings and utilities (e.g., Change Password, Logout) in the user's avatar dropdown.

### Relational Schema (DDL)
```sql
CREATE TABLE IF NOT EXISTS sec_menu_items (
    id            BIGSERIAL      PRIMARY KEY,
    name          VARCHAR(255)   NOT NULL,
    icon          VARCHAR(255),
    path          VARCHAR(255),
    section       VARCHAR(50)    NOT NULL DEFAULT 'SIDEBAR',
    parent_id     BIGINT,
    sort_order    INT            NOT NULL DEFAULT 0,
    permission_id BIGINT,
    active        BOOLEAN        NOT NULL DEFAULT TRUE,
    CONSTRAINT fk_menu_parent     FOREIGN KEY (parent_id)     REFERENCES sec_menu_items(id) ON DELETE CASCADE,
    CONSTRAINT fk_menu_permission FOREIGN KEY (permission_id) REFERENCES sec_permissions(id) ON DELETE SET NULL,
    CONSTRAINT chk_menu_section   CHECK (section IN ('SIDEBAR', 'TOPBAR', 'PROFILE'))
);

CREATE INDEX idx_menu_parent ON sec_menu_items(parent_id);
CREATE INDEX idx_menu_section_active ON sec_menu_items(section, active, sort_order);
```

### Dynamic Tree Processing
1. **Fetch Flat Permitted Items**: On calling `/api/v1/navigation`, the server gets the user permissions and filters items by layout section:
   - **Filtered by Query Parameter** (`GET /api/v1/navigation?section=SIDEBAR`):
     ```sql
     SELECT DISTINCT m FROM MenuItem m 
     LEFT JOIN FETCH m.permission p 
     LEFT JOIN FETCH m.parent 
     WHERE m.active = true 
       AND m.section = :section
       AND (p IS NULL OR p.name IN :permissions) 
     ORDER BY m.sortOrder ASC
     ```
   - **Grouped Layouts** (when `section` is omitted): Retrieves all permitted items and groups them into a JSON object containing keys `SIDEBAR`, `TOPBAR`, and `PROFILE`.
2. **Build Hierarchy Tree**: The `NavigationService` processes the flat database results into a hierarchical JSON response by nesting child items into their respective parent `children` arrays, sorting them in memory by `sortOrder` to guarantee layout stability.
3. **Permission Cascading**: 
   - If a user lacks access to a Level 1 Module (e.g. *Administration*), the module and all its nested sub-items are excluded.
   - If they have access to the parent module but lack permission for a specific submenu link (e.g. *HR Reports*), only that leaf node is removed.

### JSON Payload Schema (Example)
```json
{
  "SIDEBAR": [
    {
      "id": 1,
      "name": "CRM",
      "icon": "users-icon",
      "path": "/crm",
      "sortOrder": 20,
      "children": [
        {
          "id": 2,
          "name": "Leads",
          "icon": "funnel-icon",
          "path": "/crm/leads",
          "sortOrder": 10,
          "children": [
            {
              "id": 3,
              "name": "All Leads",
              "icon": "list-icon",
              "path": "/crm/leads/all",
              "sortOrder": 10,
              "children": []
            }
          ]
        }
      ]
    }
  ],
  "TOPBAR": [
    {
      "id": 101,
      "name": "Quick Estimation",
      "icon": "calculator",
      "path": "/estimate/quick",
      "sortOrder": 10,
      "children": []
    }
  ],
  "PROFILE": [
    {
      "id": 201,
      "name": "My Profile",
      "icon": "user-avatar",
      "path": "/user/profile",
      "sortOrder": 10,
      "children": []
    }
  ]
}
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

### Resetting / Dropping Database

If you need to force-drop the database (e.g. `railway` database) when active connection pools prevent dropping it:

```sql
-- STEP 1 : Connect to another DB first

-- STEP 2: terminate sessions (stronger version)
SELECT pg_terminate_backend(pg_stat_activity.pid)
FROM pg_stat_activity
WHERE datname = 'railway'
  AND pid <> pg_backend_pid();


-- STEP 3: drop
DROP DATABASE railway;
```
