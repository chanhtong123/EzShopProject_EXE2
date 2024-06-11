package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.Shop;
import com.example.EzShopProject_EXE2.request.shop.ShopSearchRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ShopRepository extends JpaRepository<Shop,Long> {
}
