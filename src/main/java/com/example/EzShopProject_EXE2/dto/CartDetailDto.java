package com.example.EzShopProject_EXE2.dto;

import com.example.EzShopProject_EXE2.model.Cart;
import com.example.EzShopProject_EXE2.model.Shop;
import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailDto {
    private Long id;
    private double price;
    private Date createdAt;
    private Cart cart;
    private Shop shop;
    private ProductDto product;

}