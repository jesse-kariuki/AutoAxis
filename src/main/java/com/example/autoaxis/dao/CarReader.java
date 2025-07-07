package com.example.autoaxis.dao;


import com.example.autoaxis.dto.CarModel;
import com.example.autoaxis.entities.Car;

import java.util.List;


//INTERFACE SEGREGATION
//THIS INTERFACE IS RESPONSIBLE FOR READING CARS FROM THE DATABASE
// IT HAS ONE TASK ONLY - TO PROVIDE METHODS FOR RETRIEVING CAR DATA
public interface CarReader {

    List<Car> getAllCars() throws Exception;
    List<CarModel> getDisplayCars();

}
