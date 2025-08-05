package com.BillingService.client;

import com.BillingService.dto.CustomerDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserService", url = "http://localhost:9000")

public interface UserServiceClient {

    @GetMapping("/user/customer/{userId}")
    CustomerDto getUserDetails(@PathVariable Long userId);

}
