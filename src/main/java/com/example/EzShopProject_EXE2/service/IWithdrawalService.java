package com.example.EzShopProject_EXE2.service;

import com.example.EzShopProject_EXE2.dto.WithdrawalDto;
import com.example.EzShopProject_EXE2.model.Withdrawal;

import java.util.List;

public interface IWithdrawalService {
    WithdrawalDto createWithdrawal(WithdrawalDto withdrawalDto);
    WithdrawalDto updateWithdrawalStatus(Long id, String status);
    void deleteWithdrawal(Long id);
    List<WithdrawalDto> getAllWithdrawals();
    WithdrawalDto getWithdrawalDetail(Long id);
    List<WithdrawalDto> getWithdrawalsByShopId(Long shopId);
}
