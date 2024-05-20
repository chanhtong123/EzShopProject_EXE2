package com.example.EzShopProject_EXE2.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailDTO {
    private Long id;

    @Positive(message = "Quantity must be a positive number")
    private int quantity;

    @PositiveOrZero(message = "Price must be a positive number or zero")
    private double price;

    @NotNull(message = "Product ID cannot be null")
    private Long productId;
}