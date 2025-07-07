package com.example.autoaxis.dao;

import com.example.autoaxis.controllers.AppContext;
import com.example.autoaxis.dto.Bookings;
import com.example.autoaxis.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookingDAO {

    private List<Bookings> bookings;

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
            stmt.setString(1, booking.getBookingId());
            stmt.setString(2, booking.getCustomerId());
            stmt.setString(3, booking.getCarId());
            stmt.setDate(4, java.sql.Date.valueOf(booking.getStartDate()));
            stmt.setDate(5, java.sql.Date.valueOf(booking.getEndDate()));
            stmt.setDouble(6, booking.getTotalPrice());
            stmt.executeUpdate();
        }
    }

}
