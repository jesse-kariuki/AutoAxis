package com.example.autoaxis.controllers;

import javafx.fxml.FXML;

public class HomepageController {

    @FXML
    private void handleSClassDetails() {
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showSClass();
    }
    @FXML
    private void handleMaybackDetails(){

        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showMayback();

    }

    @FXML
    private void showGLE(){
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showGle();
    }

    @FXML
    private void showNineOneOne(){
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showNineOneOne();
    }

    @FXML
    private void showCrown(){
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showCrown();
    }

    @FXML
    private void showCayenne(){
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showCayenne();

    }

    @FXML
    private void showAllVehicles() {
        AppContext.mainWindowController.handleVehiclesClick();
    }
}
