package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.VoucherDto;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;

import java.util.List;

public interface IVoucherService {
    VoucherDto createVoucher(VoucherDto voucherDTO);

    VoucherDto findVoucherById(long id);

    List<VoucherDto> findAllVouchers();

    void deleteVoucher(long id);

    VoucherDto updateVoucher(VoucherDto voucherDTO) throws DataNotFoundException;
}
