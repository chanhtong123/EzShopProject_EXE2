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
    public Shop getShopById(long id) throws Exception {
        Shop result = null;
        Optional<Shop> optionalResult;
        try {
            optionalResult = shopRepository.findById(id);
            if (optionalResult.isPresent()){
                result = optionalResult.get();
            }
        }catch (Exception exception){
            return null;
        }
        return result;
    }

    @Override
    public Shop getShopByOwnerId(long id) {
        Shop result = null;
        Optional<Shop> optionalResult;
        try {
            optionalResult = shopRepository.getShopByOwnerId(id);
            if (optionalResult.isPresent()){
                result = optionalResult.get();
            }
        }catch (Exception exception){
            return null;
        }
        return result;
    }

    @Override
    public void createNewShop(Shop shop) throws Exception {
        try{
            Optional<Shop> optionalShop = shopRepository.findById(shop.getShopId());
            if (optionalShop.isPresent()){
                throw new Exception("Exist!");
            }else{
                shopRepository.save(shop);
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updateShop(Shop shop) throws Exception {
        try{
            Optional<Shop> optionalShop = shopRepository.findById(shop.getShopId());
            if (optionalShop.isPresent()){
                Shop curentShop = optionalShop.get();
                curentShop = shop;
                shopRepository.save(curentShop);
            }else{
                throw new Exception("Exist!");
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<Shop> getShop(ShopSearchRequest request) {
        List<Shop> result = new ArrayList<>();
        try{
            result = shopRepository.getShop(request.getName());
            return result;
        }catch (Exception e){
            return null;
        }
    }


}
