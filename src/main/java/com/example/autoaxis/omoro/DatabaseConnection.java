package com.example.autoaxis.omoro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/car_sales_platform";
    private static final String USER = "root";
    private static final String PASS = "";

    static {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    // New method to test the database connection
    public static void testConnection() {
        System.out.println("Testing database connection...");

        try (Connection conn = getConnection()) {
            if (conn != null) {
                System.out.println("Connection successful!");
                System.out.println("Database: " + conn.getCatalog());
                System.out.println("JDBC Driver: " + conn.getMetaData().getDriverVersion());
            } else {
                System.out.println("Connection failed!");
            }
        } catch (SQLException e) {
            System.err.println(" Connection error:");
            System.err.println("SQL State: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Main method
    public static void main(String[] args) {
        testConnection();
    }
}
