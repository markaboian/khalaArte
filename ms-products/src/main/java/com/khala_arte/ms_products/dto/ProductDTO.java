package com.khala_arte.ms_products.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor

public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String image;
}
