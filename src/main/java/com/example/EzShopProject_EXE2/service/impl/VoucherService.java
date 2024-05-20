package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.converter.VoucherConverter;
import com.example.EzShopProject_EXE2.dto.VoucherDTO;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.model.Voucher;
import com.example.EzShopProject_EXE2.repository.VoucherRepository;
import com.example.EzShopProject_EXE2.service.IVoucherService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VoucherService implements IVoucherService {
    private static final Logger logger = LoggerFactory.getLogger(VoucherService.class);
    private final VoucherRepository voucherRepository;

    @Override
    public VoucherDTO createVoucher(VoucherDTO voucherDTO) {
        try {
            Voucher voucher = VoucherConverter.toEntity(voucherDTO);
            Voucher savedVoucher = voucherRepository.save(voucher);
            return VoucherConverter.toDto(savedVoucher);
        } catch (Exception e) {
            logger.error("Error creating voucher", e);
            throw new RuntimeException("Error creating voucher", e);
        }
    }

    @Override
    public VoucherDTO findVoucherById(long id) {
        try {
            Voucher voucher = voucherRepository.findById(id).orElse(null);
            return VoucherConverter.toDto(voucher);
        } catch (Exception e) {
            logger.error("Error finding voucher by ID", e);
            throw new RuntimeException("Error finding voucher by ID", e);
        }
    }

    @Override
    public List<VoucherDTO> findAllVouchers() {
        try {
            List<Voucher> vouchers = voucherRepository.findAll();
            return VoucherConverter.toDto(vouchers);
        } catch (Exception e) {
            logger.error("Error finding all vouchers", e);
            throw new RuntimeException("Error finding all vouchers", e);
        }
    }

    @Override
    public void deleteVoucher(long id) {
        try {
            voucherRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Error deleting voucher", e);
            throw new RuntimeException("Error deleting voucher", e);
        }
    }

    @Override
    public VoucherDTO updateVoucher(VoucherDTO voucherDTO) throws DataNotFoundException {
        try {
            Optional<Voucher> existingVoucherOpt = voucherRepository.findById(voucherDTO.getId());
            if (existingVoucherOpt.isPresent()) {
                Voucher existingVoucher = existingVoucherOpt.get();
                // Update fields
                existingVoucher.setCode(voucherDTO.getCode());
                existingVoucher.setName(voucherDTO.getName());
                existingVoucher.setValue(voucherDTO.getValue());
                existingVoucher.setCreatDate(voucherDTO.getCreatDate());
                existingVoucher.setEndDate(voucherDTO.getEndDate());

                Voucher updatedVoucher = voucherRepository.save(existingVoucher);
                return VoucherConverter.toDto(updatedVoucher);
            } else {
                throw new DataNotFoundException("Voucher not found with id: " + voucherDTO.getId());
            }
        } catch (DataNotFoundException e) {
            logger.error("Error updating voucher: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            // Any other exception is caught here
            logger.error("Error updating voucher", e);
            throw new RuntimeException("Error updating voucher", e);
        }
    }


}
