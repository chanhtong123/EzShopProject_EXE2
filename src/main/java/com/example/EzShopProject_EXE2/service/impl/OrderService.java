package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.converter.OrderConverter;
import com.example.EzShopProject_EXE2.dto.OrderDto;
import com.example.EzShopProject_EXE2.model.Order;
import com.example.EzShopProject_EXE2.repository.OrderRepository;
import com.example.EzShopProject_EXE2.service.IOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private static final Logger logger = LoggerFactory.getLogger(VoucherService.class);
    private final OrderRepository orderRepository;

    @Override
    public List<OrderDto> findAll() {
        try {
            List<Order> orders = orderRepository.findAll();
            return OrderConverter.toDto(orders);
        } catch (Exception e) {
            logger.error("Error finding all orders", e);

            throw new RuntimeException("Error finding all orders", e);
        }
    }

    @Override
    public OrderDto findById(long id) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            return order.map(OrderConverter::toDto).orElse(null);
        } catch (Exception e) {
            logger.error("Error finding order by ID", e);
            throw new RuntimeException("Error finding order by ID", e);
        }
    }

    @Override
    public OrderDto save(OrderDto orderDTO) {
        try {
            Order order = OrderConverter.toEntity(orderDTO);
            Order savedOrder = orderRepository.save(order);
            return OrderConverter.toDto(savedOrder);
        } catch (Exception e) {
            logger.error("Error creating order", e);
            throw new RuntimeException("Error creating order", e);
        }
    }

    @Override
    public void delete(long id) {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting order", e);

            throw new RuntimeException("Error deleting order", e);
        }
    }

    @Override
    public OrderDto update(long id, @Valid OrderDto orderDTO) {
        try {
            Optional<Order> existingOrderOpt = orderRepository.findById(id);
            if (existingOrderOpt.isPresent()) {
                Order existingOrder = existingOrderOpt.get();
                existingOrder.setOrderDate(orderDTO.getOrderDate());
                existingOrder.setStatus(orderDTO.getStatus());
                existingOrder.setUserId(orderDTO.getUserId());
                existingOrder.setShopId(orderDTO.getShopId());

                Order updatedOrder = orderRepository.save(existingOrder);
                return OrderConverter.toDto(updatedOrder);
            } else {
                throw new RuntimeException("Order not found");
            }
        } catch (Exception e) {
            logger.error("Error updating order", e);
            throw new RuntimeException("Error updating order", e);
        }
    }
}
