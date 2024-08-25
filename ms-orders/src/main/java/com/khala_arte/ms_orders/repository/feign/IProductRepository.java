package com.khala_arte.ms_orders.repository.feign;

import com.khala_arte.ms_orders.dto.product.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-products")
public interface IProductRepository {

    @GetMapping("/product/getProductById/{id}")
    ProductDTO getProductById(@PathVariable Long id);
}
