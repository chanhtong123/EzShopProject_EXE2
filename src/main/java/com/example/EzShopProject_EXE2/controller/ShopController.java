package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.model.Shop;
import com.example.EzShopProject_EXE2.request.shop.ShopSearchRequest;
import com.example.EzShopProject_EXE2.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shops")
public class ShopController {

    private final IShopService shopService;

    @Autowired
    public ShopController(IShopService shopService) {
        this.shopService = shopService;
    }

//Create shop
//Get Shop
//Update
//GetShop By id

    @GetMapping("/{id}")
    public ResponseEntity<Shop> getProductById(@PathVariable("id") long id) {
        try {
            Shop shop = shopService.getShopById(id);
            return new ResponseEntity<>(shop, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/owner/{id}")
    public ResponseEntity<?> getShopByOwnerId(@PathVariable("id") long id){
        try{
            Shop shop = shopService.getShopByOwnerId(id);
            return new ResponseEntity<>(shop, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<?> createNewShop(@RequestBody Shop shop){
        try{
            shopService.createNewShop(shop);
            return new ResponseEntity<>("Success!",HttpStatus.OK);
        }catch (Exception e){
            return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping
    public  ResponseEntity<?> updateShop(@RequestBody Shop shop){
        try{
            shopService.updateShop(shop);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Fails", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<?> getShop(@RequestBody ShopSearchRequest request){
        try{
            List<Shop> result = shopService.getShop(request);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Empty", HttpStatus.NOT_FOUND);
        }
    }
}
