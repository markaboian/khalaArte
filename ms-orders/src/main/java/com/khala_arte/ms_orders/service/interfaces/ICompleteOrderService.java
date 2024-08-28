package com.khala_arte.ms_orders.service.interfaces;

import com.khala_arte.ms_orders.dto.CompleteOrderDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ICompleteOrderService {

    Optional<CompleteOrderDTO> getCompleteOrderById(Long id);
    Optional<CompleteOrderDTO> getCompleteOrderByOrderDate(Date orderDate);

    List<CompleteOrderDTO> getCompleteOrderByUserId(Long id);

    Set<CompleteOrderDTO> getAllCompleteOrders();

    CompleteOrderDTO addCompleteOrder(CompleteOrderDTO completeOrderDTO);
    CompleteOrderDTO updateCompleteOrder(CompleteOrderDTO completeOrderDTO);

    void deleteCompleteOrderById(Long id);
}
