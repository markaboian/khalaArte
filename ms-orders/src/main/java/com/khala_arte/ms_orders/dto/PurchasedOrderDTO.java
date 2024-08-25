package com.khala_arte.ms_orders.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PurchasedOrderDTO {
    private Long id;
    private Long userId;
    private String status;
    private Date orderDate;
    private Double totalAmount;
    private String paymentMethod;
    private String country;
    private String city;
    private String address;
    private Integer zipCode;
    private Integer telephone;
}