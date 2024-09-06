package com.khala_arte.ms_payments.repository.feign;

import com.khala_arte.ms_payments.dto.completeOrder.CompleteOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "ms-orders")
public interface ICompleteOrderRepository {

    @RequestMapping("/completeOrder/getCompleteOrderById/{id}")
    CompleteOrderDTO getCompleteOrderById(@PathVariable Long id);
}
