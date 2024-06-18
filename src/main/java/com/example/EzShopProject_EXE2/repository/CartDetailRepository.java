package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.Cart;
import com.example.EzShopProject_EXE2.model.CartDetail;
import com.example.EzShopProject_EXE2.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface CartDetailRepository extends JpaRepository<CartDetail, Long> {
    List<CartDetail> findByCartId(Long cartId);

    CartDetail findByCartAndProduct(Cart cart, Product product);
}