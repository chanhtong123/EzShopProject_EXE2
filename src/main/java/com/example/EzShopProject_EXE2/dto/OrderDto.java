package com.example.EzShopProject_EXE2.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
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

    private int status;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Shop ID cannot be null")
    private Long shopId;

    private Long orderDetailId;
}
