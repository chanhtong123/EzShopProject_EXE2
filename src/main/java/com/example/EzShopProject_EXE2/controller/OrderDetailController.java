package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.dto.OrderDetailDto;
import com.example.EzShopProject_EXE2.model.OrderDetail;
import com.example.EzShopProject_EXE2.service.IOrderDetailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order-details")
public class OrderDetailController {

    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailDto> getOrderDetailById(@PathVariable("id") long id) {
        OrderDetailDto orderDetailDTO = orderDetailService.findById(id);
        if (orderDetailDTO != null) {
            return new ResponseEntity<>(orderDetailDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<OrderDetailDto>> getAllOrderDetails() {
        try {
            List<OrderDetailDto> orderDetailDTOList = orderDetailService.findAll();
            return new ResponseEntity<>(orderDetailDTOList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<OrderDetailDto> createOrderDetail(@Valid @RequestBody OrderDetail orderDetail) {

        try {
            OrderDetailDto createdOrderDetail = orderDetailService.create(orderDetail);
            return new ResponseEntity<>(createdOrderDetail, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDetailDto> updateOrderDetail(@PathVariable("id") long id, @Valid @RequestBody OrderDetail orderDetail) {

        try {
            OrderDetailDto updatedOrderDetail = orderDetailService.update(id, orderDetail);
            return new ResponseEntity<>(updatedOrderDetail, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable("id") long id) {

        try {
            orderDetailService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
