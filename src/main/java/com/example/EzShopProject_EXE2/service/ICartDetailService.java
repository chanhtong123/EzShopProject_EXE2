package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.model.CartDetail;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService {
    CartDetail createCartDetail(CartDetail cartDetail);

    Optional<CartDetail> getCartDetailById(Long id);
    List<CartDetail> getAllCartsDetail();

    CartDetail updateCartDetail(Long id, CartDetail cartDetail);

    void deleteCartDetail(Long id);
}
