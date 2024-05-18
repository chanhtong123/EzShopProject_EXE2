package com.example.EzShopProject_EXE2.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDTO {
    private Long id;
    private Long orderId;
    private Long userId;
    private CartDetailDTO cartDetail;

}
