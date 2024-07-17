package com.example.EzShopProject_EXE2.service.impl;

import com.example.EzShopProject_EXE2.dto.WithdrawalDto;
import com.example.EzShopProject_EXE2.model.Withdrawal;
import com.example.EzShopProject_EXE2.model.Shop;
import com.example.EzShopProject_EXE2.repository.WithdrawalRepository;
import com.example.EzShopProject_EXE2.repository.ShopRepository;
import com.example.EzShopProject_EXE2.service.IWithdrawalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WithdrawalService implements IWithdrawalService {

    @Autowired
    private WithdrawalRepository withdrawalRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Override
    public WithdrawalDto createWithdrawal(WithdrawalDto withdrawalDto) {
        Withdrawal withdrawal = convertToEntity(withdrawalDto);

        // Check if shopId is provided and fetch the Shop entity
        if (withdrawalDto.getShopId() != null) {
            Shop shop = shopRepository.findById(withdrawalDto.getShopId())
                    .orElseThrow(() -> new RuntimeException("Shop not found"));
            withdrawal.setShop(shop);
        }

        Withdrawal createdWithdrawal = withdrawalRepository.save(withdrawal);
        return convertToDto(createdWithdrawal);
    }

    @Override
    public WithdrawalDto updateWithdrawalStatus(Long id, String status) {
        Optional<Withdrawal> optionalWithdrawal = withdrawalRepository.findById(id);
        if (optionalWithdrawal.isPresent()) {
            Withdrawal withdrawal = optionalWithdrawal.get();
            withdrawal.setStatus(status);
            Withdrawal updatedWithdrawal = withdrawalRepository.save(withdrawal);
            return convertToDto(updatedWithdrawal);
        } else {
            throw new RuntimeException("Withdrawal not found");
        }
    }

    @Override
    public void deleteWithdrawal(Long id) {
        withdrawalRepository.deleteById(id);
    }

    @Override
    public List<WithdrawalDto> getAllWithdrawals() {
        List<Withdrawal> withdrawals = withdrawalRepository.findAll();
        return withdrawals.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public WithdrawalDto getWithdrawalDetail(Long id) {
        Withdrawal withdrawal = withdrawalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Withdrawal not found"));
        return convertToDto(withdrawal);
    }

    @Override
    public List<WithdrawalDto> getWithdrawalsByShopId(Long shopId) {
        List<Withdrawal> withdrawals = withdrawalRepository.findByShopId(shopId);
        return withdrawals.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private WithdrawalDto convertToDto(Withdrawal withdrawal) {
        WithdrawalDto dto = new WithdrawalDto();
        dto.setId(withdrawal.getId());
        if (withdrawal.getShop() != null) {
            dto.setShopId(withdrawal.getShop().getId());
        }
        dto.setBankAccount(withdrawal.getBankAccount());
        dto.setAmount(withdrawal.getAmount());
        dto.setStatus(withdrawal.getStatus());
        dto.setCreatedAt(withdrawal.getCreatedAt().toString());
        return dto;
    }

    private Withdrawal convertToEntity(WithdrawalDto dto) {
        Withdrawal withdrawal = new Withdrawal();
        withdrawal.setId(dto.getId());
        withdrawal.setAmount(dto.getAmount());
        withdrawal.setStatus(dto.getStatus());
        withdrawal.setBankAccount(dto.getBankAccount());
        // Do not set Shop here, handle it in createWithdrawal method
        return withdrawal;
    }
}
