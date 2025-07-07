package com.example.autoaxis.dao;

import com.example.autoaxis.entities.Car;

import java.sql.SQLException;

//INTERFACE SEGREGATION
//THIS INTERFACE IS RESPONSIBLE FOR WRITING CARS TO THE DATABASE
public interface CarWriter {

     boolean insertCar(Car car) throws SQLException;



}
