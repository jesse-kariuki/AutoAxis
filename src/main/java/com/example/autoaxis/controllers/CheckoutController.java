package com.example.autoaxis.controllers;

import com.example.autoaxis.dao.BookingDAO;
import com.example.autoaxis.dto.Bookings;
import com.example.autoaxis.dto.RentalOrder;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.util.regex.Pattern;

public class CheckoutController {

    // Car Summary
    @FXML private ImageView carImageView;
    @FXML private Label carNameLabel;
    @FXML private Label carTypeLabel;
    @FXML private Label carTransmissionLabel;
    @FXML private Label carSeatsLabel;
    @FXML private Label carACLabel;

    // Rental Details
    @FXML private Label pickupDateLabel;
    @FXML private Label returnDateLabel;
    @FXML private Label rentalDaysLabel;
    @FXML private Label dailyRateLabel;

    // Price Breakdown
    @FXML private Label subtotalLabel;
    @FXML private Label taxLabel;
    @FXML private Label totalLabel;

    // Customer Information
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField emailField;
    @FXML private TextField phoneField;
    @FXML private TextField licenseField;

    // Payment Information
    @FXML private ComboBox<String> paymentMethodCombo;
    @FXML private TextField cardNumberField;
    @FXML private TextField expiryField;
    @FXML private TextField cvvField;
    @FXML private TextField cardholderField;

    // Terms and Actions
    @FXML private CheckBox termsCheckBox;
    @FXML private Button backButton;
    @FXML private Button confirmButton;

    private RentalOrder rentalOrder;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    private BookingDAO bookingDAO;

    public void initialize() {

        paymentMethodCombo.setValue("Credit Card");


        backButton.setOnAction(e -> handleBack());
        confirmButton.setOnAction(e -> handleConfirmBooking());


        setupInputValidation();


        if (AppContext.currentRentalOrder != null) {
            this.rentalOrder = AppContext.currentRentalOrder;
            populateOrderSummary();
            // Clear AppContext.currentRentalOrder after use
            AppContext.currentRentalOrder = null;
        }
    }

    public void setRentalOrder(RentalOrder rentalOrder) {
        this.rentalOrder = rentalOrder;
        populateOrderSummary();
    }

    private void populateOrderSummary() {
        if (rentalOrder == null || rentalOrder.getCarModel() == null) {
            return;
        }

        // Car details
        carNameLabel.setText(rentalOrder.getCarModel().getName());
        carTypeLabel.setText(rentalOrder.getCarModel().getType());
        carTransmissionLabel.setText(rentalOrder.getCarModel().getTransmission());
        carSeatsLabel.setText(rentalOrder.getCarModel().getSeats() + " Seats");
        carACLabel.setText(rentalOrder.getCarModel().isHasAC() ? "Air Conditioner" : "No AC");


        try {
            String imagePath = rentalOrder.getCarModel().getImageUrl();
            if (imagePath != null) {
                URL imageUrl = getClass().getResource(imagePath);
                if (imageUrl != null) {
                    carImageView.setImage(new Image(imageUrl.toExternalForm()));
                } else {
                    System.out.println("Image not found: " + imagePath);
                }
            }
        } catch (Exception e) {
            System.out.println("Failed to load car image: " + e.getMessage());
        }


        pickupDateLabel.setText(rentalOrder.getPickupDate().format(dateFormatter));
        returnDateLabel.setText(rentalOrder.getReturnDate().format(dateFormatter));
        rentalDaysLabel.setText(String.valueOf(rentalOrder.getRentalDays()));
        dailyRateLabel.setText("Ksh. " + String.format("%.2f", rentalOrder.getDailyRate()));


        subtotalLabel.setText("Ksh. " + String.format("%.2f", rentalOrder.getSubtotal()));
        taxLabel.setText("Ksh. " + String.format("%.2f", rentalOrder.getTax()));
        totalLabel.setText("Ksh. " + String.format("%.2f", rentalOrder.getTotal()));
    }

    private void setupInputValidation() {
        // Email validation
        emailField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) { // Lost focus
                validateEmail();
            }
        });


        phoneField.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal) {
                validatePhone();
            }
        });


        cardNumberField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() > 19) {
                cardNumberField.setText(oldVal);
                return;
            }


            String formatted = newVal.replaceAll("\\s+", "").replaceAll("(.{4})", "$1 ");
            if (!formatted.equals(newVal)) {
                cardNumberField.setText(formatted.trim());
            }
        });


        cvvField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                cvvField.setText(newVal.replaceAll("[^\\d]", ""));
            }
            if (newVal.length() > 4) {
                cvvField.setText(oldVal);
            }
        });

        // Expiry date formatting
        expiryField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.length() > 5) {
                expiryField.setText(oldVal);
                return;
            }

            // Format as MM/YY
            String digits = newVal.replaceAll("\\D", "");
            if (digits.length() >= 2) {
                String formatted = digits.substring(0, 2);
                if (digits.length() > 2) {
                    formatted += "/" + digits.substring(2);
                }
                if (!formatted.equals(newVal)) {
                    expiryField.setText(formatted);
                }
            }
        });


//        termsCheckBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
//            confirmButton.setDisable(!newVal || !validateAllFields());
//        });
    }

    private boolean validateEmail() {
        String email = emailField.getText().trim();
        Pattern emailPattern = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

        if (!email.isEmpty() && !emailPattern.matcher(email).matches()) {
            emailField.setStyle("-fx-border-color: #dc3545; -fx-border-width: 2px;");
            return false;
        } else {
            emailField.setStyle("");
            return true;
        }
    }

    private boolean validatePhone() {
        String phone = phoneField.getText().trim();
        Pattern phonePattern = Pattern.compile("^[+]?[0-9]{10,15}$");

        if (!phone.isEmpty() && !phonePattern.matcher(phone.replaceAll("\\s+", "")).matches()) {
            phoneField.setStyle("-fx-border-color: #dc3545; -fx-border-width: 2px;");
            return false;
        } else {
            phoneField.setStyle("");
            return true;
        }
    }

    private boolean validateAllFields() {
        return !firstNameField.getText().trim().isEmpty() &&
                !lastNameField.getText().trim().isEmpty() &&
                !emailField.getText().trim().isEmpty() &&
                !phoneField.getText().trim().isEmpty() &&
                !licenseField.getText().trim().isEmpty() &&
                !cardNumberField.getText().trim().isEmpty() &&
                !expiryField.getText().trim().isEmpty() &&
                !cvvField.getText().trim().isEmpty() &&
                !cardholderField.getText().trim().isEmpty() &&
                validateEmail() &&
                validatePhone();
    }

    private void handleBack() {
        try {
            // Navigate back to car selection using MainWindowController
            if (AppContext.mainWindowController != null) {
                AppContext.mainWindowController.handleSClassDetailsClick(); // Or a more generic method to go back to car details
            } else {
                System.err.println("MainWindowController is not initialized.");
            }
        } catch (Exception e) {
            showAlert("Error", "Failed to navigate back.");
        }
    }

    private void handleConfirmBooking() {
        if (!termsCheckBox.isSelected()) {
            showAlert("Error", "Please accept the Terms and Conditions to proceed.");
            return;
        }

        if (!validateAllFields()) {
            showAlert("Error", "Please fill in all required fields correctly.");
            return;
        }


        try {
            String bookingId = generateBookingReference(); // You can use UUID.randomUUID().toString() for stronger IDs
            String customerId = UUID.randomUUID().toString(); // Placeholder: You'd get this from a logged-in user or generate
            String vehicleId = rentalOrder.getCarModel().getName(); // Placeholder: Use a unique ID for the car, not name

            // Create a Bookings DTO
            Bookings newBooking = new Bookings(
                    bookingId,
                    customerId,
                    vehicleId,
                    rentalOrder.getPickupDate().format(dateFormatter),
                    rentalOrder.getReturnDate().format(dateFormatter),
                    rentalOrder.getTotal()
            );

            // Save the booking to database
            bookingDAO.addBooking(newBooking);

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
            successAlert.setTitle("Booking Confirmed");
            successAlert.setHeaderText("Your car rental has been booked successfully!");
            successAlert.setContentText("Booking Reference: " + bookingId +
                    "\n\nYou will receive a confirmation email shortly.");
            successAlert.showAndWait();

            AppContext.mainWindowController.handleHomeClick(); // Navigate back to home after booking


        } catch (Exception e) {
            showAlert("Error", "Failed to process booking. Please try again.");
            e.printStackTrace();
        }
    }

    private String generateBookingReference() {
        // Generate a simple booking reference
        return "AR" + System.currentTimeMillis() % 1000000;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Getters for accessing form data if needed
    public String getFirstName() { return firstNameField.getText().trim(); }
    public String getLastName() { return lastNameField.getText().trim(); }
    public String getEmail() { return emailField.getText().trim(); }
    public String getPhone() { return phoneField.getText().trim(); }
    public String getLicense() { return licenseField.getText().trim(); }
    public String getPaymentMethod() { return paymentMethodCombo.getValue(); }
    public String getCardNumber() { return cardNumberField.getText().trim(); }
    public String getExpiry() { return expiryField.getText().trim(); }
    public String getCvv() { return cvvField.getText().trim(); }
    public String getCardholder() { return cardholderField.getText().trim(); }
}