package com.example.AgriMandi.service.impl;

import com.example.AgriMandi.entity.Product;
import com.example.AgriMandi.entity.User;
import com.example.AgriMandi.repository.ProductRepository;
import com.example.AgriMandi.repository.UserRepository;
import com.example.AgriMandi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already in use.");
        }
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return user;
    }

    @Override
    public User validateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return user;
    }

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Map<String, Object> getUserDetailsWithProduct(Long userId) {
        Map<String, Object> response = new HashMap<>();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        response.put("id", user.getId());
        response.put("name", user.getName());
        response.put("role", user.getRoles());

        Product product = (Product) productRepository.findByUserId(userId);
        if (product != null) {
            response.put("productName", product.getName());
        } else {
            response.put("productName", "No product associated");
        }

        return response;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
