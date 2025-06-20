package com.example.autoaxis.omoro;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password; // Will be hashed
    private String role;
    private boolean isGoogleAuth;

    // Constructors
    public User() {}

    public User(String firstName, String lastName, String email, String password, String role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isGoogleAuth = false;
    }

    // Google Auth constructor
    public User(String firstName, String lastName, String email, String role) {
        this(firstName, lastName, email, null, role);
        this.isGoogleAuth = true;
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public boolean isGoogleAuth() { return isGoogleAuth; }
    public void setGoogleAuth(boolean googleAuth) { isGoogleAuth = googleAuth; }
}

