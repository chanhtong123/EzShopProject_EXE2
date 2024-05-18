package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.model.Cart;
import com.example.EzShopProject_EXE2.repository.CartRepository;
import com.example.EzShopProject_EXE2.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final CartRepository cartRepository;

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Optional<Cart> getCartById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart updateCart(Long id, Cart cart) {
        if (cartRepository.existsById(id)) {
            cart.setId(id);
            return cartRepository.save(cart);
        }
        return null;
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
