package com.example.EzShopProject_EXE2.model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "order_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderDate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id", nullable = false)
    private OrderStatus status;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_order",nullable = false)
    //@JsonBackReference
    private User user;

    private Long shopId;

//    private Long orderDetailId;
//    @OneToMany(mappedBy = "order")
//    private List<OrderDetail> orderDetails;

    @OneToOne
    @JoinColumn(name = "voucher_id")
    private Voucher voucher;

    @OneToOne
    @JoinColumn(name = "orderDetail_id")
    private OrderDetail detail;
}