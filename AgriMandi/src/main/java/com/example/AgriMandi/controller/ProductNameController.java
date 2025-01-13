package com.example.AgriMandi.controller;

import com.example.AgriMandi.entity.ProductName;
import com.example.AgriMandi.service.ProductNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product-names")
public class ProductNameController {

    @Autowired
    private ProductNameService productNameService;

    @PostMapping
    public ResponseEntity<ProductName> createProductName(@RequestBody ProductName productName) {
        ProductName createdProductName = productNameService.addProductName(productName);
        return new ResponseEntity<>(createdProductName, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductName>> getAllProductNames() {
        List<ProductName> productNames = productNameService.getAllProductNames();
        return new ResponseEntity<>(productNames, HttpStatus.OK);
    }
}
