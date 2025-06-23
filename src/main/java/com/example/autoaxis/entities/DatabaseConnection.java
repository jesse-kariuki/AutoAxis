package com.example.autoaxis.entities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/autoaxis";
    private static final String USER = "root";
    private static final String PASSWORD = "Passwords$uck0";

    private static Connection connection = null;

    // Get connection instance
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                // Load the MySQL JDBC driver (optional since JDBC 4.0, but good practice)
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
            }

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        }
        return connection;
    }

}

