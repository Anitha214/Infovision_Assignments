package com.BillingService.client;

import com.BillingService.dto.PlansDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "PlanService", url = "http://localhost:9001")

public interface PlanServiceClient {

    @GetMapping("/plan/{id}")
    Optional<PlansDto> findByPlanId(@PathVariable Long id);
}
