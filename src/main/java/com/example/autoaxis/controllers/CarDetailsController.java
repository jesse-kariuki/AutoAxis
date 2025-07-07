package com.example.autoaxis.controllers;

import com.example.autoaxis.dto.CarModel;
import com.example.autoaxis.dto.RentalOrder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class CarDetailsController implements Initializable {

    @FXML private Button viewAllBtn;
    @FXML private StackPane maybackPane;
    @FXML private StackPane sClassPane;
    @FXML private StackPane glePane;
    @FXML private StackPane crownPane;
    @FXML private StackPane nineOneOnePane;
    @FXML private StackPane landcruiserPane;
    @FXML private StackPane cayennePane;

    // DatePickers for each car
    @FXML private DatePicker maybackStartDate;
    @FXML private DatePicker maybackEndDate;
    @FXML private DatePicker sClassStartDate;
    @FXML private DatePicker sClassEndDate;
    @FXML private DatePicker gleStartDate;
    @FXML private DatePicker gleEndDate;
    @FXML private DatePicker crownStartDate;
    @FXML private DatePicker crownEndDate;
    @FXML private DatePicker nineOneOneStartDate;
    @FXML private DatePicker nineOneOneEndDate;
    @FXML private DatePicker landcruiserStartDate;
    @FXML private DatePicker landcruiserEndDate;
    @FXML private DatePicker cayenneStartDate;
    @FXML private DatePicker cayenneEndDate;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        AppContext.carDetailsController = this;
        maybackPane.setVisible(false);
        sClassPane.setVisible(true);

        // Initialize date pickers with today's date
        LocalDate today = LocalDate.now();
        maybackStartDate.setValue(today);
        maybackEndDate.setValue(today.plusDays(1));
        sClassStartDate.setValue(today);
        sClassEndDate.setValue(today.plusDays(1));
        gleStartDate.setValue(today);
        gleEndDate.setValue(today.plusDays(1));
        crownStartDate.setValue(today);
        crownEndDate.setValue(today.plusDays(1));
        nineOneOneStartDate.setValue(today);
        nineOneOneEndDate.setValue(today.plusDays(1));
        landcruiserStartDate.setValue(today);
        landcruiserEndDate.setValue(today.plusDays(1));
        cayenneStartDate.setValue(today);
        cayenneEndDate.setValue(today.plusDays(1));
    }

    @FXML
    private void handleViewAll() {
        AppContext.mainWindowController.handleVehiclesClick();
    }

    // Checkout handlers for each car
    @FXML
    private void handleMaybackCheckout() {
        CarModel mayback = new CarModel(1,"Mercedes Mayback", "Sedan", "Automatic", 4, true, "/Images/mercedes-mayback-card.jpg");
        handleCheckout(mayback, 24000, maybackStartDate.getValue(), maybackEndDate.getValue());
    }

    @FXML
    private void handleSClassCheckout() {
        CarModel sClass = new CarModel(2,"Mercedes S-class", "Sedan", "Automatic", 5, true, "/Images/mercedes-S-class-sport.jpg");
        handleCheckout(sClass, 32500, sClassStartDate.getValue(), sClassEndDate.getValue());
    }

    @FXML
    private void handleGleCheckout() {
        CarModel gle = new CarModel(3,"Mercedes GLE", "SUV", "Automatic", 5, true, "/Images/mercede-gle.png");
        handleCheckout(gle, 44000, gleStartDate.getValue(), gleEndDate.getValue());
    }

    @FXML
    private void handleNineOneOneCheckout() {
        CarModel nineOneOne = new CarModel(4,"Porsche 911", "Sports Car", "Automatic", 2, true, "/Images/porsche-sports.jpg");
        handleCheckout(nineOneOne, 39000, nineOneOneStartDate.getValue(), nineOneOneEndDate.getValue());
    }

    @FXML
    private void handleCrownCheckout() {
        CarModel crown = new CarModel(5,"Toyota Crown", "Sedan", "Automatic", 5, true, "/Images/toyota-crown.jpg");
        handleCheckout(crown, 35000, crownStartDate.getValue(), crownEndDate.getValue());
    }

    @FXML
    private void handleLandcruiserCheckout() {
        CarModel landcruiser = new CarModel(8,"Landcruiser", "SUV", "Automatic", 7, true, "/Images/toyota-landcruiser.jpg");
        handleCheckout(landcruiser, 50000, landcruiserStartDate.getValue(), landcruiserEndDate.getValue());
    }

    @FXML
    private void handleCayenneCheckout() {
        CarModel cayenne = new CarModel(6,"Porsche Cayenne", "SUV", "Automatic", 5, true, "/Images/porsche-suv.jpg");
        handleCheckout(cayenne, 24000, cayenneStartDate.getValue(), cayenneEndDate.getValue());
    }

    private void handleCheckout(CarModel car, double dailyPrice, LocalDate startDate, LocalDate endDate) {
        try {
            // Validate dates
            if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
                System.out.println("Invalid date range");
                // Optionally show an Alert to the user
                return;
            }

            // Create a RentalOrder object
            RentalOrder rentalOrder = new RentalOrder(car, startDate, endDate, dailyPrice);

            // Store the RentalOrder in AppContext
            AppContext.currentRentalOrder = rentalOrder;

            // Call the MainWindowController's handleCheckoutClick method
            if (AppContext.mainWindowController != null) {
                AppContext.mainWindowController.handleCheckoutClick();
            } else {
                System.err.println("MainWindowController is not initialized in AppContext.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Rest of your existing show methods...
    @FXML
    public void showMayback() {
        maybackPane.setVisible(true);
        sClassPane.setVisible(false);
        glePane.setVisible(false);
        crownPane.setVisible(false);
        nineOneOnePane.setVisible(false);
        landcruiserPane.setVisible(false);
        cayennePane.setVisible(false);
    }

    @FXML
    public void showSClass() {
        sClassPane.setVisible(true);
        maybackPane.setVisible(false);
        glePane.setVisible(false);
        crownPane.setVisible(false);
        nineOneOnePane.setVisible(false);
        landcruiserPane.setVisible(false);
        cayennePane.setVisible(false);
    }

    @FXML
    public void showGle() {
        sClassPane.setVisible(false);
        maybackPane.setVisible(false);
        glePane.setVisible(true);
        crownPane.setVisible(false);
        nineOneOnePane.setVisible(false);
        landcruiserPane.setVisible(false);
        cayennePane.setVisible(false);
    }

    @FXML
    public void showNineOneOne() {
        sClassPane.setVisible(false);
        maybackPane.setVisible(false);
        glePane.setVisible(false);
        crownPane.setVisible(false);
        nineOneOnePane.setVisible(true);
        landcruiserPane.setVisible(false);
        cayennePane.setVisible(false);
    }

    @FXML
    public void showCrown() {
        sClassPane.setVisible(false);
        maybackPane.setVisible(false);
        glePane.setVisible(false);
        crownPane.setVisible(true);
        nineOneOnePane.setVisible(false);
        landcruiserPane.setVisible(false);
        cayennePane.setVisible(false);
    }

    @FXML
    public void showLandcruiser() {
        sClassPane.setVisible(false);
        maybackPane.setVisible(false);
        glePane.setVisible(false);
        crownPane.setVisible(false);
        nineOneOnePane.setVisible(false);
        landcruiserPane.setVisible(true);
        cayennePane.setVisible(false);
    }

    @FXML
    public void showCayenne() {
        sClassPane.setVisible(false);
        maybackPane.setVisible(false);
        glePane.setVisible(false);
        crownPane.setVisible(false);
        nineOneOnePane.setVisible(false);
        landcruiserPane.setVisible(false);
        cayennePane.setVisible(true);
    }
}