package com.example.EzShopProject_EXE2.converter;

import com.example.EzShopProject_EXE2.dto.VoucherDTO;
import com.example.EzShopProject_EXE2.model.Voucher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoucherConverter {
    public static VoucherDTO toDto(Voucher voucher) {
        return VoucherDTO.builder()
                .id(voucher.getId())
                .name(voucher.getName())
                .code(voucher.getCode())
                .value(voucher.getValue())
                .creatDate(voucher.getCreatDate())
                .endDate(voucher.getEndDate())
                .build();
    }
    public static Voucher toEntity(VoucherDTO dto) {
        return Voucher.builder()
                .id(dto.getId())
                .name(dto.getName())
                .value(dto.getValue())
                .code(dto.getCode())
                .creatDate(dto.getCreatDate())
                .endDate(dto.getEndDate())
                .build();
    }

    public static List<VoucherDTO> toDto(List<Voucher> vouchers) {
        return vouchers.stream()
                .map(VoucherConverter::toDto)
                .collect(Collectors.toList());
    }
}
