package com.example.EzShopProject_EXE2.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;



@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDto {
    private Long id;

    private String name;

    @PositiveOrZero(message = "Price must be a positive number or zero")
    private double price;

    @NotNull(message = "Product ID cannot be null")
    private Long productId;

}