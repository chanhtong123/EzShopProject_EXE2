package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.dto.OrderDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.exception.BadRequestException;
import com.example.EzShopProject_EXE2.model.Order;
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
    public ResponseEntity<Order> getOrderById(@RequestParam("id") long id) throws DataNotFoundException {
        Order order = orderService.findById(id);
        if (order == null) {
            throw new DataNotFoundException("Order not found for ID: " + id);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<?> createOrder(@Valid @RequestBody OrderDto orderDto, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }

        try {
            Order order = orderService.save(orderDto);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder( @Valid @PathVariable long id,
                                          @Valid @RequestBody OrderDto OrderDto,
                                          BindingResult result) {
        try {
            if (result.hasErrors()) {
                List<String> errorMessages = result.getFieldErrors()
                        .stream()
                        .map(FieldError::getDefaultMessage)
                        .toList();
                return ResponseEntity.badRequest().body(errorMessages);
            }
            Order updatedOrder = orderService.update(id, OrderDto);
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
    public ResponseEntity<?> getAllOrders() {
        try {
            List<OrderDto> orders = orderService.findAll();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException("Failed to get all orders: " + e.getMessage());
        }
    }

    @GetMapping("/user-id")
    public ResponseEntity<?> getOrders(@Valid @RequestParam("user_id") Long userId) {
        try {
            List<OrderDto> orders = orderService.findByUserId(userId);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException("Failed to get all orders: " + e.getMessage());
        }
    }
}