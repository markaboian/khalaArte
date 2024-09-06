package com.khala_arte.ms_orders.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
public class CompleteOrderDTO {
    private Long id;
    private Long userId;
    private Double totalAmount;
    private String shippingAddress;
    private String status;
    private List<OrderItemDTO> orderItemDTOS;
}
