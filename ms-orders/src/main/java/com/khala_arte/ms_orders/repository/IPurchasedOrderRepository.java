package com.khala_arte.ms_orders.repository;

import com.khala_arte.ms_orders.domain.PurchasedOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface IPurchasedOrderRepository extends JpaRepository<PurchasedOrder, Long> {

    @Query("SELECT o FROM PurchasedOrder o WHERE o.orderDate = ?1 ORDER BY o.orderDate")
    Optional<PurchasedOrder> getOrderByOrderDate(Date orderDate);
}
