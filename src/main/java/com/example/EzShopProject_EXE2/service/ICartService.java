package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.model.Cart;

import java.util.List;
import java.util.Optional;

public interface ICartService {
    Cart createCart(Cart cart);
    Optional<Cart> getCartById(Long id);
    List<Cart> getAllCarts();
    Cart updateCart(Long id, Cart cart);
    void deleteCart(Long id);
}
