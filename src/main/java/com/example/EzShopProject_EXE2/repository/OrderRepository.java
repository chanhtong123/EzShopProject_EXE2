package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order,Long> {
}