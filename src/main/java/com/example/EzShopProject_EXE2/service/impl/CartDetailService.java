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
        Optional<CartDetail> existingCartDetailOptional = cartDetailRepository.findById(id);
        if (existingCartDetailOptional.isPresent()) {
            CartDetail existingCartDetail = existingCartDetailOptional.get();

            if (cartDetail.getProductId() != null) {
                existingCartDetail.setProductId(cartDetail.getProductId());
            }
            if (cartDetail.getQuantity() != 0) {
                existingCartDetail.setQuantity(cartDetail.getQuantity());
            }

            return cartDetailRepository.save(existingCartDetail);
        } else {
            return null;
        }
    }


    @Override
    public void deleteCartDetail(Long id) {
         cartDetailRepository.deleteById(id);
    }
}
