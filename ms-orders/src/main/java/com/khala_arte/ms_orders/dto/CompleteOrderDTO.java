package com.khala_arte.ms_orders.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.khala_arte.ms_orders.domain.OrderItems;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CompleteOrderDTO {
    private Long id;
    private Long userId;
    private String orderStatus;
    private Date orderDate;
    private Double totalAmount;
    private String paymentMethod;
    private String country;
    private String city;
    private String address;
    private Integer telephone;
    private Integer zipCode;

    @JsonIgnore
    private Set<OrderItemsDTO> orderItems;
}
