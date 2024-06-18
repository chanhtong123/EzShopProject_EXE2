package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.model.Cart;
import com.example.EzShopProject_EXE2.repository.CartRepository;
import com.example.EzShopProject_EXE2.repository.UserRepository;
import com.example.EzShopProject_EXE2.service.ICartService;
import com.example.EzShopProject_EXE2.model.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public Cart getCartByToken(String token) {
        String username = jwtService.extractUserName(token);

        Optional<User> userOptional = userRepository.findByUserName(username);

        if (userOptional.isPresent()) {
            com.example.EzShopProject_EXE2.model.User user = userOptional.get();
            return getCartByUserId(user.getId());
        } else {
            throw new RuntimeException("User not found for username: " + username);
        }
    }

    @Override
    public Cart getCartById(Long cartId) {
        return cartRepository.findById(cartId)
                .orElseThrow(() -> new IllegalArgumentException("Không tìm thấy giỏ hàng với id: " + cartId));
    }

    @Override
    public Cart createCart(Long id) {
        Cart newCart = new Cart();
        newCart.setId(id);
        newCart.setCreatedAt(new Date());
        return cartRepository.save(newCart);
    }

}