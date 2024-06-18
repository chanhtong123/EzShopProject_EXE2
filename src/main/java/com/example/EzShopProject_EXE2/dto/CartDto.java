package com.example.EzShopProject_EXE2.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDto {
    private Long id;
    private Long orderId;
    private Long userId;
    private CartDetailDto cartDetail;

}