package com.example.EzShopProject_EXE2.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {

    private Long id;
    private String name;
    private double price;
    private String description;
    private String code;
    private String status;
    private int quantity;
    private String brand;
    private int weight;
    private int situation;
    private String color;
    private String overview;
    private String image;

}
