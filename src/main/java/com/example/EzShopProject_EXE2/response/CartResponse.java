package com.example.EzShopProject_EXE2.response;

import com.example.EzShopProject_EXE2.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {
    private Long id;
    private UserResponse user;
    private String createdAt;
    private long orderId;
}
