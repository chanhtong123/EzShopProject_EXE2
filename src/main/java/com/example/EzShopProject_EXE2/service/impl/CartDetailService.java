package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.dto.CartDetailDto;
import com.example.EzShopProject_EXE2.dto.ProductDto;
import com.example.EzShopProject_EXE2.model.*;
import com.example.EzShopProject_EXE2.repository.CartDetailRepository;
import com.example.EzShopProject_EXE2.repository.CartRepository;
import com.example.EzShopProject_EXE2.repository.ProductRepository;
import com.example.EzShopProject_EXE2.repository.ShopRepository;
import com.example.EzShopProject_EXE2.response.ProductResponse;
import com.example.EzShopProject_EXE2.service.ICartDetailService;
import com.example.EzShopProject_EXE2.service.ICartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartDetailService implements ICartDetailService {

    private final CartDetailRepository cartDetailRepository;
    private final JwtService jwtService;
    private final CartRepository cartRepository;
    private final AuthenticationService authenticationService;
    private final ICartService iCartService;
    private final ProductRepository productRepository;



//    @Override
//    public List<CartDetail> getCartDetailsByCartId(Long cartId) {
//        return cartDetailRepository.findByCartId(cartId);
//    }




    @Override
    public List<CartDetailDto> getCartDetailsByCartId(Long cartId) {
        List<CartDetail> cartDetails = cartDetailRepository.findByCartId(cartId);

        cartDetails.sort(Comparator.comparingLong(CartDetail::getId).reversed());

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

//    @Override
//    public CartDetail createCartDetail(Long productId, Long cartId) {
//
//        Product product = productRepository.findProductsById(productId);
//
//        Cart cart = iCartService.getCartById(cartId);
//
//        CartDetail cartDetail = new CartDetail();
//        cartDetail.setProduct(product);
//        cartDetail.setCreatedAt(new Date());
//        cartDetail.setPrice(product.getPrice());
//        cartDetail.setShop(product.getShop());
//        cartDetail.setCart(cart);
//
//        return cartDetailRepository.save(cartDetail);
//
//    }



    @Override
    public CartDetail createCartDetail(Long productId, Long cartId) {
        Product product = productRepository.findProductsById(productId);

        Cart cart = iCartService.getCartById(cartId);

        CartDetail existingCartDetail = cartDetailRepository.findByCartAndProduct(cart, product);

        if (existingCartDetail != null) {
            return existingCartDetail;
        }

        CartDetail cartDetail = new CartDetail();
        cartDetail.setProduct(product);
        cartDetail.setCreatedAt(new Date());
        cartDetail.setPrice(product.getPrice());
        cartDetail.setShop(product.getShop());
        cartDetail.setCart(cart);

        return cartDetailRepository.save(cartDetail);
    }

}



