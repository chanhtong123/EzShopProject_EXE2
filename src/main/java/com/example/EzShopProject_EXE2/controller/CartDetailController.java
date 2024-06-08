package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.model.CartDetail;
import com.example.EzShopProject_EXE2.service.ICartDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cart_item")
@RequiredArgsConstructor
@CrossOrigin
public class CartDetailController {
    private final ICartDetailService iCartDetailService;


    @GetMapping("/{cartId}")
    public ResponseEntity<List<CartDetail>> getCartDetailsByCartId(@PathVariable Long cartId) {
        List<CartDetail> cartDetails = iCartDetailService.getCartDetailsByCartId(cartId);
        return ResponseEntity.ok(cartDetails);
    }

    @PostMapping()
    public ResponseEntity<CartDetail> createCartDetail(@RequestBody CartDetail cartDetail) {
        CartDetail createdCartDetail = iCartDetailService.createCartDetail(cartDetail);
        return ResponseEntity.ok(createdCartDetail);
    }


    @GetMapping("/get_all")
    public ResponseEntity<List<CartDetail>> getAllCarts() {
        List<CartDetail> carts = iCartDetailService.getAllCartsDetail();
        return ResponseEntity.ok(carts);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CartDetail> updateCart(@PathVariable Long id, @RequestBody CartDetail cartDetail) {
        CartDetail updatedCart = iCartDetailService.updateCartDetail(id, cartDetail);
        if (updatedCart != null) {
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id) {
        iCartDetailService.deleteCartDetail(id);
        return ResponseEntity.noContent().build();
    }

}
