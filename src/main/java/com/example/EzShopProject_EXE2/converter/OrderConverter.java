package com.example.EzShopProject_EXE2.converter;

import com.example.EzShopProject_EXE2.dto.OrderDTO;
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

    public OrderDTO toDto(Order order) {
        OrderDTO orderDto = OrderDTO.builder()
                .id(order.getId())
                .orderDate(order.getOrderDate())
                .userId(order.getUser().getId())
                .shopId(order.getShopId())
                .status(order.getStatus())
                .customerName(order.getUser().getUsername())
                .build();
        return orderDto;
    }

    public Order toEntity(OrderDTO orderDto) {
        Order order = Order
                .builder()
                .id(orderDto.getId())
                .orderDate(orderDto.getOrderDate())
                .shopId(orderDto.getShopId())
                .status(orderDto.getStatus())
                .build();
        User user = userRepository.findById(orderDto.getUserId()).get();
        order.setUser(user);
        return order;
    }

    public List<OrderDTO> toDto(List<Order> orders) {
        return orders.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
}
