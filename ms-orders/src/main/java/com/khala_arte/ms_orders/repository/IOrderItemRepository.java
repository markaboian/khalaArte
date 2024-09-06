package com.khala_arte.ms_orders.repository;

import com.khala_arte.ms_orders.domain.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderItemRepository extends JpaRepository<OrderItem, Long> {
}
