package com.example.EzShopProject_EXE2.converter;

import com.example.EzShopProject_EXE2.dto.OrderDto;
import com.example.EzShopProject_EXE2.model.Order;
import com.example.EzShopProject_EXE2.model.User;
import com.example.EzShopProject_EXE2.repository.UserRepository;
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
                .shopId(order.getShopId())
                .status(order.getStatus())
                .customerName(order.getUser().getUsername())
                .build();
        return orderDto;
    }

    public Order toEntity(OrderDto OrderDto) {
        Order order = Order
                .builder()
                .id(OrderDto.getId())
                .orderDate(OrderDto.getOrderDate())
                .shopId(OrderDto.getShopId())
                .status(OrderDto.getStatus())
                .build();
        User user = userRepository.findById(OrderDto.getUserId()).get();
        order.setUser(user);
        return order;
    }

    public List<OrderDto> toDto(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}