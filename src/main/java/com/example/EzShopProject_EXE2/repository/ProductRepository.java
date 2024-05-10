package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
