package com.example.autoaxis.dao;

import com.example.autoaxis.controllers.AppContext;
import com.example.autoaxis.dto.UserBookingDTO;
import com.example.autoaxis.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// SINGLE RESPONSIBILITY PRINCIPLE (SRP) - UserDAO is responsible for data access operations related to User entities
// IT HAS ONE TASK ONLY - TO HANDLE DATABASE OPERATIONS FOR USERS
public class UserDAO {

    private List<User> users = new ArrayList<>();

    public List<User> getAllUsers() throws SQLException {
        if (AppContext.DB == null || AppContext.DB.isClosed()) {
            throw new SQLException("Database connection is not available");
        }

        String sql = "SELECT * FROM users";
        try (PreparedStatement stmt = AppContext.DB.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("password")

                ));
            }
        }
        return users;
    }

    public List<UserBookingDTO> getUsersAndBooking() throws SQLException {
        if (AppContext.DB == null || AppContext.DB.isClosed()) {
            throw new SQLException("Database connection is not available");
        }

        List<UserBookingDTO> userBookings = new ArrayList<>();
        // SQL JOIN query to link users and bookings tables, selecting only booking_id
        String sql = "SELECT " +
                "u.id AS user_id, u.username, u.email, " +
                "b.booking_id " + // Only booking_id from bookings table
                "FROM users u " +
                "JOIN bookings b ON u.id = b.customer_id " +
                "ORDER BY u.username, b.booking_id"; // Order results for better readability

        try (PreparedStatement stmt = AppContext.DB.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                userBookings.add(new UserBookingDTO(
                        rs.getString("user_id"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("booking_id")
                ));
            }
        }
        return userBookings;
    }




}
