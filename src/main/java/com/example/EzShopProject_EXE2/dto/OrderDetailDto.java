package com.example.EzShopProject_EXE2.dto;

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

    @Positive(message = "Quantity must be a positive number")
    private int quantity;

    @PositiveOrZero(message = "Price must be a positive number or zero")
    private double price;

    @NotNull(message = "Product ID cannot be null")
    private Long productId;
}