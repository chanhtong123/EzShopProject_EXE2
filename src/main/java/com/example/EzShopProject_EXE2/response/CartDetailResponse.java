package com.example.EzShopProject_EXE2.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartDetailResponse {

    private Long id;
    private double price;
    private Date createdAt;
    private CartResponse cart;
    private ShopResponse shop;
    private ProductResponse product;
}