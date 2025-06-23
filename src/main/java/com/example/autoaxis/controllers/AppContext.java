package com.example.autoaxis.controllers;

import com.example.autoaxis.entities.DatabaseConnection;

import java.sql.Connection;

public class AppContext {

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
