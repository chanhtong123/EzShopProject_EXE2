package com.example.EzShopProject_EXE2.converter;

import com.example.EzShopProject_EXE2.dto.VoucherDto;
import com.example.EzShopProject_EXE2.model.Voucher;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VoucherConverter {
    public static VoucherDto toDto(Voucher voucher) {
        return VoucherDto.builder()
                .id(voucher.getId())
                .name(voucher.getName())
                .code(voucher.getCode())
                .value(voucher.getValue())
                .creatDate(voucher.getCreatDate())
                .endDate(voucher.getEndDate())
                .build();
    }
    public static Voucher toEntity(VoucherDto dto) {
        return Voucher.builder()
                .id(dto.getId())
                .name(dto.getName())
                .value(dto.getValue())
                .code(dto.getCode())
                .creatDate(dto.getCreatDate())
                .endDate(dto.getEndDate())
                .build();
    }

    public static List<VoucherDto> toDto(List<Voucher> vouchers) {
        return vouchers.stream()
                .map(VoucherConverter::toDto)
                .collect(Collectors.toList());
    }
}
