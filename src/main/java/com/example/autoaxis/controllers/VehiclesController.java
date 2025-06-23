package com.example.autoaxis.controllers;

import com.example.autoaxis.dao.CarDAO;
import com.example.autoaxis.dto.CarModel;
import com.example.autoaxis.entities.Car;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.example.autoaxis.controllers.AppContext.DB;

public class VehiclesController {

    @FXML
    private GridPane vehicleGridContainer;

    private static final int CARDS_PER_ROW = 3;




    public void initialize() {
        new AppContext();
        CarDAO carDAO = new CarDAO();
        try {
            List<Car> cars = carDAO.getAllCars();

            // Make sure vehicleGridContainer is a GridPane
            GridPane gridPane = (GridPane) vehicleGridContainer;

            int column = 0;
            int row = 0;

            for (Car car : cars) {
                CarModel model = new CarModel();
                model.setName(car.getName() + " "+ car.getModel());
                model.setType(car.getType());
                model.setTransmission(car.getTransmission());
                model.setPrice(car.getPrice());
                model.setSeats(car.getSeats());
                model.setImageUrl(car.getImageUrl());



                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/pages/car_card.fxml"));
                Parent card = loader.load();

                car_cardController controller = loader.getController();
                controller.setData(model);

                controller.setCar(car);
                controller.setOnViewDetails(selectedCar -> navigateToCarDetails(selectedCar));



                gridPane.add(card, column, row);

                column++;
                if (column == 3) {
                    column = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setupCardClickHandler(Parent card, Car car) {

        card.setOnMouseClicked(event -> {
            // Navigate to car details based on car ID or name
            navigateToCarDetails(car);
        });
    }

    private void navigateToCarDetails(Car car) {

        switch (car.getId()) {
            case 1: // Mercedes Maybach
                handleMaybackDetails();
                break;
            case 2: // Mercedes S-class
                handleSClassDetails();
                break;
            case 3: // Mercedes GLE
                showGLE();
                break;
            case 4: // Porsche 911 Turbo
                showNineOneOne();
                break;
            case 5: // Toyota Crown
                showCrown();
                break;
            case 6: // Porsche Cayenne
                showCayenne();
                break;
            case 7: // Mercedes E-class
                // Add this method to your CarDetailsController
                // AppContext.carDetailsController.showEClass();
                break;
            case 8: // Toyota Landcruiser
                showLandCruiser();
                break;
            case 9: // BMW M4
                // Add this method to your CarDetailsController
                // AppContext.carDetailsController.showBMWM4();
                break;
            default:
                System.out.println("No details page found for car: " + car.getName());

        }
    }

    private void handleSClassDetails(){
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showSClass();
    }

    private void handleMaybackDetails(){

        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showMayback();

    }


    private void showGLE(){
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showGle();
    }


    private void showNineOneOne(){
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showNineOneOne();
    }


    private void showCrown(){
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showCrown();
    }


    private void showLandCruiser(){
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showLandcruiser();
    }


    private void showCayenne(){
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showCayenne();

    }



}