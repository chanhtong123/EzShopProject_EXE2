package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.Withdrawal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WithdrawalRepository extends JpaRepository<Withdrawal, Long> {
    List<Withdrawal> findByShopId(Long shopId);
}
