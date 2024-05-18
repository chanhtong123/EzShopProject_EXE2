package com.example.EzShopProject_EXE2.converter;

import com.example.EzShopProject_EXE2.dto.OrderDTO;
import com.example.EzShopProject_EXE2.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverter {
    public static OrderDTO toDto(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .userId(order.getUserId())
                .shopId(order.getShopId())
            .build();

    }
    public static Order toEntity(OrderDTO orderDto) {
        return Order.builder()
                .id(orderDto.getId())
                .orderDate(orderDto.getOrderDate())
                .userId(orderDto.getUserId())
                .shopId(orderDto.getShopId())
                .build();
    }

    public static List<OrderDTO> toDto(List<Order> orders) {
        return orders.stream()
                .map(OrderConverter::toDto)
                .collect(Collectors.toList());
    }
}
