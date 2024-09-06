package com.khala_arte.ms_orders.service.interfaces;

import com.khala_arte.ms_orders.dto.CompleteOrderDTO;

import java.util.Optional;
import java.util.Set;

public interface ICompleteOrderService {

    Optional<CompleteOrderDTO> getCompleteOrderById(Long id);
    Set<CompleteOrderDTO> getAllCompleteOrders();

    CompleteOrderDTO addCompleteOrder(CompleteOrderDTO completeOrderDTO);
    CompleteOrderDTO updateCompleteOrder(CompleteOrderDTO completeOrderDTO);

    void deleteCompleteOrderById(Long id);
}
