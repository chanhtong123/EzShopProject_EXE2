package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByNameContaining(String name);
    List<Product> findByPrice(Double price);
    List<Product> findByBrand(Integer brand);
    List<Product> findByNameContainingAndPriceAndBrand(String name, Double price, Integer brand);
}
