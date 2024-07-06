package com.example.EzShopProject_EXE2.dto.analysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevenueDto {
    private Double currentMonthRevenue;
    private Double lastMonthRevenue;
    private Double revenueChangePercentage;
}
