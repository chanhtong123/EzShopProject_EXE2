package com.example.EzShopProject_EXE2.model;

import com.example.EzShopProject_EXE2.model.enums.Gender;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", unique = true, nullable = false)
    private String phone;

    @Column(name = "dob", nullable = false)
    private LocalDateTime dob;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "status", nullable = false, columnDefinition = "boolean default true")
    private boolean status;

    @Column(name = "created_by", nullable = true)
    private String createdBy;

    @Column(name = "created_date", nullable = true)
    private Date createdDate;

    @Column(name = "modified_by", nullable = true)
    private String modifiedBy;

    @Column(name = "modified_date", nullable = true)
    private Date modifiedDate;

    @Column(name = "deleted", nullable = false)
    private boolean deleted;
}
