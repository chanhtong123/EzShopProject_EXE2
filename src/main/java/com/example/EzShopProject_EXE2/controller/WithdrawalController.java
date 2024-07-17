package com.example.EzShopProject_EXE2.controller;

import com.example.EzShopProject_EXE2.dto.WithdrawalDto;
import com.example.EzShopProject_EXE2.model.Withdrawal;
import com.example.EzShopProject_EXE2.service.IWithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/public/withdrawals")
public class WithdrawalController {

    @Autowired
    private IWithdrawalService withdrawalService;


    @PostMapping
    public ResponseEntity<WithdrawalDto> createWithdrawal(@RequestBody WithdrawalDto withdrawalDto) {
        WithdrawalDto createdWithdrawal = withdrawalService.createWithdrawal(withdrawalDto);
        return ResponseEntity.ok(createdWithdrawal);
    }


    @PutMapping("/{id}/status")
    public ResponseEntity<WithdrawalDto> updateWithdrawalStatus(@PathVariable Long id, @RequestParam String status) {
        WithdrawalDto updatedWithdrawal = withdrawalService.updateWithdrawalStatus(id, status);
        return ResponseEntity.ok(updatedWithdrawal);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWithdrawal(@PathVariable Long id) {
        withdrawalService.deleteWithdrawal(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<WithdrawalDto>> getAllWithdrawals() {
        List<WithdrawalDto> withdrawals = withdrawalService.getAllWithdrawals();
        return ResponseEntity.ok(withdrawals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WithdrawalDto> getWithdrawalDetail(@PathVariable Long id) {
        WithdrawalDto withdrawal = withdrawalService.getWithdrawalDetail(id);
        return ResponseEntity.ok(withdrawal);
    }

    @GetMapping("/shop/{shopId}")
    public ResponseEntity<List<WithdrawalDto>> getWithdrawalsByShopId(@PathVariable Long shopId) {
        List<WithdrawalDto> withdrawals = withdrawalService.getWithdrawalsByShopId(shopId);
        return ResponseEntity.ok(withdrawals);
    }
}
