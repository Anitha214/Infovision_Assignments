/*
package com.UsageService.service;

import com.UsageService.dto.UsageLogDto;
import com.UsageService.entity.UsageLog;
import com.UsageService.repository.UsageLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsageLogService {

    private final UsageLogRepository usageLogRepository;

    public UsageLog saveUsageLog(UsageLog log) {
        return usageLogRepository.save(log);
    }

    public List<UsageLog> getAllLogs() {
        return usageLogRepository.findAll();
    }

    public List<UsageLogDto> getLogByUserId(Long id){
        return usageLogRepository.getLogByUserId(id);
    }
}

*/
package com.UsageService.service;

import com.UsageService.dto.UsageLogDto;
import com.UsageService.entity.UsageLog;
import com.UsageService.repository.UsageLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsageLogService {

    private final UsageLogRepository logRepo;

    public UsageLog saveUsageLog(UsageLog log) {
        return logRepo.save(log);
    }

    public List<UsageLog> getAllLogs() {
        return logRepo.findAll();
    }

    public List<UsageLogDto> getLogsByCustomerId(Long customerId) {
        return logRepo.findByCustomerId(customerId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<UsageLogDto> getLogsByPlanId(Long planId) {
        return logRepo.findBySubscribedPlanId(planId)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public List<UsageLogDto> getLogsByCustomerIdAndDate(Long customerId, LocalDateTime start, LocalDateTime end) {
        return logRepo.findByCustomerIdAndLoggedAtBetween(customerId, start, end)
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public double getTotalDataUsed(Long customerId) {
        return logRepo.findByCustomerId(customerId)
                .stream()
                .mapToDouble(UsageLog::getMbUsed)
                .sum();
    }

    public int getTotalMinutesUsed(Long customerId) {
        return logRepo.findByCustomerId(customerId)
                .stream()
                .mapToInt(UsageLog::getMinutesUsed)
                .sum();
    }

    private UsageLogDto convertToDto(UsageLog log) {
        return new UsageLogDto(
                log.getLogId(),
                log.getCustomerId(),
                log.getSubscribedPlanId(),
                log.getMbUsed(),
                log.getMinutesUsed(),
                log.getLoggedAt()
        );
    }
}
