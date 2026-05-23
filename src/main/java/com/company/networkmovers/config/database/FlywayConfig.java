package com.company.networkmovers.config.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * Configuration to ensure Flyway migrations run before Hibernate schema validation.
 * 
 * By marking JpaAuditingConfig to depend on Flyway, we ensure the correct initialization order:
 * 1. DataSource is created
 * 2. Flyway bean is initialized and migrations are executed
 * 3. JPA/Hibernate EntityManagerFactory is created and validates the schema
 * 
 * This prevents Hibernate validation errors for tables created by Flyway migrations.
 */
@Configuration
@DependsOn("flyway")
public class FlywayConfig {
}
