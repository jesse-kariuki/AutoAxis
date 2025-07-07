package com.example.autoaxis.entities;

public class Admin extends User {
    //usage of Liskov Substitution Principle (LSP) - Admin is a User and can be used wherever a User is expected
    // Admin class extends User class and inherits its properties and methods
    public Admin(String username, String email, String password, String role) {
        super(username, email, password, role);
    }
    public Admin(int id, String username, String email, String password, String role) {
        super(id, username, email, password, role);
    }


    public void addCar() {
        System.out.println(getUsername() + " added a car to inventory.");

    }

    public void removeCar() {
        System.out.println(getUsername() + " removed a car from inventory.");

    }

    public void manageUsers() {
        System.out.println(getUsername() + " managing users.");

    }
}
