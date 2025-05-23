package com.example.autoaxis.entities;


public class main {
    public static void main(String[] args) {
        // 1. Register a user
        User buyer = new Buyer("john", "john@example.com", "1234", "buyer");
        boolean saved = buyer.saveToDatabase();
        System.out.println("User saved: " + saved);

        // 2. Login
        String email = "john@example.com";
        String password = "1234";

        AuthManager auth = new AuthManager();
        User user = auth.login(email, password);

        if (user != null) {
            System.out.println("Login successful: " + user);
        } else {
            System.out.println("Login failed.");
        }

        // 3. Add a car
        CarManager carManager = new CarManager();
        Car car = new Car("Toyota", "Corolla", 2020, 15000.0, "Sedan", true, "Reliable and fuel-efficient");

        boolean success = carManager.addCar(car);

        if (success) {
            System.out.println("Car added successfully.");
        } else {
            System.out.println("Failed to add car.");
        }
    }
}
