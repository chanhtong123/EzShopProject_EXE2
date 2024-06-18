package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.OrderDetailDto;
import com.example.EzShopProject_EXE2.model.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailDto findById(long id);

    List<OrderDetailDto> findAll();

    OrderDetailDto create(OrderDetail orderDetail);

    OrderDetailDto update(long id, OrderDetail orderDetail);

    void delete(long id);

    List<OrderDetailDto> getOrderDetailByOrderId(long id);
}
