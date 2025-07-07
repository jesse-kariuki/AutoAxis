package com.example.autoaxis.dto;

public class Bookings {

    private String booking_id;
    private String customer_id;
    private String vehicle_id;
    private String pickup_date;
    private String return_date;
    private Double total_amount;

    public Bookings(String booking_id, String customer_id, String vehicle_id, String pickup_date, String return_date, Double total_amount) {
        this.booking_id = booking_id;
        this.customer_id = customer_id;
        this.vehicle_id = vehicle_id;
        this.pickup_date = pickup_date;
        this.return_date = return_date;
        this.total_amount = total_amount;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public String getVehicle_id() {
        return vehicle_id;
    }

    public String getPickup_date() {
        return pickup_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public Double getTotal_amount() {
        return total_amount;
    }
}
