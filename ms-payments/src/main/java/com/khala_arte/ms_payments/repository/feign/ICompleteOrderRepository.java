package com.khala_arte.ms_payments.repository.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "ms-products")
public interface ICompleteOrderRepository {

    @RequestMapping("/product/getProductById/{id}")

}
