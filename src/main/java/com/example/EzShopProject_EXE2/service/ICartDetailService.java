package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.CartDetailDto;
import com.example.EzShopProject_EXE2.model.CartDetail;

import java.util.List;
import java.util.Optional;

public interface ICartDetailService {
    CartDetail createCartDetail(CartDetail cartDetail);
    List<CartDetailDto> getCartDetailsByCartId(Long cartId);

    Optional<CartDetail> getCartDetailById(Long id);
    List<CartDetail> getAllCartsDetail();
    void deleteCartDetail(Long id);


}
