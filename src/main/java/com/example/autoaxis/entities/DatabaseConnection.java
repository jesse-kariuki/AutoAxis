package com.example.autoaxis.entities;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String DB_HOST = System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";
    private static final String DB_PORT = System.getenv("DB_PORT") != null ? System.getenv("DB_PORT") : "3306";
    private static final String DB_NAME = System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : "autoaxis";
    private static final String DB_USER = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root"; // Default for local testing
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "Passwords$uck0"; // Default for local testing

    private static Connection connection = null;


    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.err.println("MySQL JDBC Driver not found.");
                e.printStackTrace();
                throw new SQLException("MySQL JDBC Driver not found.", e);
            }

            // Construct the URL using the environment variables
            String URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME + "?useSSL=false&allowPublicKeyRetrieval=true";

            System.out.println("Connecting to DB at: jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME);
            System.out.println("Username: " + DB_USER);
            System.out.println("Password: " + DB_PASSWORD);

            connection = DriverManager.getConnection(URL, DB_USER, DB_PASSWORD);
            System.out.println("Database connection established: " + URL); // For debugging
        }
        return connection;
    }

}

