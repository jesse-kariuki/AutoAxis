package com.example.autoaxis.controllers;

import javafx.fxml.FXML;

public class VehiclesController {

    @FXML
    private void handleSClassDetails(){
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
    private void showLandCruiser(){
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showLandcruiser();
    }

    @FXML
    private void showCayenne(){
        AppContext.mainWindowController.handleSClassDetailsClick();
        AppContext.carDetailsController.showCayenne();

    }



}
