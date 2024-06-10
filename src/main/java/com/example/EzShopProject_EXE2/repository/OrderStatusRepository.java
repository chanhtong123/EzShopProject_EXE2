package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Long> {
}