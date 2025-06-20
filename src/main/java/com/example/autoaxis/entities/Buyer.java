package com.example.autoaxis.entities;

public class Buyer extends User {


    public Buyer(int id, String username, String email, String password, String role) {
        super(id, username, email, password, role);
    }

    public Buyer(String username, String email, String password, String role) {
        super(username, email, password, role);
    }

    public void makePurchase() {
        System.out.println(getUsername() + " made a purchase.");

    }

    public void viewPurchaseHistory() {
        System.out.println(getUsername() + "'s purchase history");

    }
}
