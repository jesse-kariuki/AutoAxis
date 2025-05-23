package com.example.autoaxis.entities;

public class Renter extends User {

    private int rentalPeriod;

    public Renter(int id, String username, String email, String password, String role) {
        super(id, username, email, password, role);
    }

    public Renter(String username, String email, String password, String role) {
        super(username, email, password, role);
    }
    public int getRentalPeriod() {
        return rentalPeriod;
    }
    public void setRentalPeriod(int rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }
    public void rentCar() {
        System.out.println(getUsername() + " rented a car for " + rentalPeriod + " days.");
    }
    public void viewRentalHistory() {
        System.out.println(getUsername() + "'s rental history");

    }
}
