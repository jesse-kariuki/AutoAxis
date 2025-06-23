package com.example.autoaxis.dao;

import com.example.autoaxis.controllers.AppContext;
import com.example.autoaxis.dto.CarModel;
import com.example.autoaxis.entities.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {




    public List<Car> getAllCars() throws SQLException {
        if (AppContext.DB == null || AppContext.DB.isClosed()) {
            throw new SQLException("Database connection is not available");
        }
        List<Car> cars = new ArrayList<>();
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
}

