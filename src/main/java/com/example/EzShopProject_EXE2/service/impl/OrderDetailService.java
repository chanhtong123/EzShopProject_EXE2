package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.converter.OrderDetailConverter;
import com.example.EzShopProject_EXE2.dto.OrderDetailDto;
import com.example.EzShopProject_EXE2.model.OrderDetail;
import com.example.EzShopProject_EXE2.repository.OrderDetailRepository;
import com.example.EzShopProject_EXE2.service.IOrderDetailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailService implements IOrderDetailService {

    private static final Logger logger = LoggerFactory.getLogger(OrderDetailService.class);
    private final OrderDetailRepository orderDetailRepository;

    @Override
    public OrderDetailDto findById(long id) {
        try {
            Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
            return orderDetail.map(OrderDetailConverter::toDto).orElse(null);
        } catch (Exception e) {
            logger.error("Error finding order detail by ID", e);
            throw new RuntimeException("Error finding order detail by ID", e);
        }
    }

    @Override
    public List<OrderDetailDto> findAll() {
        try {
            List<OrderDetail> orderDetails = orderDetailRepository.findAll();
            return OrderDetailConverter.toDto(orderDetails);
        } catch (Exception e) {
            logger.error("Error finding all order details", e);
            throw new RuntimeException("Error finding all order details", e);
        }
    }

    @Override
    public OrderDetailDto create(OrderDetail orderDetail) {
        try {
            OrderDetail savedOrderDetail = orderDetailRepository.save(orderDetail);
            return OrderDetailConverter.toDto(savedOrderDetail);
        } catch (Exception e) {
            logger.error("Error creating order detail", e);
            throw new RuntimeException("Error creating order detail", e);
        }
    }

    @Override
    public OrderDetailDto update(long id, OrderDetail orderDetail) {
        try {
            Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(id);
            if (orderDetailOptional.isPresent()) {
                OrderDetail orderDetailToUpdate = orderDetailOptional.get();
                orderDetailToUpdate.setPrice(orderDetail.getPrice());
                orderDetailToUpdate.setProduct(orderDetail.getProduct());
                return OrderDetailConverter.toDto(orderDetailRepository.save(orderDetailToUpdate));
            } else {
                throw new RuntimeException("Order detail not found");
            }
        } catch (Exception e) {
            logger.error("Error updating order detail", e);
            throw new RuntimeException("Error updating order detail", e);
        }
    }

    @Override
    public void delete(long id) {
        try {
            orderDetailRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting order detail", e);
            throw new RuntimeException("Error deleting order detail", e);
        }
    }

    @Override
    public List<OrderDetailDto> getOrderDetailByOrderId(long id) {
        try {
            List<OrderDetail> orderDetailOptional = orderDetailRepository.findByOrderId(id);
            List<OrderDetailDto> orderDetailDtos = OrderDetailConverter.toDto(orderDetailOptional);
            return orderDetailDtos;
        }catch (Exception e) {
            logger.error("Error getting order detail by ID", e);
            throw new RuntimeException("Error getting order detail by ID", e);
        }
    }

}
