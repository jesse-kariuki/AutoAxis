package com.example.autoaxis.entities.dao;

import com.example.autoaxis.controllers.AppContext;
import com.example.autoaxis.dao.BookingDAO;
import com.example.autoaxis.dto.Bookings;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookingDAOTest {

    private BookingDAO bookingDAO;

    private Connection mockConnection;
    private PreparedStatement mockStatement;
    private ResultSet mockResultSet;

    @BeforeEach
    void setUp() throws SQLException {
        bookingDAO = new BookingDAO();
        mockConnection = mock(Connection.class);
        mockStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);


        AppContext.DB = mockConnection;


        when(mockConnection.isClosed()).thenReturn(false);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockStatement);
    }

    @Test
    void testGetAllBookings_returnsList() throws Exception {
        when(mockStatement.executeQuery()).thenReturn(mockResultSet);
        when(mockResultSet.next()).thenReturn(true, false);
        when(mockResultSet.getString("booking_id")).thenReturn("B001");
        when(mockResultSet.getString("customer_id")).thenReturn("C001");
        when(mockResultSet.getString("vehicle_id")).thenReturn("V001");
        when(mockResultSet.getDate("pickup_date")).thenReturn(Date.valueOf("2024-08-01"));
        when(mockResultSet.getDate("return_date")).thenReturn(Date.valueOf("2024-08-10"));
        when(mockResultSet.getDouble("total_amount")).thenReturn(5000.0);

        List<Bookings> bookings = bookingDAO.getAllBookings();

        assertEquals(1, bookings.size());
        assertEquals("B001", bookings.get(0).getBooking_id());
        assertEquals("C001", bookings.get(0).getCustomer_id());
        assertEquals("V001", bookings.get(0).getVehicle_id());
        assertEquals("2024-08-01", bookings.get(0).getPickup_date());
    }

    @Test
    void testAddBooking_insertsDataCorrectly() throws Exception {
        Bookings booking = new Bookings(
                "B002", "C002", "V002",
                "Jul 01, 2024", "Jul 10, 2024", 7500.0
        );

        bookingDAO.addBooking(booking);

        verify(mockStatement).setString(1, "B002");
        verify(mockStatement).setString(2, "C002");
        verify(mockStatement).setString(3, "V002");
        verify(mockStatement).setDate(eq(4), any(Date.class));
        verify(mockStatement).setDate(eq(5), any(Date.class));
        verify(mockStatement).setDouble(6, 7500.0);
        verify(mockStatement).executeUpdate();
    }

    @Test
    void testAddBooking_throwsSQLException_onInvalidDateFormat() throws Exception {
        Bookings booking = new Bookings(
                "B003", "C003", "V003",
                "2024-07-01", 
                "Jul 10, 2024", 9000.0
        );

        Exception ex = assertThrows(SQLException.class, () -> {
            bookingDAO.addBooking(booking);
        });

        assertTrue(ex.getMessage().contains("Invalid start date format"));
    }
}
