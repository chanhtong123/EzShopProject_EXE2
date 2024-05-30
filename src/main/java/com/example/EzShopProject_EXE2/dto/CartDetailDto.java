package com.example.EzShopProject_EXE2.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetailDto {
    private Long id;
    private Long productId;
    private int quantity;
}
