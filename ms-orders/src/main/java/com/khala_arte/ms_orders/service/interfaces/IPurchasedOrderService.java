package com.khala_arte.ms_orders.service.interfaces;

import com.khala_arte.ms_orders.dto.PurchasedOrderDTO;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public interface IPurchasedOrderService {

    Optional<PurchasedOrderDTO> getOrderById(Long id);
    Optional<PurchasedOrderDTO> getOrderByOrderDate(Date orderDate);
    Set<PurchasedOrderDTO> getAllOrders();

    PurchasedOrderDTO addOrder(PurchasedOrderDTO purchasedOrderDTO);
    PurchasedOrderDTO updateOrder(PurchasedOrderDTO purchasedOrderDTO);

    void deleteOrderById(Long id);
}
