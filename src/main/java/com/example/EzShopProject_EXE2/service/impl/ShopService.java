package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.model.Shop;
import com.example.EzShopProject_EXE2.repository.ProductRepository;
import com.example.EzShopProject_EXE2.repository.ShopRepository;
import com.example.EzShopProject_EXE2.service.IShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class ShopService implements IShopService {

    private final ShopRepository shopRepository;
    private ProductRepository productRepository;


    @Autowired
    public ShopService(ShopRepository shopRepository,ProductRepository productRepository) {
        this.shopRepository = shopRepository;
        this.productRepository = productRepository;
    }


    @Override
    public Optional<Shop> getShopById(Long id) {
        return shopRepository.findById(id);
    }

    @Override
    public List<Shop> getAllShop() {
        return  shopRepository.findAll();
    }

    @Override
    public List<Shop> getShopByOwnerId(Long ownerId) {
        return shopRepository.findByOwnerId(ownerId);
    }


    @Override
    public Shop createShop(Shop shop) {
        return shopRepository.save(shop);
    }

    @Override
    public Shop updateShop(Long id, Shop shopDetails) {
        Optional<Shop> shopOptional = shopRepository.findById(id);
        if (shopOptional.isPresent()) {
            Shop shop = shopOptional.get();

            if (shopDetails.getNameShop() != null) {
                shop.setNameShop(shopDetails.getNameShop());
            }
            if (shopDetails.getAddress() != null) {
                shop.setAddress(shopDetails.getAddress());
            }
            if (shopDetails.getEmail() != null) {
                shop.setEmail(shopDetails.getEmail());
            }
            if (shopDetails.getPhoneNumber() != null) {
                shop.setPhoneNumber(shopDetails.getPhoneNumber());
            }
            if (shopDetails.getImage() != null) {
                shop.setImage(shopDetails.getImage());
            }
            if (shopDetails.getBackgroundImage() != null) {
                shop.setBackgroundImage(shopDetails.getBackgroundImage());
            }
            if (shopDetails.getWallet() != 0) {
                shop.setWallet(shopDetails.getWallet());
            }
            shop.setStatus(shopDetails.isStatus());

            if (shopDetails.getOwner() != null) {
                shop.setOwner(shopDetails.getOwner());
            }

            return shopRepository.save(shop);
        }
        return null;
    }


    @Override
    public Optional<Shop> getShopByProductId(Long productId) {
        return productRepository.findShopByProductId(productId);
    }
}
