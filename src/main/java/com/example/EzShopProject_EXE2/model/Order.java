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

    private int status;

    private Long userId;

    private Long shopId;

    private Long orderDetailId;
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
