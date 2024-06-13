package com.example.EzShopProject_EXE2.response;

import com.example.EzShopProject_EXE2.dto.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@Builder
@NoArgsConstructor
public class OrderListResponse {
    private List<OrderDto> orders;
    private int totalPages;
}
