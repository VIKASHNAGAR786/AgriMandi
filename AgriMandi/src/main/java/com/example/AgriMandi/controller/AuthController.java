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

    @Autowired
    private UserService userService;

    // Login method using @RequestParam
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    	logger.info("Login attempt for email: {}", loginRequest.getEmail());
    	try {
    	    User user = userService.validateUser(loginRequest.getEmail(), loginRequest.getPassword());
    	    if (user == null) {
    	        logger.warn("Invalid credentials for email: {}", loginRequest.getEmail());
    	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
    	            "message", "Invalid email or password"
    	        ));
    	    }

    	    logger.info("Generating token...");
    	    String token = JwtUtil.generateToken(loginRequest.getEmail());
    	    logger.info("Token generated: {}", token);

    	    return ResponseEntity.ok(Map.of(
    	        "token", token,
    	        "message", "Login successful"
    	    ));
    	} catch (Exception e) {
    	    logger.error("Error during login: ", e);
    	    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
    	        "message", "Login failed"
    	    ));
    	}
    }
 // Token validation method
    @GetMapping("/login")
    public ResponseEntity<?> validateToken(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            // Check if the token is present and starts with "Bearer "
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("message", "Token is missing or invalid"));
            }

            // Extract the token from the Authorization header
            String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

            // Validate the token
            boolean isValid = JwtUtil.validateToken(token);

            if (isValid) {
                // If the token is valid, decode the token to extract user details (e.g., email)
                String email = JwtUtil.extractEmailFromToken(token); // Assuming the email is stored in the token

                // Fetch user details from the database using the extracted email
                User user = userService.findUserByEmail(email);

                if (user != null) {
                    // Return user details along with token validity message
                    return ResponseEntity.ok(Map.of(
                        "message", "Token is valid",
                        "user", Map.of(
                            "email", user.getEmail(),
                            "name", user.getName(),
                           "role", user.getRoles(),
                           "id", user.getId() //Add other user details as necessary
                        )
                    ));
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "User not found"));
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Token is invalid or expired"));
            }
        } catch (Exception e) {
            logger.error("Error during token validation: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("message", "Error validating token"));
        }
    }


}

