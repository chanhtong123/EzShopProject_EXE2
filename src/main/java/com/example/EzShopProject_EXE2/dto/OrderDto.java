package com.example.EzShopProject_EXE2.dto;


import com.example.EzShopProject_EXE2.model.OrderDetail;
import com.example.EzShopProject_EXE2.model.enums.OrderStatus;
import com.example.EzShopProject_EXE2.model.enums.PaymentMethod;
import com.example.EzShopProject_EXE2.model.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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

    @NotNull(message = "Order status not null")
    private OrderStatus orderStatus;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotNull(message = "Shop ID cannot be null")
    private Long shopId;

    @Min(value = 1, message = "Total money must be >= 0")
    private double totalAmount;

    private String customerName;

    private double profit;

    @NotNull(message = "Payment status not null")
    private PaymentStatus paymentStatus;

    @JsonProperty("payment_method")
    private PaymentMethod paymentMethod;

    @JsonProperty("shipping_date")
    private LocalDate shippingDate;

    private String notes;

    private String discounts;

    @JsonProperty("fullName")
    private String fullName;

    private String email;

    @JsonProperty("phone_number")
    @NotBlank(message = "Phone number is required")
    @Size(min = 5, message = "Phone number must be at least 5 characters")
    private String phoneNumber;

    private String province;

    private String district;

    private String ward;

    private String address;

    private Boolean active;

    @JsonProperty("cart_items")
    private List<OrderDetailDto> cartItems;

}