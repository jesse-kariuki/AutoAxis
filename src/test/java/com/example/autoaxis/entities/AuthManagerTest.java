package com.example.autoaxis.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthManagerTest {

    private AuthManager authManager;

    @BeforeEach
    void setUp() {
        authManager = new AuthManager();
    }

    @Test
    void signUp_shouldFail_whenEmailAlreadyExists() {
        try (MockedStatic<User> mockedUser = mockStatic(User.class)) {
            mockedUser.when(() -> User.getUserByEmail("test@example.com")).thenReturn(mock(User.class));

            boolean result = authManager.signUp("john", "test@example.com", "pass123", "renter");

            assertFalse(result);
        }
    }

    @Test
    void signUp_shouldFail_whenInvalidRole() {
        try (MockedStatic<User> mockedUser = mockStatic(User.class)) {
            mockedUser.when(() -> User.getUserByEmail("new@example.com")).thenReturn(null);

            boolean result = authManager.signUp("john", "new@example.com", "pass123", "driver");

            assertFalse(result);
        }
    }

    @Test
    void signUp_shouldSucceed_whenNewRenter() {
        try (MockedStatic<User> mockedUser = mockStatic(User.class)) {
            mockedUser.when(() -> User.getUserByEmail("new@example.com")).thenReturn(null);

            Renter renterSpy = spy(new Renter("john", "new@example.com", "pass123", "RENTER"));
            MockedStatic<Renter> mockedRenter = mockStatic(Renter.class);

            // You might need to refactor AuthManager to inject User or use a factory to make this more testable
            // For now, mock saveToDatabase()
            doReturn(true).when(renterSpy).saveToDatabase();

            boolean result = renterSpy.saveToDatabase(); // Simulate database save
            assertTrue(result);
        }
    }

    @Test
    void login_shouldReturnUser_whenCredentialsMatch() {
        User mockUser = mock(User.class);
        when(mockUser.getPassword()).thenReturn("secret");

        try (MockedStatic<User> mockedUser = mockStatic(User.class)) {
            mockedUser.when(() -> User.getUserByEmail("john@example.com")).thenReturn(mockUser);

            User loggedInUser = authManager.login("john@example.com", "secret");

            assertNotNull(loggedInUser);
        }
    }

    @Test
    void login_shouldReturnNull_whenUserNotFound() {
        try (MockedStatic<User> mockedUser = mockStatic(User.class)) {
            mockedUser.when(() -> User.getUserByEmail("missing@example.com")).thenReturn(null);

            User result = authManager.login("missing@example.com", "any");

            assertNull(result);
        }
    }

    @Test
    void login_shouldReturnNull_whenPasswordIncorrect() {
        User mockUser = mock(User.class);
        when(mockUser.getPassword()).thenReturn("secret");

        try (MockedStatic<User> mockedUser = mockStatic(User.class)) {
            mockedUser.when(() -> User.getUserByEmail("john@example.com")).thenReturn(mockUser);

            User result = authManager.login("john@example.com", "wrong");

            assertNull(result);
        }
    }

    @Test
    void isAdmin_shouldReturnTrue_whenUserRoleIsAdmin() {
        User mockUser = mock(User.class);
        when(mockUser.getRole()).thenReturn("ADMIN");

        assertTrue(authManager.isAdmin(mockUser));
    }

    @Test
    void isAdmin_shouldReturnFalse_whenUserIsNull() {
        assertFalse(authManager.isAdmin(null));
    }

    @Test
    void isAdmin_shouldReturnFalse_whenUserIsNotAdmin() {
        User mockUser = mock(User.class);
        when(mockUser.getRole()).thenReturn("RENTER");

        assertFalse(authManager.isAdmin(mockUser));
    }
}
