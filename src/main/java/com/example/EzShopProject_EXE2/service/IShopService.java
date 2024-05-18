package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.model.Shop;
import com.example.EzShopProject_EXE2.request.shop.ShopSearchRequest;

import java.util.List;

public interface IShopService {
    Shop getShopById(long id) throws Exception;

    Shop getShopByOwnerId(long id);

    void createNewShop(Shop shop) throws Exception;

    void updateShop(Shop shop) throws Exception;

    List<Shop> getShop(ShopSearchRequest request);
}
