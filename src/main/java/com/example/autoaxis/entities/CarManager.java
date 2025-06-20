package com.example.autoaxis.entities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarManager {

    // Add a new car
    public boolean addCar(Car car) {
        return car.saveToDatabase();
    }

    // Delete car by ID
    public boolean deleteCar(int carId) {
        String sql = "DELETE FROM cars WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, carId);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting car: " + e.getMessage());
            return false;
        }
    }

    // Update car availability
    public boolean updateAvailability(int carId, boolean isAvailable) {
        String sql = "UPDATE cars SET is_available = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setBoolean(1, isAvailable);
            stmt.setInt(2, carId);
            int rows = stmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            System.out.println("Error updating availability: " + e.getMessage());
            return false;
        }
    }

    // Get all available cars
    public List<Car> getAvailableCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE is_available = true";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("id"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("type"),
                        rs.getBoolean("is_available"),
                        rs.getString("description")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving available cars: " + e.getMessage());
        }

        return cars;
    }

    // Search cars by make or model
    public List<Car> searchCars(String keyword) {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE make LIKE ? OR model LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, "%" + keyword + "%");
            stmt.setString(2, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("id"),
                        rs.getString("make"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("type"),
                        rs.getBoolean("is_available"),
                        rs.getString("description")
                ));
            }

        } catch (SQLException e) {
            System.out.println("Error searching cars: " + e.getMessage());
        }

        return cars;
    }
}
