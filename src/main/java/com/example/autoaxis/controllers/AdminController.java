package com.example.autoaxis.controllers;

import com.example.autoaxis.dao.BookingDAO;
import com.example.autoaxis.dao.CarDAO;
import com.example.autoaxis.dao.CarReader;
import com.example.autoaxis.dao.UserDAO;
import com.example.autoaxis.dto.Bookings;
import com.example.autoaxis.dto.CarModel;
import com.example.autoaxis.dto.UserBookingDTO;
import com.example.autoaxis.entities.Car;
import com.example.autoaxis.entities.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminController {


    @FXML private TableColumn<Bookings, Double> totalAmountColumn;
    @FXML private TableColumn<Bookings, String> returnDateColumn;
    @FXML private TableColumn<Bookings, String> pickupDateColumn;
    @FXML private TableColumn<Bookings, String> vehicleBookedColumn;
    @FXML private TableColumn<Bookings, String> customerNameColumn;
    @FXML private TableColumn<Bookings, String> bookingIdColumn;
    @FXML private TableView<Bookings> bookingTableView;

    @FXML private TableView<UserBookingDTO> customerTableView;
    @FXML private TableColumn<UserBookingDTO, String> bookingDetailsReferenceColumn;
    @FXML private TableColumn<UserBookingDTO, String> customerIdColumn;
    @FXML private TableColumn<UserBookingDTO, String> customerEmailColumn;
    @FXML private TableColumn<UserBookingDTO, String> customerFullNameColumn;

  // TableView now holds UserBookingDTO




    // Navigation buttons
    @FXML private Button dashboardBtn;
    @FXML private Button vehiclesBtn;
    @FXML private Button bookingsBtn;
    @FXML private Button customersBtn;
    @FXML private Button logoutBtn;

    // Views
    @FXML private ScrollPane dashboardView;
    @FXML private ScrollPane vehicleManagementView;
    @FXML private ScrollPane bookingsView;
    @FXML private ScrollPane customersView;

    //Vehicle Management
    @FXML private TableView<CarModel> vehicleTableView;
    @FXML private TableColumn<CarModel, String> vehicleIdColumn;
    @FXML private TableColumn<CarModel, String> vehicleNameColumn;
    @FXML private TableColumn<CarModel, String> vehicleTypeColumn;
    @FXML private TableColumn<CarModel, String> vehiclePriceColumn;
    @FXML private TableColumn<CarModel, String> vehicleStatusColumn;
    // Add Vehicle Panel
    @FXML private VBox addVehiclePanel;
    @FXML private Button addVehicleBtn;
    @FXML private Button closePanelBtn;
    @FXML private Button saveVehicleBtn;
    @FXML private Button cancelAddBtn;
    @FXML private Button chooseImageBtn;
    @FXML private Button exportBtn;

    // Form fields
    @FXML private TextField newVehicleName;
    @FXML private ComboBox<String> newVehicleType;
    @FXML private TextField newVehiclePrice;
    @FXML private ComboBox<String> newVehicleTransmission;
    @FXML private TextField newVehicleSeats;
    @FXML private CheckBox newVehicleAC;
    @FXML private Label selectedImageLabel;

    @FXML
    public void initialize() {


        // Check if all required components are loaded
        if (dashboardView == null) {
            System.err.println("dashboardView is null - check fx:id in FXML");
            return;
        }
        if (vehicleManagementView == null) {
            System.err.println("vehicleManagementView is null - check fx:id in FXML");
            return;
        }
        if (bookingsView == null) {
            System.err.println("bookingsView is null - check fx:id in FXML");
            return;
        }
        if (customersView == null) {
            System.err.println("customersView is null - check fx:id in FXML");
            return;
        }

        // Initialize view visibility - Dashboard should be visible by default
       showDashboard();

        // Initialize combo boxes
        if (newVehicleType != null) {
            newVehicleType.getItems().addAll("SUV", "Sedan", "Hatchback", "Convertible", "Truck");
        }
        if (newVehicleTransmission != null) {
            newVehicleTransmission.getItems().addAll("Manual", "Automatic");
        }


    }

    @FXML
    public void handleDashboard(ActionEvent event) {
        showDashboard();
        updateActiveNavButton(dashboardBtn);
    }

    @FXML
    public void handleVehicles(ActionEvent event) {
        showVehicleManagement();
        updateActiveNavButton(vehiclesBtn);
    }

    @FXML
    public void handleBookings(ActionEvent event) {
        System.out.println("Bookings clicked");
        showBookings();
        updateActiveNavButton(bookingsBtn);
    }

    @FXML
    public void handleCustomers(ActionEvent event) {
        System.out.println("Customers clicked");
        showCustomers();
        updateActiveNavButton(customersBtn);
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        System.out.println("Logout clicked");
        // Add logout logic here

        try {
            Scene currentScene = ((Node) event.getSource()).getScene();
            currentScene.getWindow().hide();
            Parent loginRoot = FXMLLoader.load(getClass().getResource("/com/example/pages/LoginPage.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            Scene loginScene = new Scene(loginRoot);
            stage.setScene(loginScene);
            stage.setWidth(900);
            stage.setHeight(600);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleAddVehicle(ActionEvent event) {
        try {
            // Load the FXML for the popup
            Parent popupRoot = FXMLLoader.load(getClass().getResource("/com/example/pages/AddVehicles.fxml"));

            // Create a new window (Stage)
            Stage popupStage = new Stage();
            popupStage.setTitle("Add Vehicle");

//            // Optional: block interaction with other windows until this one is closed
//            popupStage.initModality(Modality.APPLICATION_MODAL);
//
//            // Optional: set the owner window
//            popupStage.initOwner(((Node) event.getSource()).getScene().getWindow());

            // Set the scene and show
            Scene popupScene = new Scene(popupRoot, 350, 700); // adjust size as needed
            popupStage.setResizable(false);
            popupStage.setScene(popupScene);

            popupStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void handleClosePanel(ActionEvent event) {
        System.out.println("Close Panel clicked");
        if (addVehiclePanel != null) {
            addVehiclePanel.setVisible(false);
        }
    }

    @FXML
    public void handleSaveVehicle(ActionEvent event) {
        System.out.println("Save Vehicle clicked");
        // Add save logic here
        if (addVehiclePanel != null) {
            addVehiclePanel.setVisible(false);
        }
    }

    @FXML
    public void handleCancelAdd(ActionEvent event) {
        System.out.println("Cancel Add clicked");
        if (addVehiclePanel != null) {
            addVehiclePanel.setVisible(false);
        }
    }

    @FXML
    public void handleChooseImage(ActionEvent event) {
        System.out.println("Choose Image clicked");
        // Add file chooser logic here
    }

    @FXML
    public void handleExport(ActionEvent event) {
        System.out.println("Export clicked");
        // Add export logic here
    }

    // Helper methods to show different views
    private void showDashboard() {
        hideAllViews();
        if (dashboardView != null) {
            dashboardView.setVisible(true);
        }
    }

    private void showVehicleManagement() {
        hideAllViews();
        if (vehicleManagementView != null) {
            vehicleManagementView.setVisible(true);
        }



        new AppContext();

        CarReader carReader = new CarDAO();
        try{

            List<Car> cars = carReader.getAllCars();

           List<CarModel> carModels = carReader.getDisplayCars();



           vehicleIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
           vehicleNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
           vehiclePriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
           vehicleTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
           vehicleStatusColumn.setCellValueFactory(new PropertyValueFactory<>("isAvailable"));


            vehicleTableView.setItems(FXCollections.observableArrayList(carModels));




         }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void showBookings() {
        hideAllViews();
        if (bookingsView != null) {
            bookingsView.setVisible(true);
        }

        new AppContext();

        BookingDAO bookingDAO = new BookingDAO();
        try{

            List<Bookings> bookings = bookingDAO.getAllBookings();





            bookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("booking_id"));
            customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
            vehicleBookedColumn.setCellValueFactory(new PropertyValueFactory<>("vehicle_id"));
            pickupDateColumn.setCellValueFactory(new PropertyValueFactory<>("pickup_date"));
            returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("return_date"));
            totalAmountColumn.setCellValueFactory(new PropertyValueFactory<>("total_amount"));


            bookingTableView.setItems(FXCollections.observableArrayList(bookings));




        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void showCustomers() {
        hideAllViews();
        if (customersView != null) {
            customersView.setVisible(true);
        }
        new AppContext();

        UserDAO userDAO = new UserDAO();

        try {
            List<UserBookingDTO> userBookings = userDAO.getUsersAndBooking();

            // Set up cell value factories for the columns
            customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
            customerFullNameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
            customerEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
            bookingDetailsReferenceColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId")); // Set for bookingId

            customerTableView.setItems(FXCollections.observableArrayList(userBookings));

        } catch (SQLException e) {
            System.err.println("Error fetching user and booking reference data: " + e.getMessage());
            e.printStackTrace();
            // Optionally, show an alert to the user
        }

    }

    private void hideAllViews() {
        if (dashboardView != null) dashboardView.setVisible(false);
        if (vehicleManagementView != null) vehicleManagementView.setVisible(false);
        if (bookingsView != null) bookingsView.setVisible(false);
        if (customersView != null) customersView.setVisible(false);
    }

    private void updateActiveNavButton(Button activeButton) {
        // Remove active class from all nav buttons
        if (dashboardBtn != null) {
            dashboardBtn.getStyleClass().remove("nav-button-active");
            if (!dashboardBtn.getStyleClass().contains("nav-button")) {
                dashboardBtn.getStyleClass().add("nav-button");
            }
        }
        if (vehiclesBtn != null) {
            vehiclesBtn.getStyleClass().remove("nav-button-active");
            if (!vehiclesBtn.getStyleClass().contains("nav-button")) {
                vehiclesBtn.getStyleClass().add("nav-button");
            }
        }
        if (bookingsBtn != null) {
            bookingsBtn.getStyleClass().remove("nav-button-active");
            if (!bookingsBtn.getStyleClass().contains("nav-button")) {
                bookingsBtn.getStyleClass().add("nav-button");
            }
        }
        if (customersBtn != null) {
            customersBtn.getStyleClass().remove("nav-button-active");
            if (!customersBtn.getStyleClass().contains("nav-button")) {
                customersBtn.getStyleClass().add("nav-button");
            }
        }

        // Add active class to the clicked button
        if (activeButton != null) {
            activeButton.getStyleClass().remove("nav-button");
            if (!activeButton.getStyleClass().contains("nav-button-active")) {
                activeButton.getStyleClass().add("nav-button-active");
            }
        }
    }


}
