package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.model.Cart;
import com.example.EzShopProject_EXE2.service.impl.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@CrossOrigin
public class CartController {
    private final CartService cartService;



    @GetMapping("/user")
    public ResponseEntity<Cart> getCartByToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = extractTokenFromHeader(authorizationHeader);
        Cart cart = cartService.getCartByToken(token);
        return ResponseEntity.ok(cart);
    }

    private String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7).trim(); // Loại bỏ tiền tố "Bearer" và khoảng trắng
        } else {
            throw new IllegalArgumentException("Authorization header is missing or invalid");
        }
    }




}
