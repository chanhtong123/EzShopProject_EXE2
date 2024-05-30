package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.OrderDto;
import jakarta.validation.Valid;

import java.util.List;

public interface IOrderService {
    List<OrderDto> findAll();

    OrderDto findById(long id);

    OrderDto save(OrderDto orderDTO);

    void delete(long id);

    OrderDto update(long id, @Valid OrderDto orderDTO);
}
