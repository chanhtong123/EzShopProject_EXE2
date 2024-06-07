package com.example.EzShopProject_EXE2.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ShopResponse {
    private Long shopId;
    private String nameShop;
    private String address;
    private String phoneNumber;
    private String image;
    private String backgroundImage;
    private double wallet;
    private boolean status;
    private String owner;
}
