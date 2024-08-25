package com.khala_arte.ms_orders.service.interfaces;

import com.khala_arte.ms_orders.dto.OrderItemsDTO;

import java.util.Optional;
import java.util.Set;

public interface IOrderItemsService {

    Optional<OrderItemsDTO> getOrderItemById(Long id);
    Set<OrderItemsDTO> getAllOrderItems();

    OrderItemsDTO addOrderItem(OrderItemsDTO orderItemDTO);
    OrderItemsDTO updateOrderItem(OrderItemsDTO orderItemDTO);

    void deleteOrderItemById(Long id);
}
