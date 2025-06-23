package com.example.autoaxis.controllers;

import com.example.autoaxis.dto.CarModel;
import com.example.autoaxis.entities.Car;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Random;

public class car_cardController {


    @FXML private Button viewDetailsButton;
    @FXML
    private ImageView carImage;
    @FXML
    private Label carName;
    @FXML
    private Label carPrice;
    @FXML
    private Label carType;
    @FXML
    private Label carTransmission;
    @FXML
    private Label carSeats;
    @FXML
    private Label carAC;


    private Car car;

    public void setData(CarModel car) {


        carName.setText(car.getName());
        carPrice.setText("Ksh. " + car.getPrice());
        carType.setText(car.getType());
        carTransmission.setText(car.getTransmission());
        carSeats.setText(car.getSeats()+ " Seats");
        carAC.setText(car.isHasAC() ? "Air Conditioner" : "No AC");

        try {
            String imagePath = car.getImageUrl();
            if (imagePath != null) {
                URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl != null) {
                    carImage.setImage(new Image(imageUrl.toExternalForm()));
                } else {
                    System.out.println(" Resource not found: " + imagePath);
                }
            } else {
                System.out.println(" Image path is null for car: " + car.getName());
            }
        } catch (Exception e) {
            System.out.println(" Failed to load image for: " + car.getName());
            e.printStackTrace();
        }
    }

    public void setCar(Car car) {
        this.car = car;
    }
    public void setOnViewDetails(java.util.function.Consumer<Car> handler) {
        viewDetailsButton.setOnAction(e -> {
            if (handler != null && car != null) {
                handler.accept(car);
            }
        });
    }
}