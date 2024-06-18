package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
    @Query("SELECT od FROM OrderDetail od WHERE od.orders.id = :orderId")
    List<OrderDetail> findByOrderId(@Param("orderId") Long orderId);
}