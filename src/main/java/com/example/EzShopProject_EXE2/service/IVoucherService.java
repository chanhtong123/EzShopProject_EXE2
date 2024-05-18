package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.VoucherDTO;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;

import java.util.List;

public interface IVoucherService {
    VoucherDTO createVoucher(VoucherDTO voucherDTO);

    VoucherDTO findVoucherById(long id);

    List<VoucherDTO> findAllVouchers();

    void deleteVoucher(long id);

    VoucherDTO updateVoucher(VoucherDTO voucherDTO) throws DataNotFoundException;
}
