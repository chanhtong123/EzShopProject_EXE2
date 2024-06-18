package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.model.Cart;

import java.util.List;
import java.util.Optional;

public interface    ICartService {
    Cart getCartByUserId(Long userId);
    Cart getCartByToken(String token);

    Cart createCart(Long id);
    public Cart getCartById(Long cartId);
}