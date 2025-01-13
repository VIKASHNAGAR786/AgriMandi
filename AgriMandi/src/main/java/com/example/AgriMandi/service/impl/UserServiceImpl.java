

package com.example.AgriMandi.service.impl;

import com.example.AgriMandi.entity.Role;
import com.example.AgriMandi.entity.RoleRepository;
import com.example.AgriMandi.entity.User;
import com.example.AgriMandi.repository.UserRepository;
import com.example.AgriMandi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    public User validateUser(String email, String password) {
        logger.info("Validating user with email: {}", email);
        User user = userRepository.findByEmail(email);

        if (user == null) {
            logger.warn("User not found for email: {}", email);
            return null;
        }

        if (!user.getPassword().equals(password)) {
            logger.warn("Password mismatch for email: {}", email);
            return null;
        }

        logger.info("User validated successfully: {}", email);
        return user;
    }

    @Override
    public User registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Email already in use.");
        }
        return userRepository.save(user);
    }

    @Override
    public User loginUser(String email, String password) {
        User user = validateUser(email, password);
        if (user == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return user;
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

//    @Override
//    public User saveUser(User user) {
//        return userRepository.save(user);
//    }
    
    @Autowired
    private RoleRepository roleRepository; // Make sure RoleRepository is also injected
    
    

    @Override
    public User saveUser(User user) {
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            // Assign a default role (e.g., FARMER)
            Role defaultRole = roleRepository.findByName("FARMER");
            if (defaultRole == null) {
                defaultRole = new Role();
                defaultRole.setName("FARMER");
                roleRepository.save(defaultRole);
            }
            Set<Role> defaultRoles = new HashSet<>();
            defaultRoles.add(defaultRole);
            user.setRole(defaultRoles);
        } else {
            Set<Role> persistedRoles = new HashSet<>();
            for (Role role : user.getRoles()) {
                Role existingRole = roleRepository.findByName(role.getName());
                if (existingRole != null) {
                    persistedRoles.add(existingRole);
                } else {
                    persistedRoles.add(roleRepository.save(role));
                }
            }
            user.setRole(persistedRoles);
        }
        return userRepository.save(user);
    }




    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Map<String, Object> getUserDetailsWithProduct(Long userId) {
        // Placeholder for user-product-related implementation
        return null;
    }

    // Method to find user by email (already present)
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email); // Returns null if no user is found
    }
}
