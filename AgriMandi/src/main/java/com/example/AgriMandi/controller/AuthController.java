package com.example.AgriMandi.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.AgriMandi.service.UserService;
import com.example.AgriMandi.entity.User;
import com.example.AgriMandi.service.JwtUtil;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    // Login method using @RequestParam
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        logger.info("Login attempt for email: {}", email);
        try {
            // If user is valid, generate token
            String token = JwtUtil.generateToken(email);
            logger.info("Login successful for email: {}", email);

            // Return the token and a success message
            return ResponseEntity.ok(Map.of(
                "token", token,
                "message", "Login successful"
            ));
        } catch (Exception e) {
            logger.error("Login failed for email: {}", email, e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "message", "Invalid credentials"
            ));
        }
    }
}
