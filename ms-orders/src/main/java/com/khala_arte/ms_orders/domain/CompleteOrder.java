package com.khala_arte.ms_orders.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString

@Entity
@Table(name = "complete_order")
public class CompleteOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @OneToMany(mappedBy = "completeOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private Set<OrderItems> orderItems;
}
