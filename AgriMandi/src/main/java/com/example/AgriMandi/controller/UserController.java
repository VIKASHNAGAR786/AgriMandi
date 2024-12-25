

package com.example.AgriMandi.controller;

import com.example.AgriMandi.entity.User;
import com.example.AgriMandi.service.JwtUtil;
import com.example.AgriMandi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.saveUser(user);
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
        try {
            User user = userService.loginUser (email, password); // Validate user from DB
            String token = JwtUtil.generateToken(user.getEmail()); // Generate JWT token
            return ResponseEntity.ok(Map.of(
                "status", "success",
                "message", "Login successful",
                "token", token,
                "role", user.getRoles() // Add role if applicable
            ));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "status", "error",
                "message", e.getMessage()
            ));
        }
    }

}

