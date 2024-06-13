package com.example.EzShopProject_EXE2.response;

import com.example.EzShopProject_EXE2.dto.CartItemDto;
import com.example.EzShopProject_EXE2.model.OrderDetail;
import com.example.EzShopProject_EXE2.model.enums.PaymentStatus;
import com.example.EzShopProject_EXE2.model.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse extends BaseResponse {
    private Long id;

    @JsonProperty("total_amount")
    @Min(value = 1, message = "Total money must be >= 0")
    private double totalAmount;

    @JsonProperty("profit")
    private double profit;

    @JsonProperty("payment_method")
    private String paymentMethod;

    @JsonProperty("shipping_method")
    private String shippingMethod;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("shipping_date")
    private LocalDate shippingDate;

    private String notes;


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

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;

    @JsonProperty("cart_items")
    private List<CartItemDto> cartItems;

    private Long couponId;

    @JsonProperty("user_id")
    @Min(value = 1, message = "User's ID must be > 0")
    private Long userId;

    @JsonProperty("order_details")
    private List<OrderDetail> orderDetails;
}