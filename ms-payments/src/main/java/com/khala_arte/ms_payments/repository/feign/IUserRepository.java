package com.khala_arte.ms_payments.repository.feign;

import com.khala_arte.ms_payments.dto.user.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "ms-users")
public interface IUserRepository {

    @RequestMapping("/user/getUserById/{id}")
    UserDTO getUserById(@PathVariable Long id);
}
