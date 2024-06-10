package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.dto.OrderDTO;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.exception.BadRequestException;
import com.example.EzShopProject_EXE2.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("guest/api/orders")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    private final IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/id")
    public ResponseEntity<OrderDTO> getOrderById(@RequestParam("id") long id) throws DataNotFoundException {
        OrderDTO orderDTO = orderService.findById(id);
        if (orderDTO == null) {
            throw new DataNotFoundException("Order not found for ID: " + id);
        }
        return new ResponseEntity<>(orderDTO, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createOrder( @Valid @RequestBody OrderDTO orderDTO,
                                                 BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            OrderDTO order = orderService.save(orderDTO);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (Exception e) {
            throw new BadRequestException("Failed to create order: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder( @Valid @PathVariable long id,
                                          @Valid @RequestBody OrderDTO orderDTO,
                                          BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            OrderDTO updatedOrder = orderService.update(id, orderDTO);
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException("Failed to update order: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable long id) {
        try {
            orderService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException("Failed to delete order: " + e.getMessage());
        }
    }


    @GetMapping("/")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        try {
            List<OrderDTO> orders = orderService.findAll();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException("Failed to get all orders: " + e.getMessage());
        }
    }

    @GetMapping("/user-id")
    public ResponseEntity<?> getOrders(@Valid @RequestParam("user_id") Long userId) {
        try {
            List<OrderDTO> orders = orderService.findByUserId(userId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException("Failed to get all orders: " + e.getMessage());
        }
    }
}
