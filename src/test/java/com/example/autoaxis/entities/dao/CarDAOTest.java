package com.example.autoaxis.entities.dao;

import com.example.autoaxis.controllers.AppContext;
import com.example.autoaxis.dao.CarDAO;
import com.example.autoaxis.dto.CarModel;
import com.example.autoaxis.entities.Car;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CarDAOTest {

    private CarDAO carDAO;
    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        carDAO = new CarDAO();
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        AppContext.DB = mockConnection;

        when(mockConnection.isClosed()).thenReturn(false);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
    }

    @Test
    void testGetAllCars_returnsCarsList() throws Exception {
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false); // One row
        when(mockResultSet.getInt("id")).thenReturn(1);
        when(mockResultSet.getString("name")).thenReturn("Toyota");
        when(mockResultSet.getString("model")).thenReturn("Corolla");
        when(mockResultSet.getInt("year")).thenReturn(2022);
        when(mockResultSet.getDouble("price")).thenReturn(25000.0);
        when(mockResultSet.getString("type")).thenReturn("Sedan");
        when(mockResultSet.getBoolean("is_available")).thenReturn(true);
        when(mockResultSet.getString("description")).thenReturn("Fuel efficient");
        when(mockResultSet.getInt("seats")).thenReturn(5);
        when(mockResultSet.getString("image_url")).thenReturn("toyota.jpg");
        when(mockResultSet.getString("transmission")).thenReturn("Automatic");

        List<Car> result = carDAO.getAllCars();

        assertEquals(1, result.size());
        Car car = result.get(0);
        assertEquals("Toyota", car.getName());
        assertEquals("Corolla", car.getModel());
        assertTrue(car.getIsAvailable());
    }

    @Test
    void testInsertCar_successfulInsert() throws Exception {
        Car car = new Car(1, "Mazda", "CX-5", 2023, 30000.0, "SUV", true,
                "Nice SUV", 5, "mazda.jpg", "Automatic");

        when(mockStatement.executeUpdate()).thenReturn(1);

        boolean success = carDAO.insertCar(car);

        assertTrue(success);
        verify(mockStatement, times(1)).setString(1, "Mazda");
        verify(mockStatement, times(1)).setString(2, "CX-5");
        verify(mockStatement, times(1)).setInt(3, 2023);
        verify(mockStatement, times(1)).setDouble(4, 30000.0);
        verify(mockStatement, times(1)).setString(5, "SUV");
        verify(mockStatement, times(1)).setBoolean(6, true);
        verify(mockStatement, times(1)).setString(7, "Nice SUV");
        verify(mockStatement, times(1)).setInt(8, 5);
        verify(mockStatement, times(1)).setString(9, "mazda.jpg");
        verify(mockStatement, times(1)).setString(10, "Automatic");
        verify(mockStatement, times(1)).executeUpdate();
    }

    @Test
    void testInsertCar_returnsFalseOnSQLException() throws Exception {
        Car car = new Car(1, "ErrorCar", "Broken", 2020, 1000.0, "Trash", false,
                "Will fail", 4, "", "");

        when(mockStatement.executeUpdate()).thenThrow(new SQLException("DB error"));

        boolean success = carDAO.insertCar(car);

        assertFalse(success);
    }

    @Test
    void testGetDisplayCars_transformsCorrectly() throws Exception {
        // Setup: call getAllCars() first to fill `cars` field
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getInt("id")).thenReturn(7);
        when(mockResultSet.getString("name")).thenReturn("BMW");
        when(mockResultSet.getString("model")).thenReturn("X5");
        when(mockResultSet.getInt("year")).thenReturn(2021);
        when(mockResultSet.getDouble("price")).thenReturn(50000.0);
        when(mockResultSet.getString("type")).thenReturn("SUV");
        when(mockResultSet.getBoolean("is_available")).thenReturn(false);
        when(mockResultSet.getString("description")).thenReturn("Luxury SUV");
        when(mockResultSet.getInt("seats")).thenReturn(5);
        when(mockResultSet.getString("image_url")).thenReturn("bmw.jpg");
        when(mockResultSet.getString("transmission")).thenReturn("Auto");

        carDAO.getAllCars(); // populate `cars` list

        List<CarModel> displayCars = carDAO.getDisplayCars();
        assertEquals(1, displayCars.size());
        CarModel model = displayCars.get(0);

        assertEquals("BMW X5", model.getName());
        assertEquals("SUV", model.getType());
        assertEquals(false, model.isAvailable());
        assertEquals(50000.0, model.getPrice());
    }
}
