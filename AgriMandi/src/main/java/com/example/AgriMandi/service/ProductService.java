//
//package com.example.AgriMandi.service;
//
//import com.example.AgriMandi.entity.Product;
//import java.util.List;
//
//public interface ProductService {
//    Product addProduct(Product product);  // Use addProduct instead of createProduct
//    List<Product> getAllProducts();
//    Product updateProduct(Long productId, Product product);
//    void deleteProduct(Long productId);
//    Product getProductById(Long id);
//}
package com.example.AgriMandi.service;

import com.example.AgriMandi.entity.Product;
import java.util.List;

public interface ProductService {
    Product addProduct(Product product);  // Use addProduct instead of createProduct
    List<Product> getAllProducts();
    Product updateProduct(Long productId, Product product);
    void deleteProduct(Long productId);
    Product getProductById(Long id);
}
