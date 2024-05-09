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
private Long ShopId;
private String Name;
private String Address;
private String PhoneNumber;
private double wallet;
private int status;
private Long owner;
}
