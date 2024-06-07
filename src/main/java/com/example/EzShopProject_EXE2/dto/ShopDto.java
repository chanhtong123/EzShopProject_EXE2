package com.example.EzShopProject_EXE2.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ShopDto {
    private Long shopId;

    @NotBlank
    private String name;

    @NotBlank
    private String address;
    private String image;
    private String backgroundImage;

    @NotBlank
    private String phoneNumber;

    private double wallet;

    private boolean status;

    private String owner;
}

