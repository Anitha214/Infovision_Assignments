package com.UsageService.service;

import com.UsageService.entity.UsageLog;
import com.UsageService.util.RandomUsageGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class LogSimulationJob {

    private final UsageLogService usageLogService;

    @Async
    public void generateLog() {
        UsageLog log = UsageLog.builder()
                .customerId(RandomUsageGenerator.getRandomUserId())
                .subscribedPlanId(RandomUsageGenerator.getRandomPlanId())
                .mbUsed(RandomUsageGenerator.getRandomDataUsage())
                .minutesUsed(RandomUsageGenerator.getRandomCallMinutes())
                .loggedAt(LocalDateTime.now())
                .build();

        usageLogService.saveUsageLog(log);

    }
}
