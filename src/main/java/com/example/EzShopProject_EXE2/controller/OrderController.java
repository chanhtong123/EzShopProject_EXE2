package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.dto.OrderDTO;
import com.example.EzShopProject_EXE2.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@CrossOrigin
public class OrderController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("/id")
    public ResponseEntity<OrderDTO> getOrderById(@RequestParam("id") long id) {
        try {
            OrderDTO orderDTO = orderService.findById(id);
            return new ResponseEntity<>(orderDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
       try {
           OrderDTO order = orderService.save(orderDTO);
           return new ResponseEntity<>(order, HttpStatus.CREATED);
       }catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable long id, @Valid @RequestBody OrderDTO orderDTO) {
        try {
            OrderDTO updatedOrder = orderService.update(id, orderDTO);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/id")
    public ResponseEntity<OrderDTO> deleteOrder(@RequestParam("id") long id) {
        try {
            orderService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        try {
            List<OrderDTO> orders = orderService.findAll();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
