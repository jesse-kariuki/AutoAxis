package com.example.autoaxis.dao;

//SINGLE RESPONSIBILITY PRINCIPLE (SRP) - CarDAO is responsible for data access operations related to Car entities
// IT HAS ONE TASK ONLY - TO HANDLE DATABASE OPERATIONS FOR CARS
import com.example.autoaxis.controllers.AppContext;
import com.example.autoaxis.dto.CarModel;
import com.example.autoaxis.entities.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO implements CarReader, CarWriter{



    private ArrayList<Car> cars;

    @Override
    public List<Car> getAllCars() throws SQLException {
        if (AppContext.DB == null || AppContext.DB.isClosed()) {
            throw new SQLException("Database connection is not available");
        }
        cars = new ArrayList<>();
        String sql = "SELECT * FROM car";
        try (PreparedStatement stmt = AppContext.DB.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                cars.add(new Car(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("model"),
                        rs.getInt("year"),
                        rs.getDouble("price"),
                        rs.getString("type"),
                        rs.getBoolean("is_available"),
                        rs.getString("description"),
                        rs.getInt("seats"),
                        rs.getString("image_url"),
                        rs.getString("transmission")
                ));
            }
        }
        return cars;
    }


    @Override
    public List<CarModel> getDisplayCars() {
        List<CarModel> carModels = new ArrayList<>();
        for (Car car : this.cars) {
            carModels.add(new CarModel(
                    car.getId(),
                    car.getFullName(),
                    car.getType(),
                    car.getIsAvailable(),
                    car.getPrice()

            ));
        }
        return carModels;
    }

    @Override
    public boolean insertCar(Car car) throws SQLException {
        if (AppContext.DB == null || AppContext.DB.isClosed()) {
            throw new SQLException("Database connection is not available");
        }
        String sql = "INSERT INTO car (name, model, year, price, type, is_available, description, seats, image_url, transmission) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
             PreparedStatement stmt = AppContext.DB.prepareStatement(sql)) {


            stmt.setString(1, car.getName());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setDouble(4, car.getPrice());
            stmt.setString(5, car.getType());
            stmt.setBoolean(6, car.isAvailable());
            stmt.setString(7, car.getDescription());
            stmt.setInt(8, car.getSeats());
            stmt.setString(9, car.getImageUrl());
            stmt.setString(10, car.getTransmission());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}

