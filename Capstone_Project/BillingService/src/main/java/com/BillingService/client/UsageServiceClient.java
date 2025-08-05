package com.BillingService.client;

import com.BillingService.dto.UsageSummary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "UsageService", url = "http://localhost:9002")

public interface UsageServiceClient {

    @GetMapping("/usage/user/{userId}")
    List<UsageSummary> getUsageSummary(@PathVariable Long userId);

}
