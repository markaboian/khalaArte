package com.khala_arte.ms_orders.repository;

import com.khala_arte.ms_orders.domain.CompleteOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ICompleteOrderRepository extends JpaRepository<CompleteOrder, Long> {

    @Query("SELECT co FROM CompleteOrder co WHERE co.orderDate = ?1 ORDER BY co.orderDate")
    Optional<CompleteOrder> getCompleteOrderByOrderDate(Date orderDate);

    @Query("SELECT co FROM CompleteOrder co WHERE co.userId = ?1 ORDER BY co.orderDate")
    List<CompleteOrder> getCompleteOrderByUserId(Long id);
}
