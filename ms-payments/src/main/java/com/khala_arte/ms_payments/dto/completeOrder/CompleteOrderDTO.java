package com.khala_arte.ms_payments.dto.completeOrder;

import com.khala_arte.ms_payments.dto.orderItem.OrderItemDTO;

import java.util.List;

public class CompleteOrderDTO {
    private Long id;
    private Long userId;
    private Double totalAmount;
    private String shippingAddress;
    private String status;
    private List<OrderItemDTO> orderItemDTOS;
}
