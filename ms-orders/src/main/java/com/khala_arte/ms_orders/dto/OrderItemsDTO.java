package com.khala_arte.ms_orders.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("orderId")
    private Long orderId;
    private Long productId;
    private Integer quantity;
    private Double price;
}