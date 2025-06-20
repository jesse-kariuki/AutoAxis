package com.example.autoaxis.entities;

public class AuthManager {

    // Sign up new user
    public boolean signUp(String username, String email, String password, String role) {
        // Check if user/email already exists
        if (User.getUserByEmail(email) != null) {
            System.out.println("Email already registered.");
            return false;
        }

        // Create user object based on role
        User newUser;
        switch (role.toLowerCase()) {
            case "admin":
                newUser = new Admin(username, email, password, role.toUpperCase());
                break;
            case "renter":
                newUser = new Renter(username, email, password, role.toUpperCase());
                break;
            case "buyer":
                newUser = new Buyer(username, email, password, role.toUpperCase());
                break;
            default:
                System.out.println("Invalid role specified.");
                return false;
        }

        // Save user to DB
        boolean saved = newUser.saveToDatabase();
        if (saved) {
            System.out.println("User signed up successfully.");
            return true;
        } else {
            System.out.println("Failed to sign up user.");
            return false;
        }
    }

    // Login user by email and password, returns User object or null
    public User login(String email, String password) {
        User user = User.getUserByEmail(email);

        if (user == null) {
            System.out.println("No user found with that email.");
            return null;
        }

        if (user.getPassword().equals(password)) {
            System.out.println("Login successful.");
            return user;
        } else {
            System.out.println("Incorrect password.");
            return null;
        }
    }

    // Check if user is admin
    public boolean isAdmin(User user) {
        return user != null && "ADMIN".equalsIgnoreCase(user.getRole());
    }



}
