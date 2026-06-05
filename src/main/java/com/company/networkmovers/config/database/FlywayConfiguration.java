package com.company.networkmovers.config.database;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.FlywayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayMigrationStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class FlywayConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(FlywayConfiguration.class);

    @Bean
    @Profile({"dev", "stage"})
    public FlywayMigrationStrategy cleanOnValidationErrorStrategy() {
        return flyway -> {
            try {
                logger.info("Validating Flyway database migrations...");
                flyway.validate();
                logger.info("Flyway validation succeeded.");
            } catch (FlywayException e) {
                logger.warn("Flyway validation failed: {}. Database clean-on-validation-error is active. Cleaning database...", e.getMessage());
                try {
                    flyway.clean();
                    logger.info("Database cleaned successfully.");
                } catch (Exception cleanEx) {
                    logger.error("Failed to clean database: {}", cleanEx.getMessage(), cleanEx);
                    throw cleanEx;
                }
            }
            logger.info("Running Flyway migrations...");
            flyway.migrate();
            logger.info("Flyway migrations executed successfully.");
        };
    }
}
