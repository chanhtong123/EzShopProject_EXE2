package com.example.EzShopProject_EXE2.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_detail")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId; 

    private int quantity;


}
