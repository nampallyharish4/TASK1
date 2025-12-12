package com.codegnan.Task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Responsible for creating the database and tables if not present and providing connections.
 */
public class DatabaseConfig {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "Task1";
    private static final String URL_WITH_DB = BASE_URL + DB_NAME + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String URL_BASE = BASE_URL + "?serverTimezone=UTC&allowPublicKeyRetrieval=true&useSSL=false";
    private static final String USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
    private static final String PASS = System.getenv("DB_PASS") != null ? System.getenv("DB_PASS") : "root";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL driver not found on classpath. Ensure mysql-connector-j-*.jar is in the classpath (e.g., lib folder).", e);
        }
    }

    public static void init() {
        try (Connection conn = DriverManager.getConnection(URL_BASE, USER, PASS);
             Statement st = conn.createStatement()) {
            st.execute("CREATE DATABASE IF NOT EXISTS " + DB_NAME + " CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create database: " + e.getMessage(), e);
        }

        // create tables
        try (Connection conn = getConnection(); Statement st = conn.createStatement()) {
            String createCoffee = "CREATE TABLE IF NOT EXISTS coffee_shop (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "name VARCHAR(100) NOT NULL, " +
                    "price DECIMAL(10,2) NOT NULL, " +
                    "description TEXT)";
            st.execute(createCoffee);

            String createLog = "CREATE TABLE IF NOT EXISTS update_log (" +
                    "log_id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "coffee_id INT NOT NULL, " +
                    "column_name VARCHAR(100) NOT NULL, " +
                    "old_value TEXT, " +
                    "new_value TEXT, " +
                    "updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY (coffee_id) REFERENCES coffee_shop(id) ON DELETE CASCADE)";
            st.execute(createLog);
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create tables: " + e.getMessage(), e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL_WITH_DB, USER, PASS);
    }
}
