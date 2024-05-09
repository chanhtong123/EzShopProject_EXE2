package com.example.EzShopProject_EXE2.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Shop")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shop {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long shopId;
    @Column(name = "name", length = 500)
    private String name;

    @Column(name = "address", length = 500)
    private String address;

    @Column(name = "phone_number", length = 500)
    private String phoneNumber;

    @Column(name = "wallet")
    private double wallet;

    @Column(name = "status")
    private int status;

    @Column(name = "owner")
    private Long owner;
}
