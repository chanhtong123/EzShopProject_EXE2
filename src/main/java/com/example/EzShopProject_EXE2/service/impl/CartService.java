package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.model.Cart;
import com.example.EzShopProject_EXE2.model.CartDetail;
import com.example.EzShopProject_EXE2.repository.CartDetailRepository;
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
    private final CartDetailRepository cartDetailRepository;

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
        Optional<Cart> existingCartOptional = cartRepository.findById(id);
        if (existingCartOptional.isPresent()) {
            Cart existingCart = existingCartOptional.get();
            if (cart.getOrderId() != null) {
                existingCart.setOrderId(cart.getOrderId());
            }
            if (cart.getUserId() != null) {
                existingCart.setUserId(cart.getUserId());
            }
            if (cart.getCartDetail() != null) {
                CartDetail cartDetail = cart.getCartDetail();
                if (cartDetail.getId() != null) {
                    Optional<CartDetail> existingCartDetailOptional = cartDetailRepository.findById(cartDetail.getId());
                    if (existingCartDetailOptional.isPresent()) {
                        CartDetail existingCartDetail = existingCartDetailOptional.get();
                        if (cartDetail.getProductId() != null) {
                            existingCartDetail.setProductId(cartDetail.getProductId());
                        }
                        if (cartDetail.getQuantity() != 0) {
                            existingCartDetail.setQuantity(cartDetail.getQuantity());
                        }

                        existingCart.setCartDetail(cartDetailRepository.save(existingCartDetail));
                    }
                } else {
                    existingCart.setCartDetail(cartDetailRepository.save(cartDetail));
                }
            }
            return cartRepository.save(existingCart);
        } else {
            return null;
        }
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }
}
