package com.example.autoaxis.entities;

import com.example.autoaxis.dto.CarModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Car {
    private int id;
    private String name;
    private String model;
    private int year;
    private double price;
    private String type; // "rent" or "buy"
    private boolean isAvailable;
    private String description;
    private int seats;
    private String imageUrl;
    private String transmission;


    private CarModel carModel;




    public Car(int id, String name, String model, int year, double price, String type, boolean isAvailable, String description, int seats, String imageUrl, String transmission) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.year = year;
        this.price = price;
        this.type = type;
        this.isAvailable = isAvailable;
        this.description = description;
        this.seats= seats;
        this.imageUrl = imageUrl;
    }

    public int getSeats() {
        return seats;
    }

    public String getImageUrl() {
        return  this.imageUrl;
    }

    public String getTransmission() {
        return transmission;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public Car(int id,String name, String model, int year, double price, String type, boolean isAvailable, String description, int seats, String imageUrl, String transmission, CarModel carModel) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.year = year;
        this.price = price;
        this.type = type;
        this.isAvailable = isAvailable;
        this.description = description;
        this.seats = seats;
        this.imageUrl = imageUrl;
        this.transmission = transmission;
        this.carModel = carModel;
    }

    // Constructor without ID (for adding new cars)
    public Car(String name, String model, int year, double price, String type, boolean isAvailable, String description) {
        this.name = name;
        this.model = model;
        this.year = year;
        this.price = price;
        this.type = type;
        this.isAvailable = isAvailable;
        this.description = description;
    }

    // Constructor with ID (for cars retrieved from DB)
    public Car(int id, String name, String model, int year, double price, String type, boolean isAvailable, String description) {
        this(name, model, year, price, type, isAvailable, description);
        this.id = id;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public int getYear() { return year; }
    public void setYear(int year) { this.year = year; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    // Save car to database
    public boolean saveToDatabase() {
        String sql = "INSERT INTO cars (make, model, year, price, type, is_available, description) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setString(2, model);
            stmt.setInt(3, year);
            stmt.setDouble(4, price);
            stmt.setString(5, type);
            stmt.setBoolean(6, isAvailable);
            stmt.setString(7, description);

            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error saving car: " + e.getMessage());
            return false;
        }
    }

    // Load car by ID
    public static Car getCarById(int carId) {
        String sql = "SELECT * FROM cars WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Car(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("type"),
                        rs.getBoolean("is_available"),
                        rs.getString("description")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error loading car: " + e.getMessage());
        }

        return null;
    }



    @Override
    public String toString() {
        return "Car{id=" + id + ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", type='" + type + '\'' +
                ", isAvailable=" + isAvailable +
                ", description='" + description + '\'' +
                '}';
    }
}
