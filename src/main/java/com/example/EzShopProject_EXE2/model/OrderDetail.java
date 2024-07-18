package com.example.EzShopProject_EXE2.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "order_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double price;

    private String name;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "product_id",nullable = false)
    private Product product;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "order_id",nullable = false)
    @JsonBackReference
    private Order orders;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "shop_id",nullable = false)
    private Shop shop;
}
