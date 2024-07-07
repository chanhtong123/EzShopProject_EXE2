package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.OrderDto;
import com.example.EzShopProject_EXE2.dto.analysis.OrderStatsDTO;
import com.example.EzShopProject_EXE2.dto.analysis.RevenueDTO;
import com.example.EzShopProject_EXE2.dto.analysis.RevenueDayDTO;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface IOrderService {
    Page<OrderDto> findAll(Pageable pageable);

    OrderDto findById(long id);

    @Transactional
    Order save(OrderDto orderDto) throws Exception;

    void delete(long id);

//    OrderDto update(long id, @Valid OrderDto OrderDto);

    @Transactional
    Order update(Long id, OrderDto orderDTO) throws DataNotFoundException;

    Page<OrderDto> findByUserId(Long userId, Pageable pageable);

    OrderStatsDTO getOrderStats();

    RevenueDTO getRevenueStatistics();

    Double calculateRevenueChangePercentage(Double currentMonthRevenue, Double lastMonthRevenue);

    RevenueDayDTO getTotalSales();

    Integer countOrdersByProductId(Long productId);

    Optional<Double> getProductRevenue(Long productId);

    List<OrderDto> findOrdersByOrderDateBetween(LocalDateTime startDate, LocalDateTime endDate);


    Page<OrderDto> findByShopId(Long shopId, Pageable pageable);
}