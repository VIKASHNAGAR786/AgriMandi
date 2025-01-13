package com.example.AgriMandi.controller;

import com.example.AgriMandi.entity.Product;
import com.example.AgriMandi.entity.User;
import com.example.AgriMandi.repository.UserRepository;
import com.example.AgriMandi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @PostMapping
//    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
//        Product createdProduct = productService.addProduct(product);
//        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
//    }
    
    
    @Autowired
    private UserRepository userRepository;  // Add UserRepository for fetching User

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        // Ensure that the user_id is not null and fetch the User from the database
//        if (product.getUser() == null || product.getUser().getId() == null) {
//            return new ResponseEntity<>("User ID cannot be null", HttpStatus.BAD_REQUEST);
//        }

        User user = userRepository.findById(product.getUser().getId())
                                  .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + product.getUser().getId()));

        product.setUser(user);  // Set the valid User object

        Product createdProduct = productService.addProduct(product);
        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
    }
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

//
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        Product product = productService.getProductById(id);  // Call service method to fetch product by ID
//        return new ResponseEntity<>(product, HttpStatus.OK);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);  // Get the product from the service layer
        return new ResponseEntity<>(product, HttpStatus.OK);  // Return the product in response with 200 OK
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Product>> getProductsByUserId(@PathVariable Long userId) {
        List<Product> products = productService.getProductsByUserId(userId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product updatedProduct = productService.updateProduct(id, product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
