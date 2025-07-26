package com.louisklein.javatraining.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {}

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection("jdbc:h2:./studentdb", "sa", "");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
