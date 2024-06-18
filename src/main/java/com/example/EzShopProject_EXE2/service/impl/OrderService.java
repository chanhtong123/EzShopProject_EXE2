package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.converter.OrderConverter;
import com.example.EzShopProject_EXE2.converter.OrderDetailConverter;
import com.example.EzShopProject_EXE2.dto.OrderDetailDto;
import com.example.EzShopProject_EXE2.dto.OrderDto;
import com.example.EzShopProject_EXE2.dto.analysis.OrderStatsDTO;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.*;
import com.example.EzShopProject_EXE2.model.enums.OrderStatus;
import com.example.EzShopProject_EXE2.repository.*;
import com.example.EzShopProject_EXE2.service.IOrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {
    private static final Logger logger = LoggerFactory.getLogger(VoucherService.class);
    private final OrderRepository orderRepository;
    private final OrderConverter orderConverter;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;

    @Override
    public List<OrderDto> findAll() {
        try {
            List<Order> orders = orderRepository.findAll();
            return includeOrderDtoWithOrderDetailDto(orders);
        } catch (Exception e) {
            logger.error("Error finding all orders", e);
            throw new RuntimeException("Error finding all orders", e);
        }
    }

    private List<OrderDto> includeOrderDtoWithOrderDetailDto(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();

        for (Order order : orders) {
            List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order.getId());
            OrderDto orderDto = orderConverter.toDto(order);
            List<OrderDetailDto> orderDetailDtos = OrderDetailConverter.toDto(orderDetails);
            orderDto.setCartItems(orderDetailDtos);
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }


    @Override
    public OrderDto findById(long id) {
        try {
            Optional<Order> order = orderRepository.findById(id);
            if (order.isPresent()) {
                List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order.get().getId());
                List<OrderDetailDto> orderDetailDtos = OrderDetailConverter.toDto(orderDetails);
                OrderDto orderDto = orderConverter.toDto(order.get());
                orderDto.setCartItems(orderDetailDtos);
                return orderDto;
            }
            return null;
        } catch (Exception e) {
            logger.error("Error finding order by ID", e);
            throw new RuntimeException("Error finding order by ID", e);
        }
    }

    @Override
    @Transactional
    public Order save(OrderDto orderDto) throws Exception {
        try {
            // Fetch user and statuses
            User user = userRepository.findById(orderDto.getUserId())
                    .orElseThrow(() -> new DataNotFoundException("Cannot find user with id: " + orderDto.getUserId()));

            Cart cart = cartRepository.findByUserId(orderDto.getUserId());
            // Convert DTO to entity and set relationships
            Order order = orderConverter.toEntity(orderDto);
            order.setUser(user);
            order.setOrderDate(LocalDateTime.now());
            order.setStatus(OrderStatus.Pending);
            order.setPaymentStatus(orderDto.getPaymentStatus());
            order.setPaymentMethod(orderDto.getPaymentMethod());

            LocalDate shippingDate = orderDto.getShippingDate() == null ? LocalDate.now() : orderDto.getShippingDate();
            if (shippingDate.isBefore(LocalDate.now())) {
                throw new DataNotFoundException("Date must be at least today!");
            }
            order.setShippingDate(shippingDate);
            order.setActive(true);
            order.setTotalAmount(orderDto.getTotalAmount());

            // Update product quantities if status id is 5
            if (order.getStatus() != null && order.getStatus() == OrderStatus.Completed) {
                updateProductQuantities(orderDto.getCartItems());
            }

            // Persist order to obtain a managed entity
            order = orderRepository.save(order);

            // Create order details
            List<OrderDetail> orderDetails = new ArrayList<>();
            List<CartDetail> cartDetails = cartDetailRepository.findByCartId(cart.getId());
            for (CartDetail cartDetail : cartDetails) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrders(order);

                Long productId = cartDetail.getProduct().getId();
                Product product = productRepository.findById(productId)
                        .orElseThrow(() -> new DataNotFoundException("Product not found with id: " + productId));
                orderDetail.setName(product.getName());
                orderDetail.setPrice(product.getPrice());
                orderDetail.setProduct(product);
                orderDetails.add(orderDetail);
            }
            cart.setOrderId(order.getId());
            cartRepository.save(cart);
            // Save all order details
            orderDetailRepository.saveAll(orderDetails);

            return order;
        } catch (Exception e) {
            throw new Exception("Failed to save order: " + e.getMessage(), e);
        }
    }



    @Override
    public void delete(long id) {
        try {
            orderRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting order", e);
            throw new RuntimeException("Error deleting order", e);
        }
    }


    @Override
    @Transactional
    public Order update(Long id, OrderDto orderDTO) throws DataNotFoundException {
        //try {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("Cannot find order with id: " + id));
        User existingUser = userRepository.findById(orderDTO.getUserId())
                .orElseThrow(()-> new DataNotFoundException("Cannot find user with id: " + id));

        order = orderConverter.toEntity(orderDTO);
        order.setUser(existingUser);
        order.setStatus(orderDTO.getOrderStatus());
        order.setPaymentStatus(orderDTO.getPaymentStatus());
        order.setPaymentMethod(orderDTO.getPaymentMethod());
        orderRepository.save(order);
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail;
        for (OrderDetailDto cartItemDto : orderDTO.getCartItems()) {
            orderDetail = new OrderDetail();
            orderDetail.setOrders(order);
            Long productId = cartItemDto.getProductId();
            OrderDetail orderDetailExisting = orderDetailRepository.findById(cartItemDto.getId())
                    .orElseThrow(() -> new DataNotFoundException("Orderdetail not found with id: " + cartItemDto.getId()));
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new DataNotFoundException("Product not found with id: " + productId));
            orderDetail.setId(orderDetailExisting.getId());
            orderDetail.setName(orderDetailExisting.getName());
            orderDetail.setPrice(product.getPrice());
            orderDetail.setProduct(product);
            order.setPaymentStatus(orderDTO.getPaymentStatus());
            if (order.getStatus() != null && order.getStatus() == OrderStatus.Completed) {
                updateProductQuantities(orderDTO.getCartItems());
            }
            orderDetails.add(orderDetail);
        }
        orderDetailRepository.saveAll(orderDetails);
        return order;
    }

    @Override
    public List<OrderDto> findByUserId(Long userId) {
        try {
            List<Order> orders = orderRepository.findByUserId(userId);
            return includeOrderDtoWithOrderDetailDto(orders);
        } catch (Exception e) {
            logger.error("Error finding order by ID", e);
            throw new RuntimeException("Error finding order by ID", e);
        }
    }


    private void updateProductQuantities(List<OrderDetailDto> cartItems) throws DataNotFoundException {
        for (OrderDetailDto cartItemDto : cartItems) {
            Long productId = cartItemDto.getProductId();

            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new DataNotFoundException("Product not found with id: " + productId));

            productRepository.save(product);
        }
    }

    public OrderStatsDTO getOrderStats() {
        OrderStatsDTO orderStatsDTO = new OrderStatsDTO();
        long totalOrders = orderRepository.countTotalOrders();
        orderStatsDTO.setTotalOrders(totalOrders);
        double orderChangePercentage = orderRepository.calculateOrderChange();
        orderStatsDTO.setOrderChangePercentage(orderChangePercentage);
        return orderStatsDTO;
    }

//    public RevenueDTO getRevenueStatistics() {
//        Double currentMonthRevenue = orderRepository.findTotalRevenueCurrentMonth();
//        Double lastMonthRevenue = orderRepository.findTotalRevenueLastMonth();
//
//        Double revenueChangePercentage = calculateRevenueChangePercentage(currentMonthRevenue, lastMonthRevenue);
//
//        return new RevenueDTO(currentMonthRevenue, lastMonthRevenue, revenueChangePercentage);
//    }

    private Double calculateRevenueChangePercentage(Double currentMonthRevenue, Double lastMonthRevenue) {
        if (lastMonthRevenue == null || lastMonthRevenue == 0) {
            return currentMonthRevenue == null ? null : 100.0; // Assuming a 100% increase if last month revenue is 0
        }
        return ((currentMonthRevenue - lastMonthRevenue) / lastMonthRevenue) * 100;
    }

//    public RevenueDayDTO getTotalSales() {
//        RevenueDayDTO totalSalesDTO = new RevenueDayDTO();
//        totalSalesDTO.setTotalSalesToday(orderRepository.getTotalAmountToday());
//        totalSalesDTO.setTotalSalesYesterday(orderRepository.getTotalAmountYesterday());
//        return totalSalesDTO;
//    }
//
//    @Override
//    public Integer countOrdersByProductId(Long productId) {
//        return orderDetailRepository.countOrderByProductId(productId);
//    }
//    @Override
//    public Optional<Double> getProductRevenue(Long productId) {
//        return orderDetailRepository.findTotalRevenueByProductId(productId);
//    }
}