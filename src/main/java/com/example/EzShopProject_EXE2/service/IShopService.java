package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.model.CartDetail;
import com.example.EzShopProject_EXE2.model.Shop;
import com.example.EzShopProject_EXE2.request.shop.ShopSearchRequest;

import java.util.List;
import java.util.Optional;

public interface IShopService {
    Optional<Shop> getShopById(Long id);

    List<Shop> getAllShop();

}
