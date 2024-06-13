package com.example.EzShopProject_EXE2.model;
import com.example.EzShopProject_EXE2.model.enums.OrderStatus;
import com.example.EzShopProject_EXE2.model.enums.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "order_table")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "order_date")
    private LocalDateTime orderDate;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "total_amount")
    private double totalAmount;

//    @OneToOne
//    @JoinColumn(name = "voucher_id")
//    private Voucher voucher;

    @Column(name = "profit")
    private double profit;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "province")
    private String province;

    @Column(name = "district")
    private String district;

    @Column(name = "ward")
    private String ward;

    @Column(name = "address")
    private String address;

    @Column(name = "shipping_method")
    private String shippingMethod;

    @Column(name = "shipping_date")
    private LocalDate shippingDate;

    @Column(name = "notes")
    private String notes;

    @Column(name = "fullname")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "active")
    private Boolean active;


    @JoinColumn(name = "status_id", nullable = false)
    private OrderStatus status;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "user_order",nullable = false)
    //@JsonBackReference
    private User user;

    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<OrderDetail> orderDetails;


    @JoinColumn(name = "payment_status_id")
    private PaymentStatus paymentStatus;


}