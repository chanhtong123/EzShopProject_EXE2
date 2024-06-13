package com.example.EzShopProject_EXE2.repository;

import com.example.EzShopProject_EXE2.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Month;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUserId(Long userId);


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

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE FUNCTION('YEAR', o.orderDate) = FUNCTION('YEAR', CURRENT_DATE) " +
            "AND FUNCTION('MONTH', o.orderDate) = FUNCTION('MONTH', CURRENT_DATE)")
    long countOrdersThisMonth();

    @Query("SELECT COUNT(o) FROM Order o " +
            "WHERE FUNCTION('YEAR', o.orderDate) = FUNCTION('YEAR', CURRENT_DATE) " +
            "AND FUNCTION('MONTH', o.orderDate) = FUNCTION('MONTH', CURRENT_DATE) - 1")
    long countOrdersLastMonth();

}