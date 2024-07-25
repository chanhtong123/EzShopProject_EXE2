package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.dto.OrderDto;
import com.example.EzShopProject_EXE2.dto.analysis.*;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.exception.BadRequestException;
import com.example.EzShopProject_EXE2.model.Order;
import com.example.EzShopProject_EXE2.model.enums.OrderStatus;
import com.example.EzShopProject_EXE2.service.ICartDetailService;
import com.example.EzShopProject_EXE2.service.IOrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.FieldError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("guest/api/orders")
@CrossOrigin
public class OrderController {
    private final IOrderService orderService;
    private final ICartDetailService cartDetailService;
    public OrderController(IOrderService orderService,
                           ICartDetailService cartDetailService) {
        this.orderService = orderService;
        this.cartDetailService = cartDetailService;
    }

    @GetMapping("/id")
    public ResponseEntity<?> getOrderById(@RequestParam("id") long id) throws DataNotFoundException {
        OrderDto order = orderService.findById(id);
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
            cartDetailService.deleteAllCartDetail();
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
    public ResponseEntity<?> getAllOrders(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<OrderDto> orders = orderService.findAll(pageable);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException("Failed to get all orders: " + e.getMessage());
        }
    }

    @GetMapping("/user-id")
    public ResponseEntity<?> getOrdersByUserId(
            @Valid @RequestParam("user_id") Long userId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<OrderDto> orders = orderService.findByUserId(userId, pageable);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException("Failed to get orders for user: " + e.getMessage());
        }
    }

    @GetMapping("/total-order")
    public ResponseEntity<OrderStatsDTO> getOrderStats() {
        OrderStatsDTO orderStatsDTO = orderService.getOrderStats();
        return ResponseEntity.ok(orderStatsDTO);
    }

    @GetMapping("/total-revenue")
    public ResponseEntity<RevenueDTO> getRevenueStatistics() {
        RevenueDTO revenueDTO = orderService.getRevenueStatistics();
        return ResponseEntity.ok(revenueDTO);
    }

    @GetMapping("/total-revenue-day")
    public ResponseEntity<RevenueDayDTO> getTotalSales() {
        RevenueDayDTO totalSalesDTO = orderService.getTotalSales();
        String formattedTotalSalesToday = totalSalesDTO.getFormattedTotalSalesToday();
        String formattedTotalSalesYesterday = totalSalesDTO.getFormattedTotalSalesYesterday();
        totalSalesDTO.setTotalSalesToday(formattedTotalSalesToday != null ? Double.valueOf(formattedTotalSalesToday) : null);
        totalSalesDTO.setTotalSalesYesterday(formattedTotalSalesYesterday != null ? Double.valueOf(formattedTotalSalesYesterday) : null);
        return ResponseEntity.ok(totalSalesDTO);
    }

    @GetMapping("/count/{productId}")
    public ResponseEntity<OrderCountDto> countOrdersByProductId(@PathVariable Long productId) {
        Integer orderCount = orderService.countOrdersByProductId(productId);
        OrderCountDto orderCountDto = new OrderCountDto();
        orderCountDto.setProductId(productId);
        orderCountDto.setOrderCount(orderCount);
        return ResponseEntity.ok(orderCountDto);
    }

    @GetMapping("/revenue/{productId}")
    public ResponseEntity<ProductRevenueDto> getProductRevenue(@PathVariable Long productId) {
        Optional<Double> revenue = orderService.getProductRevenue(productId);
        if (revenue.isPresent()) {
            ProductRevenueDto productRevenueDto = new ProductRevenueDto();
            productRevenueDto.setTotalRevenue(revenue.get());
            productRevenueDto.setProductId(productId);
            return ResponseEntity.ok(productRevenueDto);
        } else {
            ProductRevenueDto productRevenueDto = new ProductRevenueDto();
            productRevenueDto.setTotalRevenue(0.0); // Set total revenue to 0
            productRevenueDto.setProductId(productId);
            return ResponseEntity.ok(productRevenueDto);
        }
    }

    @GetMapping("/shop-id")
    public ResponseEntity<?> getOrdersByShopId(
            @Valid @RequestParam("shop_id") Long shopId,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        try {
            Pageable pageable = PageRequest.of(page, size);
            Page<OrderDto> orders = orderService.findByShopId(shopId, pageable);
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            throw new BadRequestException("Failed to get orders for user: " + e.getMessage());
        }
    }


    @GetMapping("/date-range")
    public ResponseEntity<List<OrderDto>> getOrdersByDateRange(
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        List<OrderDto> orders = orderService.findOrdersByOrderDateBetween(startDate, endDate);
        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long id, @RequestParam OrderStatus status) throws DataNotFoundException {
        OrderDto updatedOrder = orderService.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }
}