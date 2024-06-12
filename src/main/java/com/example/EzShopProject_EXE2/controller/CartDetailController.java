package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.dto.CartDetailDto;
import com.example.EzShopProject_EXE2.model.CartDetail;
import com.example.EzShopProject_EXE2.model.Product;
import com.example.EzShopProject_EXE2.response.*;
import com.example.EzShopProject_EXE2.service.ICartDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart_item")
@RequiredArgsConstructor
@CrossOrigin
public class CartDetailController {
    private final ICartDetailService iCartDetailService;


    @PostMapping("/cart-details/create")
    public ResponseEntity<CartDetailResponse> createCartDetail(@RequestParam Long productId,
                                                               @RequestParam Long cartId) {
        CartDetail cartDetail = iCartDetailService.createCartDetail(productId, cartId);

        CartDetailResponse cartDetailResponse = CartDetailResponse.builder()
                .id(cartDetail.getId())
                .price(cartDetail.getPrice())
                .createdAt(cartDetail.getCreatedAt())
                .cart(CartResponse.builder()
                        .id(cartDetail.getCart().getId())
                        .user(UserResponse.builder()
                                .id(cartDetail.getCart().getUser().getId())
                                .firstName(cartDetail.getCart().getUser().getFirstName())
                                .lastName(cartDetail.getCart().getUser().getLastName())
                                .email(cartDetail.getCart().getUser().getEmail())
                                .phone(cartDetail.getCart().getUser().getPhone())
                                .build())
                        .createdAt(cartDetail.getCart().getCreatedAt().toString())
                        .build())
                .shop(ShopResponse.builder()
                        .shopId(cartDetail.getShop().getId())
                        .nameShop(cartDetail.getShop().getNameShop())
                        .address(cartDetail.getShop().getAddress())
                        .phoneNumber(cartDetail.getShop().getPhoneNumber())
                        .build())
                .product(ProductResponse.builder()
                        .id(cartDetail.getProduct().getId())
                        .name(cartDetail.getProduct().getName())
                        .price(cartDetail.getProduct().getPrice())
                        .description(cartDetail.getProduct().getDescription())
                        .code(cartDetail.getProduct().getCode())
                        .build())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(cartDetailResponse);
    }

    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartDetailDto>> getCartDetailsByCartId(@PathVariable Long cartId) {
        List<CartDetailDto> cartDetails = iCartDetailService.getCartDetailsByCartId(cartId);
        return ResponseEntity.ok(cartDetails);
    }



    @GetMapping("/get_all")
    public ResponseEntity<List<CartDetail>> getAllCarts() {
        List<CartDetail> carts = iCartDetailService.getAllCartsDetail();
        return ResponseEntity.ok(carts);
    }



    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        iCartDetailService.deleteCartDetail(id);
        return ResponseEntity.noContent().build();
    }

}
