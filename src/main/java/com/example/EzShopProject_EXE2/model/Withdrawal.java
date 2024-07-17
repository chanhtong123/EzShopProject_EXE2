package com.example.EzShopProject_EXE2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "withdrawal")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = true)
    private Shop shop;

    @Column(name = "amount")
    private double amount;

    @Column(name = "bank_account", nullable = true)
    private String bankAccount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = true, updatable = false)
    private Date createdAt = new Date();

    @Column(name = "status", nullable = true)
    private String status;


}
