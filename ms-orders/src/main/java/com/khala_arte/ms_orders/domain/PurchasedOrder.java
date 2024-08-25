package com.khala_arte.ms_orders.domain;

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
@Table(name = "Purchased_Order")
public class PurchasedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long user_id;
    private String status;
    private Date orderDate;
    private Double totalAmount;
    private String paymentMethod;
    private String country;
    private String city;
    private String address;
    private Integer zipCode;
    private Integer telephone;

    @OneToMany(mappedBy = "purchasedOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItems> orderItems;

}
