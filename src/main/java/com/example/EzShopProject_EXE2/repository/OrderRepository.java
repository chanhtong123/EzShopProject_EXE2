package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Page<Order> findByUserId(Long userId, Pageable pageable);

    @Query("SELECT COUNT(o) FROM Order o")
    long countTotalOrders();

    default double calculateOrderChange() {
        long currentMonthCount = countOrdersThisMonth();
        long lastMonthCount = countOrdersLastMonth();

        if (lastMonthCount == 0) {
            return 0; // To avoid division by zero
        }

        double percentageChange = ((double) (currentMonthCount - lastMonthCount) / lastMonthCount) * 100;
        return percentageChange;
    }

    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE MONTH(o.orderDate) = MONTH(CURRENT_DATE())")
    Double findTotalRevenueCurrentMonth();

    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE MONTH(o.orderDate) = MONTH(CURRENT_DATE()) - 1")
    Double findTotalRevenueLastMonth();

    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.orderDate = CURRENT_DATE")
    Double getTotalAmountToday();

    @Query("SELECT SUM(o.totalAmount) FROM Order o WHERE o.orderDate = :yesterday")
    Double getTotalAmountYesterday(@Param("yesterday") LocalDateTime yesterday);

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE FUNCTION('YEAR', o.orderDate) = FUNCTION('YEAR', CURRENT_DATE) " +
            "AND FUNCTION('MONTH', o.orderDate) = FUNCTION('MONTH', CURRENT_DATE)")
    long countOrdersThisMonth();

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE FUNCTION('YEAR', o.orderDate) = FUNCTION('YEAR', CURRENT_DATE) " +
            "AND FUNCTION('MONTH', o.orderDate) = FUNCTION('MONTH', CURRENT_DATE) - 1")
    long countOrdersLastMonth();
}
