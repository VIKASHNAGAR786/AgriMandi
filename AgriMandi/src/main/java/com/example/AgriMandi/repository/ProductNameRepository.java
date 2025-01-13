package com.example.AgriMandi.repository;

import com.example.AgriMandi.entity.ProductName;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductNameRepository extends JpaRepository<ProductName, Long> {
    List<ProductName> findAll();
}
