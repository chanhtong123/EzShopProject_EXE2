package com.example.EzShopProject_EXE2.dto;

import com.example.EzShopProject_EXE2.model.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import lombok.*;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private Long id;

    @NotNull(message = "Order date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderDate;

    private OrderStatus status;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Shop ID cannot be null")
    private Long shopId;

    private Long orderDetailId;

    @Min(value = 1, message = "Total money must be >= 0")
    private double totalAmount;

    private String customerName;

    private double profit;
}