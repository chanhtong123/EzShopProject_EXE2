package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.OrderDTO;
import javax.validation.Valid;

import java.util.List;

public interface IOrderService {
    List<OrderDTO> findAll();

    OrderDTO findById(long id);

    OrderDTO save(OrderDTO orderDTO);

    void delete(long id);

    OrderDTO update(long id, @Valid OrderDTO orderDTO);
}
