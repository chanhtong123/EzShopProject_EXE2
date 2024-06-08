package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.dto.CartDetailDto;
import com.example.EzShopProject_EXE2.dto.ProductDto;
import com.example.EzShopProject_EXE2.model.CartDetail;
import com.example.EzShopProject_EXE2.model.Product;
import com.example.EzShopProject_EXE2.repository.CartDetailRepository;
import com.example.EzShopProject_EXE2.service.ICartDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartDetailService implements ICartDetailService {

    private final CartDetailRepository cartDetailRepository;

    @Override
    public CartDetail createCartDetail(CartDetail cartDetail) {
        return cartDetailRepository.save(cartDetail);
    }

//    @Override
//    public List<CartDetail> getCartDetailsByCartId(Long cartId) {
//        return cartDetailRepository.findByCartId(cartId);
//    }


    @Override
    public List<CartDetailDto> getCartDetailsByCartId(Long cartId) {
        List<CartDetail> cartDetails = cartDetailRepository.findByCartId(cartId);
        return cartDetails.stream().map(this::convertToCartDetailDTO).collect(Collectors.toList());
    }

    private CartDetailDto convertToCartDetailDTO(CartDetail cartDetail) {
        CartDetailDto dto = new CartDetailDto();
        dto.setId(cartDetail.getId());
        dto.setPrice(cartDetail.getPrice());
        dto.setCreatedAt(cartDetail.getCreatedAt());
        dto.setCart(cartDetail.getCart());
        dto.setShop(cartDetail.getShop());
        dto.setProduct(convertToProductDTO(cartDetail.getProduct()));
        return dto;
    }

    private ProductDto convertToProductDTO(Product product) {
        ProductDto dto = new ProductDto();
        dto.setId(product.getId());
        dto.setImage(product.getImage());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCode(product.getCode());
        dto.setStatus(product.getStatus());
        dto.setQuantity(product.getQuantity());
        dto.setBrand(product.getBrand());
        dto.setWeight(product.getWeight());
        dto.setSituation(product.getSituation());
        dto.setColor(product.getColor());
        dto.setSize(product.getSize());
        dto.setDetail(product.getDetail());
        dto.setOverview(product.getOverview());
        return dto;
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
    public void deleteCartDetail(Long id) {
         cartDetailRepository.deleteById(id);
    }
}
