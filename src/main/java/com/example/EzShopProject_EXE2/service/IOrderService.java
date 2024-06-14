package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.OrderDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;


import java.util.List;

public interface IOrderService {
    List<OrderDto> findAll();

    OrderDto findById(long id);

    @Transactional
    Order save(OrderDto orderDto) throws Exception;

    void delete(long id);

//    OrderDto update(long id, @Valid OrderDto OrderDto);

    @Transactional
    Order update(Long id, OrderDto orderDTO) throws DataNotFoundException;

    List<OrderDto> findByUserId(Long userId);
}