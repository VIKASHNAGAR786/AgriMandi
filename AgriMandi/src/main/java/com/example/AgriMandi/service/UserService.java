package com.example.AgriMandi.service;



import java.util.List;
import java.util.Map;

import com.example.AgriMandi.entity.User;

public interface UserService {
    User registerUser(User user);
    User loginUser(String email, String password);
    List<User> getAllUsers();               // Get all users
    User getUserById(Long id);              // Get user by ID
    User getUserByEmail(String email);      // Get user by email
    User saveUser(User user);               // Save a new user
    void deleteUser(Long id);               // Delete user by ID
    Map<String, Object> getUserDetailsWithProduct(Long userId);
    User validateUser(String email, String password);
}
