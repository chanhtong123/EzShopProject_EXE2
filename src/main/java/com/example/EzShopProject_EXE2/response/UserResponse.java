package com.example.EzShopProject_EXE2.response;

import com.example.EzShopProject_EXE2.model.enums.Gender;
import com.example.EzShopProject_EXE2.model.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String userName;
    private String email;
    private String phone;
    private String image;
    private LocalDateTime dob;
    private Gender gender;
    private boolean status;
    private String createdBy;
    private LocalDateTime createdDate;
    private String modifiedBy;
    private LocalDateTime modifiedDate;
    private boolean deleted;
    private Role role;
}