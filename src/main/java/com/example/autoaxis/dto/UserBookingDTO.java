package com.example.autoaxis.dto;

// No longer need LocalDate or other specific booking imports here

public class UserBookingDTO {
    private String userId;
    private String username;
    private String email;
    private String bookingId; // Only bookingId is kept

    public UserBookingDTO(String userId, String username, String email, String bookingId) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.bookingId = bookingId;
    }

    // Getters for all fields
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getBookingId() {
        return bookingId;
    }

    // You might also want setters if you modify these objects after creation.
}