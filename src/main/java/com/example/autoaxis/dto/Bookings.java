package com.example.autoaxis.dto;

import com.example.autoaxis.controllers.AppContext;
import com.example.autoaxis.entities.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Bookings {

    private String bookingId;
    private String customerId;
    private String carId;
    private String startDate;
    private String endDate;
    private Double totalPrice;

    public Bookings(String bookingId, String customerId, String carId, String startDate, String endDate, Double totalPrice) {
        this.bookingId = bookingId;
        this.customerId = customerId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
    }

    public String getBookingId() {
        return bookingId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getCarId() {
        return carId;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }
}
