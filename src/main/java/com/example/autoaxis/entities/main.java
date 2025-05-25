package com.example.autoaxis.entities;

import java.util.List;
import java.util.ArrayList;


public class main {
    public static void main(String[] args) {
        // 1. Signing up as buyer

        /*
        User buyer = new Buyer("john", "john@example.com", "1234", "buyer");
        boolean saved = buyer.saveToDatabase();
        System.out.println("User saved: " + saved);


        // 2. Logging in as a buyer
        String email = "john@example.com";
        String password = "1234";

        AuthManager auth = new AuthManager();
        User user = auth.login(email, password);

        if (user != null) {
            System.out.println("Login successful: " + user);
        } else {
            System.out.println("Login failed.");
        }
        */

        // 1. Signing up as a renter

        /*
        User renter1 = new Renter("Happy", "Happy@example.com", "12345", "renter");
        boolean saved1 = renter1.saveToDatabase();
        System.out.println("User saved: " + saved1);   */

        // 2. Logging in as a  renter
        /*
        String email1 = "Happy@example.com";
        String password1 = "12345";

        AuthManager auth1 = new AuthManager();
        User user1 = auth1.login(email1, password1);

        if (user1 != null) {
            System.out.println("Login successful: " + user1);
        } else {
            System.out.println("Login failed.");
        }
        */

            // 1. Registering as an admin is not allowed
        /*
        User admins = new Admin("john", "john11@example.com", "1234", "admin");
        boolean saved = admins.saveToDatabase();
        System.out.println("User saved: " + saved);    */


        //Logging in as an admin to the account that has already been created in the database
        /*
        String email3 = "admin@example.com";
        String password3 = "123456";

        AuthManager auth2 = new AuthManager();
        User user = auth2.login(email3, password3);

        if (user != null) {
            System.out.println("Login successful: " + user);
        } else {
            System.out.println("Login failed.");
        }  */


        /*
        // 1. Add cars
        CarManager carManager = new CarManager();
        Car car = new Car("Toyota", "Corolla", 2020, 15000.0, "Sedan", true, "Reliable and fuel-efficient");

        boolean success = carManager.addCar(car);

        if (success) {
            System.out.println("Car added successfully.");
        } else {
            System.out.println("Failed to add car.");
        }
        Car car2 = new Car("Honda", "Civic", 2019, 14000.0, "Sedan", true, "Comfortable and efficient");
        carManager.addCar(car2);

        Car car3 = new Car("Ford", "Mustang", 2021, 26000.0, "Coupe", true, "Sporty and powerful");
        carManager.addCar(car3);

         */


        // Delete Car By ID
        /*
        CarManager carManager = new CarManager();
        boolean deleted = carManager.deleteCar(22);

        if (deleted) {
            System.out.println("Car was deleted.");
        } else {
            System.out.println("Car was not found or could not be deleted.");
        }

        */


        // List All Available Cars

        /*
        CarManager carManager = new CarManager();
        List<Car> availableCars = carManager.getAvailableCars();

        if (availableCars.isEmpty()) {
            System.out.println("No available cars found.");
        } else {
            System.out.println("Available Cars:");
            for (Car car : availableCars) {
                System.out.println("ID: " + car.getId() +
                        " | " + car.getMake() +
                        " " + car.getModel() +
                        " | Year: " + car.getYear() +
                        " | Price: $" + car.getPrice());
            }
        }

        */



        /*
        CartManager cartManager = new CartManager();

        int userId = 1; // Simulate a user ID

        // Sample cars
        Car car1 = new Car(101, "Toyota", "Corolla", 2020, 20000, "Sedan", true, "Reliable car");
        Car car2 = new Car(102, "Honda", "Civic", 2021, 22000, "Sedan", true, "Sporty and efficient");

        // Add to cart
        cartManager.addToCart(userId, car1, "rent");
        cartManager.addToCart(userId, car2, "buy");

        // View cart
        Cart cart = cartManager.viewCart(userId);
        System.out.println("Cart Contents:\n" + cart);

        // Total with tax
        System.out.printf("Total with Tax: $%.2f%n", cartManager.getTotalWithTax(userId));

        // Remove one item
        cartManager.removeFromCart(userId, 101);
        System.out.println("After removing Toyota Corolla:");
        System.out.println(cart);




        // Clear cart
        cartManager.clearCart(userId);
        System.out.println("After clearing cart:");
        System.out.println(cart);

        */

    }
}
