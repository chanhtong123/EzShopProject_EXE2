package com.example.EzShopProject_EXE2.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDetailDto {
    private Long id;

    private int quantity;

    private double price;

    private Long productId;
}
