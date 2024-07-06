package com.example.EzShopProject_EXE2.dto.analysis;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.DecimalFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatsDto {
    private Long usersThisMonth;
    private Long usersLastMonth;
    private double percentageChange;
    public String getFormattedPercentageChange() {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(percentageChange);
    }
}
