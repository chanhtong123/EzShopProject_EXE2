package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {
}