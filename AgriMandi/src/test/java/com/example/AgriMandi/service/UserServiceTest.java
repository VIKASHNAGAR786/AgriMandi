//package com.example.AgriMandi.service;
//
//import com.example.AgriMandi.entity.User;
//import com.example.AgriMandi.repository.UserRepository;
//import com.example.AgriMandi.service.impl.UserServiceImpl;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//class UserServiceTest {
//
//    @Mock
//    private UserRepository userRepository;
//
//    @InjectMocks
//    private UserServiceImpl userService;
//
//    public UserServiceTest() {
//        MockitoAnnotations.openMocks(this);
//    }
//
//    @Test
//    void testRegisterUser() {
//        // Arrange
//        User user = new User();
//        user.setEmail("test@example.com");
//        user.setPassword("password");
//        when(userRepository.findByEmail("test@example.com")).thenReturn(null);
//        when(userRepository.save(user)).thenReturn(user);
//
//        // Act
//        User result = userService.registerUser(user);
//
//        // Assert
//        assertNotNull(result);
//        assertEquals("test@example.com", result.getEmail());
//        verify(userRepository, times(1)).save(user);
//    }
//
//    @Test
//    void testRegisterUser_EmailAlreadyExists() {
//        // Arrange
//        User existingUser = new User();
//        existingUser.setEmail("test@example.com");
//        when(userRepository.findByEmail("test@example.com")).thenReturn(existingUser);
//
//        // Act & Assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            userService.registerUser(existingUser);
//        });
//        assertEquals("Email already in use.", exception.getMessage());
//        verify(userRepository, never()).save(any(User.class));
//    }
//
//    @Test
//    void testLoginUser() {
//        // Arrange
//        User user = new User();
//        user.setEmail("test@example.com");
//        user.setPassword("password");
//        when(userRepository.findByEmail("test@example.com")).thenReturn(user);
//
//        // Act
//        User result = userService.loginUser("test@example.com", "password");
//
//        // Assert
//        assertNotNull(result);
//        assertEquals("test@example.com", result.getEmail());
//    }
//
//    @Test
//    void testLoginUser_InvalidCredentials() {
//        // Arrange
//        when(userRepository.findByEmail("invalid@example.com")).thenReturn(null);
//
//        // Act & Assert
//        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
//            userService.loginUser("invalid@example.com", "wrongpassword");
//        });
//        assertEquals("Invalid email or password.", exception.getMessage());
//    }
//}
