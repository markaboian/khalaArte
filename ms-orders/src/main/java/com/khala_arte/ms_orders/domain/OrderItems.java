package com.khala_arte.ms_orders.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString

@Entity
@Table(name = "Order_Items")
public class OrderItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private PurchasedOrder purchasedOrder;

    private Long product_id;
    private Integer quantity;
    private Double price;
}
