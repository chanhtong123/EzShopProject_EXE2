package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.OrderDetailDTO;
import com.example.EzShopProject_EXE2.model.OrderDetail;

import java.util.List;

public interface IOrderDetailService {
    OrderDetailDTO findById(long id);

    List<OrderDetailDTO> findAll();

    OrderDetailDTO create(OrderDetail orderDetail);

    OrderDetailDTO update(long id, OrderDetail orderDetail);

    void delete(long id);
}
