package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.model.CartDetail;
import com.example.EzShopProject_EXE2.repository.CartDetailRepository;
import com.example.EzShopProject_EXE2.service.ICartDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartDetailService implements ICartDetailService {

    private final CartDetailRepository cartDetailRepository;

    @Override
    public CartDetail createCartDetail(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

    @Override
    public Optional<CartDetail> getCartDetailById(Long id) {
        return cartDetailRepository.findById(id);
    }

    @Override
    public List<CartDetail> getAllCartsDetail() {
        return cartDetailRepository.findAll();
    }

    @Override
    public CartDetail updateCartDetail(Long id, CartDetail cartDetail) {
        if (cartDetailRepository.existsById(id)) {
            cartDetail.setId(id);
            return cartDetailRepository.save(cartDetail);
        }
        return null;
    }

    @Override
    public void deleteCartDetail(Long id) {
         cartDetailRepository.deleteById(id);
    }
}
