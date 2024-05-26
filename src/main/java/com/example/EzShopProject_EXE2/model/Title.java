package com.example.EzShopProject_EXE2.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "title")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Title {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
    private Set<Product> products;
}
