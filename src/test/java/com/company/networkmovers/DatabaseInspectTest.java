package com.company.networkmovers;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseInspectTest {

    @Test
    public void inspectDatabase() {
        System.out.println("=== START DATABASE INSPECTION ===");
        
        // Check if Flyway class is on classpath
        try {
            Class<?> flywayClass = Class.forName("org.flywaydb.core.Flyway");
            System.out.println("Flyway class found on classpath: " + flywayClass.getName());
        } catch (ClassNotFoundException e) {
            System.err.println("Flyway class NOT found on classpath: " + e.getMessage());
        }

        String url = "jdbc:postgresql://localhost:5432/network_movers";
        String user = "postgres";
        String password = "12345";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Connected to PostgreSQL successfully!");

            // Try manual Flyway migration
            try {
                System.out.println("Attempting manual programmatic Flyway migration...");
                org.flywaydb.core.Flyway flyway = org.flywaydb.core.Flyway.configure()
                        .dataSource(url, user, password)
                        .locations("classpath:db/migration")
                        .baselineOnMigrate(true)
                        .load();
                int migrations = flyway.migrate().migrationsExecuted;
                System.out.println("Programmatic Flyway migration executed: " + migrations + " migrations.");
            } catch (Throwable t) {
                System.err.println("Manual programmatic Flyway migration failed: " + t.getMessage());
                t.printStackTrace();
            }

            // 1. List all tables in public schema
            System.out.println("\n--- TABLES IN DB ---");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public'")) {
                while (rs.next()) {
                    System.out.println("Table: " + rs.getString("table_name"));
                }
            }

            // 2. Query flyway_schema_history
            System.out.println("\n--- FLYWAY SCHEMA HISTORY ---");
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success FROM flyway_schema_history ORDER BY installed_rank")) {
                while (rs.next()) {
                    System.out.printf("Rank: %d, Version: %s, Desc: %s, Script: %s, Success: %b%n",
                            rs.getInt("installed_rank"),
                            rs.getString("version"),
                            rs.getString("description"),
                            rs.getString("script"),
                            rs.getBoolean("success")
                    );
                }
            } catch (Exception e) {
                System.out.println("Error querying flyway_schema_history: " + e.getMessage());
            }

        } catch (Exception e) {
            System.err.println("Database inspection failed: " + e.getMessage());
            e.printStackTrace();
        }
        System.out.println("=== END DATABASE INSPECTION ===");
    }
}
