package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.model.Shop;
import com.example.EzShopProject_EXE2.repository.ShopRepository;
import com.example.EzShopProject_EXE2.request.shop.ShopSearchRequest;
import com.example.EzShopProject_EXE2.service.IShopService;
import jdk.jshell.spi.ExecutionControlProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShopService implements IShopService {

    private final ShopRepository shopRepository;

    @Autowired
    public ShopService(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }


    @Override
    public Optional<Shop> getShopById(Long id) {
        return shopRepository.findById(id);
    }

    @Override
    public List<Shop> getAllShop() {
        return  shopRepository.findAll();
    }
}
