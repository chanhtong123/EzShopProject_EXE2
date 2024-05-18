package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.model.CartDetail;
import com.example.EzShopProject_EXE2.service.ICartDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/cart_detail")
@RequiredArgsConstructor
@CrossOrigin
public class CartDetailController {
    private final ICartDetailService iCartDetailService;


    @PostMapping()
    public ResponseEntity<CartDetail> createCartDetail(@RequestBody CartDetail cartDetail) {
        CartDetail createdCartDetail = iCartDetailService.createCartDetail(cartDetail);
        return ResponseEntity.ok(createdCartDetail);
    }
    @GetMapping("/{id}")
    public ResponseEntity<CartDetail> getCartDetailById(@PathVariable Long id)
    {
        Optional<CartDetail> cartDetail = iCartDetailService.getCartDetailById(id);
        return cartDetail.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping()
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
