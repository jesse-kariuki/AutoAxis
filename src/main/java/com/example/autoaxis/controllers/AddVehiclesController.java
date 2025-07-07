package com.example.autoaxis.controllers;

import com.example.autoaxis.dao.CarDAO;
import com.example.autoaxis.entities.Car;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;

public class AddVehiclesController {

    @FXML private TextField newVehicleDescription;
    @FXML private TextField newVehicleYear;
    @FXML private TextField newVehicleModel;
    @FXML private CheckBox newVehicleAC;
    @FXML private TextField newVehicleSeats;
    @FXML private ComboBox newVehicleTransmission;
    @FXML private TextField newVehiclePrice;
    @FXML private ComboBox newVehicleType;
    @FXML private TextField newVehicleName;
    @FXML private VBox addVehiclePanel;

    boolean isAvailable = true;

    private String imagePath;

    @FXML
    public void handleClosePanel(ActionEvent event) {
        System.out.println("Close Panel clicked");
        if (addVehiclePanel != null) {
            addVehiclePanel.setVisible(false);
        }
    }

    @FXML
    public void initialize() {
        // Initialize ComboBoxes and other UI elements if needed
        newVehicleType.getItems().addAll("Sedan", "SUV", "Truck", "Van");
        newVehicleTransmission.getItems().addAll("Automatic", "Manual");
    }

    @FXML
    public void handleChooseImage(ActionEvent event) {
        System.out.println("Choose Image clicked");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a File");



        // Optional: Set file extension filters (e.g., only images)
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"),
                new FileChooser.ExtensionFilter("All Files", "*.*")
        );

        // Get the current window
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Show the file chooser dialog
        File selectedFile = fileChooser.showOpenDialog(stage);

        String filename = selectedFile.getName();

        if (selectedFile != null) {
            // Get the absolute path
            String absolutePath = selectedFile.getAbsolutePath();

            // Get the URI-style path (starts with "/")
            imagePath = "/Images/" + filename;



            // Optionally display in a label or text field
            // filePathField.setText(uriPath);
        }

    }

    @FXML
    public void handleSaveVehicle(ActionEvent event) {
        System.out.println("Save Vehicle clicked");
        // Add save logic here
        new AppContext();
        CarDAO carDAO = new CarDAO();
        Random random = new Random();
        try{
        carDAO.insertCar(new Car(
                newVehicleName.getText(),
                newVehicleType.getValue().toString(),
                Double.parseDouble(newVehiclePrice.getText()),
                newVehicleTransmission.getValue().toString(),
                Integer.parseInt(newVehicleSeats.getText()),
                newVehicleModel.getText(),
                Integer.parseInt(newVehicleYear.getText()),
                newVehicleDescription.getText(),
                isAvailable || random.nextBoolean(),
                imagePath // Placeholder for image URL

                ));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();

         }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error saving vehicle: " + e.getMessage());
        }


    }


    @FXML
    public void handleCancelAdd(ActionEvent event) {
        System.out.println("Cancel Add clicked");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }


}
