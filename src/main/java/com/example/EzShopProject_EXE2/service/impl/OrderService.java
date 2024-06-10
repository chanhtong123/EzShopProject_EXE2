package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.converter.OrderConverter;
import com.example.EzShopProject_EXE2.dto.OrderDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.Order;
import com.example.EzShopProject_EXE2.model.OrderStatus;
import com.example.EzShopProject_EXE2.model.User;
import com.example.EzShopProject_EXE2.repository.OrderRepository;
import com.example.EzShopProject_EXE2.repository.OrderStatusRepository;
import com.example.EzShopProject_EXE2.repository.UserRepository;
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
    private final OrderConverter orderConverter;
    private final UserRepository userRepository;
    private final OrderStatusRepository orderStatusRepository;

    @Override
    public List<OrderDto> findAll() {
        try {
            List<Order> orders = orderRepository.findAll();
            return orderConverter.toDto(orders);
        } catch (Exception e) {
            logger.error("Error finding all orders", e);

            throw new RuntimeException("Error finding all orders", e);
        }
    }

    @Override
    public OrderDto findById(long id) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            if (order.isPresent()) {
                Order existingOrder = order.get();
                return orderConverter.toDto(existingOrder);
            }
            return null;
        } catch (Exception e) {
            logger.error("Error finding order by ID", e);
            throw new RuntimeException("Error finding order by ID", e);
        }
    }

    @Override
    public OrderDto save(OrderDto OrderDto) {
        try {
            Order order = orderConverter.toEntity(OrderDto);
            Order savedOrder = orderRepository.save(order);
            return orderConverter.toDto(savedOrder);
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
    public OrderDto update(long id, @Valid OrderDto OrderDto) {
        try {
            User existingUser = userRepository.findById(OrderDto.getUserId())
                    .orElseThrow(()-> new DataNotFoundException("Cannot find user with id: " + id));
            OrderStatus orderStatus = orderStatusRepository.findById(OrderDto.getStatus().getId())
                    .orElse(OrderDto.getStatus());
            Optional<Order> existingOrderOpt = orderRepository.findById(id);
            if (existingOrderOpt.isPresent()) {

                Order existingOrder = existingOrderOpt.get();
                existingOrder.setOrderDate(OrderDto.getOrderDate());
                existingOrder.setStatus(OrderDto.getStatus());
                existingOrder.setUser(existingUser);
                existingOrder.setStatus(orderStatus);
                existingOrder.setShopId(OrderDto.getShopId());

                Order updatedOrder = orderRepository.save(existingOrder);
                return orderConverter.toDto(updatedOrder);
            } else {
                throw new RuntimeException("Order not found");
            }
        } catch (Exception e) {
            logger.error("Error updating order", e);
            throw new RuntimeException("Error updating order", e);
        }
    }

    @Override
    public List<OrderDto> findByUserId(Long userId) {
        try {
            List<Order> orders = orderRepository.findByUserId(userId);
            return orderConverter.toDto(orders);
        }catch (Exception e) {
            logger.error("Error finding order by ID", e);
            throw new RuntimeException("Error finding order by ID", e);
        }
    }
}