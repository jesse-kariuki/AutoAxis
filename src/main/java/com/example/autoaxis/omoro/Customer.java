package com.example.autoaxis.omoro;

public class Customer {
    private int id;
    private int userId;
    private String driverLicense;
    private String phone;
    private int loyaltyPoints;

    // Constructors
    public Customer() {}

    public Customer(int userId, String driverLicense, String phone) {
        this.userId = userId;
        this.driverLicense = driverLicense;
        this.phone = phone;
        this.loyaltyPoints = 0;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDriverLicense() {
        return driverLicense;
    }

    public void setDriverLicense(String driverLicense) {
        this.driverLicense = driverLicense;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public void addLoyaltyPoints(int points) {
        this.loyaltyPoints += points;
    }
}

