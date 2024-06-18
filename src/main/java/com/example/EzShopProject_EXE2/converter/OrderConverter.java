package com.example.EzShopProject_EXE2.converter;

import com.example.EzShopProject_EXE2.dto.OrderDto;
import com.example.EzShopProject_EXE2.model.Order;
import com.example.EzShopProject_EXE2.model.User;
import com.example.EzShopProject_EXE2.repository.UserRepository;
import com.example.EzShopProject_EXE2.response.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class OrderConverter {
    private final UserRepository userRepository;

    public OrderConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public OrderDto toDto(Order order) {
        OrderDto orderDto = OrderDto.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .userId(order.getUser().getId())
                .orderStatus(order.getStatus())
                .totalAmount(order.getTotalAmount())
                .profit(order.getProfit())
                .paymentStatus(order.getPaymentStatus())
                .shopId(order.getShopId())
                .active(order.getActive())
                .address(order.getAddress())
                .province(order.getProvince())
                .district(order.getDistrict())
                .ward(order.getWard())
                .phoneNumber(order.getPhoneNumber())
                .email(order.getEmail())
                .fullName(order.getFullName())
                .notes(order.getNotes())
                .shippingDate(order.getShippingDate())
                .shippingMethod(order.getShippingMethod())
                .paymentMethod(order.getPaymentMethod())
                .customerName(order.getUser().getUsername())
                .build();
        return orderDto;
    }

    public Order toEntity(OrderDto OrderDto) {
        Order order = Order
                .builder()
                .id(OrderDto.getId())
                .orderDate(OrderDto.getOrderDate())
                .status(OrderDto.getOrderStatus())
                .shopId(OrderDto.getShopId())
                .active(OrderDto.getActive())
                .address(OrderDto.getAddress())
                .province(OrderDto.getProvince())
                .district(OrderDto.getDistrict())
                .ward(OrderDto.getWard())
                .phoneNumber(OrderDto.getPhoneNumber())
                .email(OrderDto.getEmail())
                .fullName(OrderDto.getFullName())
                .notes(OrderDto.getNotes())
                .shippingDate(OrderDto.getShippingDate())
                .shippingMethod(OrderDto.getShippingMethod())
                .paymentMethod(OrderDto.getPaymentMethod())
                .totalAmount(OrderDto.getTotalAmount())
                .profit(OrderDto.getProfit())
                .build();
        Optional<User> user = userRepository.findById(OrderDto.getUserId());
        user.ifPresent(order::setUser);
        return order;
    }

    public List<OrderDto> toDto(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public static OrderResponse fromOrder(Order order) {
        OrderResponse orderResponse =  OrderResponse
                .builder()
                .id(order.getId())
                .userId(order.getUser().getId())
                .active(order.getActive())
                .address(order.getAddress())
                .province(order.getProvince())
                .district(order.getDistrict())
                .ward(order.getWard())
                .phoneNumber(order.getPhoneNumber())
                .email(order.getEmail())
                .fullName(order.getFullName())
                .notes(order.getNotes())
                .shippingDate(order.getShippingDate())
                .shippingMethod(order.getShippingMethod())
                .paymentMethod(order.getPaymentMethod())
                .totalAmount(order.getTotalAmount())
                .profit(order.getProfit())
                .paymentStatus(order.getPaymentStatus())
                .orderDetails(order.getOrderDetails())
                .build();
        orderResponse.setCreatedAt(order.getCreatedAt());
        orderResponse.setUpdatedAt(order.getUpdatedAt());
        return orderResponse;
    }
}