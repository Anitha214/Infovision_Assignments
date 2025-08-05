package com.UsageService.service;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class UsageLogScheduler {
    private final LogSimulationJob logSimulationJob;

    @Scheduled(fixedRate = 10000)
    public void scheduleLogGeneration() {
        logSimulationJob.generateLog();
    }

}
