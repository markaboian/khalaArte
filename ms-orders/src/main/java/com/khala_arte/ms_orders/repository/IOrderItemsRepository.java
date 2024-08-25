package com.khala_arte.ms_orders.repository;

import com.khala_arte.ms_orders.domain.OrderItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderItemsRepository extends JpaRepository<OrderItems, Long> {

}
