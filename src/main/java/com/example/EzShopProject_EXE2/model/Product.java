package com.example.EzShopProject_EXE2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String image;

    private String name;
    private double price;
    private String description;
    private String code;
    private String status;
    private int quantity;
    private String brand;
    private int weight;
    private int situation;
    private String color;
    private String size;
    @Column(columnDefinition = "TEXT")
    private String detail;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt = new Date();


    @Column(columnDefinition = "TEXT")
    private String overview;

    @ManyToMany
    @JoinTable(name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
    @OneToMany(mappedBy = "product")
    private Set<OrderDetail> orderDetails = new HashSet<>();
    @ManyToOne
    @JoinColumn(name = "title_id")
    private Title title;
}