package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository< Cart, Long> {
    Optional<Cart> findByUserId(Long userId);

}
