package com.example.autoaxis.entities;

import com.example.autoaxis.controllers.AppContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User {
    protected int id;
    protected String username;
    protected String email;
    protected String password;
    protected String role;
    protected String phone;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = "renter"; // Default role if not specified in constructor
        this.phone = null;
    }



    public User(int id, String username, String email, String password, String phone) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }


    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = "renter"; // Default role if not specified in constructor

    }

    public User(int id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String username, String email, String password, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role.toLowerCase(); // Ensure role is stored in lowercase
    }


    public String getPhone() {
        return phone;
    }

    public int getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getRole() { return role; }

    public void setId(int id) { this.id = id; }
    public void setUsername(String username) { this.username = username; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setRole(String role) { this.role = role; }

    /* public boolean saveToDatabase() {
        String sql = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, role.toLowerCase());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
            return false;
        }
    }  */


    public boolean saveToDatabase() {
        if ("admin".equalsIgnoreCase(role)) {
            System.out.println("You cannot register as an admin.");
            return false;
        }

        String sql = "INSERT INTO users (username, email, password, role) VALUES (?, ?, ?, ?)";

        // IMPORTANT: Do NOT put AppContext.DB in try-with-resources here, as it's your single static connection.
        // Only PreparedStatement should be in try-with-resources.
        try (PreparedStatement stmt = AppContext.DB.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, email);
            stmt.setString(3, password);
            stmt.setString(4, role.toLowerCase());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("Error saving user: " + e.getMessage());
            e.printStackTrace(); // Added for better debugging
            return false;
        }
    }



    public static User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        // IMPORTANT: Do NOT put AppContext.DB in try-with-resources here.
        // Only PreparedStatement and ResultSet should be in try-with-resources.
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = AppContext.DB.prepareStatement(sql); // Use the static connection directly
            stmt.setString(1, email);
            rs = stmt.executeQuery();

            if (rs.next()) {
                String role = rs.getString("role");

                AppContext.role = role.toLowerCase();


                switch (role.toLowerCase()) {
                    case "admin":
                        return new Admin(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                role
                        );
                    case "buyer":
                        return new Buyer(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                role
                        );
                    case "renter":
                        return new Renter(
                                rs.getInt("id"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getString("password"),
                                role
                        );
                    default:
                        System.out.println("Unknown role found for user: " + role);
                        return null;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving user by email: " + e.getMessage());
            e.printStackTrace(); // Added for better debugging
        } finally {
            // Manually close PreparedStatement and ResultSet in a finally block
            try {
                if (rs != null) rs.close();
            } catch (SQLException e) {
                System.out.println("Error closing ResultSet: " + e.getMessage());
            }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing PreparedStatement: " + e.getMessage());
            }
        }

        return null;
    }


    @Override
    public String toString() {
        return "User{id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
