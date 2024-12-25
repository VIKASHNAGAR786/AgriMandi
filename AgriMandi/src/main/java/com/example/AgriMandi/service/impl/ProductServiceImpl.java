////package com.example.AgriMandi.service.impl;
////
////
////
////import com.example.AgriMandi.entity.Product;
////import com.example.AgriMandi.repository.ProductRepository;
////import com.example.AgriMandi.service.ProductService;
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.stereotype.Service;
////
////import java.util.List;
////
////@Service
////public class ProductServiceImpl implements ProductService {
////
////    @Autowired
////    private ProductRepository productRepository;
////
////    @Override
////    public Product addProduct(Product product) {
////        return productRepository.save(product);
////    }
////
////    @Override
////    public List<Product> getAllProducts() {
////        return productRepository.findAll();
////    }
////
////    @Override
////    public Product updateProduct(Long productId, Product product) {
////        Product existingProduct = productRepository.findById(productId).orElseThrow(
////                () -> new IllegalArgumentException("Product not found.")
////        );
////        existingProduct.setName(product.getName());
////        existingProduct.setPrice(product.getPrice());
////        existingProduct.setQuantity(product.getQuantity());
////        existingProduct.setDescription(product.getDescription());
////        return productRepository.save(existingProduct);
////    }
////
////    @Override
////    public void deleteProduct(Long productId) {
////        productRepository.deleteById(productId);
////    }
////}
////
//
//package com.example.AgriMandi.service.impl;
//
//import com.example.AgriMandi.entity.Product;
//import com.example.AgriMandi.repository.ProductRepository;
//import com.example.AgriMandi.service.ProductService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class ProductServiceImpl implements ProductService {
//
//    @Autowired
//    private ProductRepository productRepository;
//
//    // Implementing addProduct (from ProductService interface)
//    @Override
//    public Product addProduct(Product product) {
//        return productRepository.save(product);
//    }
//
//    // Method to get all products
//    @Override
//    public List<Product> getAllProducts() {
//        return productRepository.findAll();
//    }
//
//    // Method to get a product by its ID
//    @Override
//    public Product getProductById(Long id) {
//        return productRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
//    }
//
//    // Method to update a product
//    @Override
//    public Product updateProduct(Long id, Product product) {
//        Product existingProduct = productRepository.findById(id)
//                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
//
//        // Update fields
//        existingProduct.setName(product.getName());
//        existingProduct.setPrice(product.getPrice());
//        existingProduct.setQuantity(product.getQuantity());
//        existingProduct.setDescription(product.getDescription());
//
//        return productRepository.save(existingProduct);
//    }
//
//    // Method to delete a product
//    @Override
//    public void deleteProduct(Long id) {
//        productRepository.deleteById(id);
//    }
//}






package com.example.AgriMandi.service.impl;

import com.example.AgriMandi.entity.Product;
import com.example.AgriMandi.repository.ProductRepository;
import com.example.AgriMandi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    // Implementing addProduct (from ProductService interface)
    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    // Method to get all products
    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Method to get a product by its ID
    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));
    }

    // Method to update a product
    @Override
    public Product updateProduct(Long id, Product product) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + id));

        // Update fields
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setQuantity(product.getQuantity());
        existingProduct.setDescription(product.getDescription());

        return productRepository.save(existingProduct);
    }

    // Method to delete a product
    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
