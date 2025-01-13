package com.example.AgriMandi.service;

import com.example.AgriMandi.entity.ProductName;
import java.util.List;

public interface ProductNameService {
    List<ProductName> getAllProductNames();
    ProductName addProductName(ProductName productName);
}
