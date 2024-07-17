package com.example.EzShopProject_EXE2.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WithdrawalDto {
    private Long id;
    private Long shopId;
    private Double amount;
    private String status;
    private String createdAt;
    private String bankAccount;
}
