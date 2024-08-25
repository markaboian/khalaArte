package com.khala_arte.ms_orders.dto;

import com.khala_arte.ms_orders.domain.PurchasedOrder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class OrderItemsDTO {
    private Long id;
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double price;
}
