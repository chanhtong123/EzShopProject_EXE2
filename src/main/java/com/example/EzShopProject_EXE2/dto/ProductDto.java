package com.example.EzShopProject_EXE2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
@Getter
@Setter
public class ProductDto {
    private Long id;

    @NotBlank
    @JsonProperty("product_name")
    private String name;

    @NotBlank
    private double price;

    @NotBlank
    private String description;

    @NotBlank
    private String code;

    @NotBlank
    private String status;

    private int quantity;
    private int category;
    private int brand;
    private int weight;

    @JsonProperty("categories")
    private Set<CategoryDto> categories = new HashSet<>();

    @JsonProperty("shop")
    private ShopDto shop;

    @JsonProperty("order_details")
    private Set<OrderDetailDto> orderDetails = new HashSet<>();
}
