package com.khala_arte.ms_cart.repository.feign;

import com.khala_arte.ms_cart.dto.product.ProductDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-products")
public interface IProductRepository {

    @GetMapping("/product/getProductById/{id}")
    ProductDTO getProductById(@PathVariable Long id);
    
}
