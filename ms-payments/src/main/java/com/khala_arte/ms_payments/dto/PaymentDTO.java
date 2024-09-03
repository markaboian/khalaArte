package com.khala_arte.ms_payments.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class PaymentDTO {
    private Long id;
    private Long orderId;
    private Long userId;
    private Double amount;
    private String paymentMethod;
    private String status;
}
