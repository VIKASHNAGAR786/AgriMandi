package com.example.AgriMandi.service.impl;

import com.example.AgriMandi.entity.ProductName;
import com.example.AgriMandi.repository.ProductNameRepository;
import com.example.AgriMandi.service.ProductNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductNameServiceImpl implements ProductNameService {

    @Autowired
    private ProductNameRepository productNameRepository;

    @Override
    public List<ProductName> getAllProductNames() {
        return productNameRepository.findAll();
    }

    @Override
    public ProductName addProductName(ProductName productName) {
        return productNameRepository.save(productName);
    }
}
