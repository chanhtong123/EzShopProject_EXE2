package com.example.EzShopProject_EXE2.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class ShopDto {
    private Long shopId;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phoneNumber;

    private double wallet;

    private int status;

    private Long owner;
}

