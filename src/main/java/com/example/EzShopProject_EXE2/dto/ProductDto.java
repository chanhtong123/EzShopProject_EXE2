package com.example.EzShopProject_EXE2.dto;

import com.example.EzShopProject_EXE2.model.Shop;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;


    private String name;
    private String image;
    @NotBlank
    private double price;

    @NotBlank
    private String description;

    @NotBlank
    private String code;

    @NotBlank
    private String status;

    private int quantity;
    private String brand;
    private int weight;
    private int situation;
    private String overview;
    private String color;
    private String detail;
    private String size;

    @JsonProperty("categories")
    private Set<CategoryDto> categories = new HashSet<>();

    private Shop shop;

    @JsonProperty("order_details")
    private Set<OrderDetailDto> orderDetails = new HashSet<>();
    private TitleDto title;
}
