package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.dto.VoucherDTO;
import com.example.EzShopProject_EXE2.exception.DataNotFoundException;
import com.example.EzShopProject_EXE2.service.IVoucherService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vouchers")
@CrossOrigin
public class VoucherController {

    @Autowired
    private IVoucherService voucherService;

    @GetMapping("/{id}")
    public ResponseEntity<VoucherDTO> findById(@PathVariable("id") Long id) {
        try {
            VoucherDTO voucherDTO = voucherService.findVoucherById(id);
            if (voucherDTO == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(voucherDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<VoucherDTO> createVoucher(@Valid @RequestBody VoucherDTO voucherDTO) {
        try {
            VoucherDTO createdVoucher = voucherService.createVoucher(voucherDTO);
            return new ResponseEntity<>(createdVoucher, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/")
    public ResponseEntity<VoucherDTO> updateVoucher(@Valid @RequestBody VoucherDTO voucherDTO) {
        try {
            VoucherDTO updatedVoucher = voucherService.updateVoucher(voucherDTO);
            return new ResponseEntity<>(updatedVoucher, HttpStatus.OK);
        } catch (DataNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVoucher(@PathVariable("id") Long id) {
        try {
            voucherService.deleteVoucher(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<VoucherDTO>> findAll() {
        try {
            List<VoucherDTO> voucherDTOS = voucherService.findAllVouchers();
            return new ResponseEntity<>(voucherDTOS, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
