package com.example.autoaxis.controllers;

import com.example.autoaxis.dto.RentalOrder;
import com.example.autoaxis.entities.DatabaseConnection;

import java.sql.Connection;
import java.time.LocalDate;

public class AppContext {

    public static String selectedCar;
    public static LocalDate rentalStartDate;
    public static LocalDate rentalEndDate;
    public static double dailyPrice;
    public static double totalPrice;

    public static RentalOrder currentRentalOrder;


    public static MainWindowController mainWindowController;
    public static CarDetailsController carDetailsController;
    public static Connection DB;




    public AppContext(){

        try {
            DB = DatabaseConnection.getConnection();

        } catch (Exception e) {
            System.out.println("Failed to connect to the database: " + e.getMessage());
            e.printStackTrace(); // Print full stack trace
        }
    }
}
