package com.khala_arte.ms_orders.dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@RequiredArgsConstructor
public class OrderItemDTO {
    private Long id;
    private Long orderId;
    private Long productId;
    private Double price;
}
