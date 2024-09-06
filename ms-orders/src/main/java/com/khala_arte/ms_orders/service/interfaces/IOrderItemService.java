package com.khala_arte.ms_orders.service.interfaces;

import com.khala_arte.ms_orders.dto.OrderItemDTO;

import java.util.Optional;
import java.util.Set;

public interface IOrderItemService {
    Optional<OrderItemDTO> getOrderItemById(Long id);
    Set<OrderItemDTO> getAllOrderItems();

    OrderItemDTO addOrderItem(OrderItemDTO orderItemDTO);
    OrderItemDTO updateOrderItem(OrderItemDTO orderItemDTO);

    void deleteOrderItemById(Long id);
}
