package com.example.EzShopProject_EXE2.converter;

import com.example.EzShopProject_EXE2.dto.OrderDetailDto;
import com.example.EzShopProject_EXE2.model.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDetailConverter {
    public static OrderDetailDto toDto(OrderDetail orderDetail) {
        return OrderDetailDto.builder()
                .id(orderDetail.getId())
                .price(orderDetail.getPrice())
                .productId(orderDetail.getId())
                .quantity(orderDetail.getQuantity())
                .build();
    }

    public static OrderDetail toEntity(OrderDetailDto orderDetailDto) {
        return OrderDetail.builder()
                .id(orderDetailDto.getId())
                .price(orderDetailDto.getPrice())
                .quantity(orderDetailDto.getQuantity())
                .build();
    }

    public static List<OrderDetailDto> toDto(List<OrderDetail> orderDetails) {
        return orderDetails.stream()
                .map(OrderDetailConverter::toDto)
                .collect(Collectors.toList());
    }
}
