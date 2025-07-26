package com.louisklein.javatraining.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void initialize() throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement()) {

            String createStudentsTable = """
                CREATE TABLE IF NOT EXISTS students (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255),
                    age INT,
                    email VARCHAR(255),
                    grade INT
                );
            """;

            String createTeachersTable = """
                CREATE TABLE IF NOT EXISTS teachers (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255),
                    age INT,
                    email VARCHAR(255),
                    subject VARCHAR(255)
                );
            """;

            stmt.execute(createStudentsTable);
            stmt.execute(createTeachersTable);
        }
    }
}
