package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.model.Shop;
import com.example.EzShopProject_EXE2.response.ShopResponse;
import com.example.EzShopProject_EXE2.service.IShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequiredArgsConstructor
public class ShopController {

    private final IShopService iShopService;

    @GetMapping("/public/shop/{id}")
    public ResponseEntity<Shop> getShopById(@PathVariable Long id) {
        Optional<Shop> optionalShop = iShopService.getShopById(id);
        return optionalShop.map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());
    }


    @GetMapping("/public/shop")
    public ResponseEntity<List<ShopResponse>> getAllShop() {
        List<Shop> shopList = iShopService.getAllShop();
        List<ShopResponse> responseList = new ArrayList<>();
        for (Shop shop : shopList) {
            responseList.add(ShopResponse.builder()
                    .shopId(shop.getId())
                    .nameShop(shop.getNameShop())
                    .address(shop.getAddress())
                    .phoneNumber(shop.getPhoneNumber())
                    .image(shop.getImage())
                    .backgroundImage(shop.getBackgroundImage())
                    .wallet(shop.getWallet())
                    .status(shop.isStatus())
                    .build());
        }
        return ResponseEntity.ok(responseList);
    }

}
