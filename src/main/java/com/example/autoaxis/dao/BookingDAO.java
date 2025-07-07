package com.example.autoaxis.dao;

import com.example.autoaxis.controllers.AppContext;
import com.example.autoaxis.dto.Bookings;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BookingDAO {

    private static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    private List<Bookings> bookings = new ArrayList<Bookings>();

    public List<Bookings> getAllBookings() throws SQLException {
        if (AppContext.DB == null || AppContext.DB.isClosed()) {
            throw new SQLException("Database connection is not available");
        }

        String sql = "SELECT * FROM bookings";
        try (PreparedStatement stmt = AppContext.DB.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                bookings.add(new Bookings(
                        rs.getString("booking_id"),
                        rs.getString("customer_id"),
                        rs.getString("vehicle_id"),
                        rs.getDate("pickup_date").toLocalDate().toString(),
                        rs.getDate("return_date").toLocalDate().toString(),
                        rs.getDouble("total_amount")
                ));
            }
        }
        return bookings;
    }

    public void addBooking(Bookings booking) throws SQLException {
        if (AppContext.DB == null || AppContext.DB.isClosed()) {
            throw new SQLException("Database connection is not available");
        }

        String sql = "INSERT INTO bookings (booking_id, customer_id, vehicle_id, pickup_date, return_date, total_amount) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = AppContext.DB.prepareStatement(sql)) {
            stmt.setString(1, booking.getBooking_id());
            stmt.setString(2, booking.getCustomer_id());
            stmt.setString(3, booking.getVehicle_id());
            java.sql.Date sqlStartDate = null;
            String startDateStr = booking.getPickup_date();
            if (startDateStr != null && !startDateStr.trim().isEmpty()) {
                try {
                    // Parse the string using the defined formatter
                    LocalDate localStartDate = LocalDate.parse(startDateStr, INPUT_DATE_FORMATTER);
                    sqlStartDate = Date.valueOf(localStartDate);
                } catch (DateTimeParseException e) {
                    System.err.println("Error parsing start date string '" + startDateStr + "': " + e.getMessage());
                    throw new SQLException("Invalid start date format. Expected 'MMM dd, yyyy'.", e);
                }
            }
            stmt.setDate(4, sqlStartDate); // Set the date parameter

            java.sql.Date sqlEndDate = null;
            String endDateStr = booking.getReturn_date();
            if (endDateStr != null && !endDateStr.trim().isEmpty()) {
                try {
                    // Parse the string using the defined formatter
                    LocalDate localEndDate = LocalDate.parse(endDateStr, INPUT_DATE_FORMATTER);
                    sqlEndDate = Date.valueOf(localEndDate);
                } catch (DateTimeParseException e) {
                    System.err.println("Error parsing end date string '" + endDateStr + "': " + e.getMessage());
                    throw new SQLException("Invalid end date format. Expected 'MMM dd, yyyy'.", e);
                }
            }
            stmt.setDate(5, sqlEndDate);
            stmt.setDouble(6, booking.getTotal_amount());
            stmt.executeUpdate();
        }
    }

}
