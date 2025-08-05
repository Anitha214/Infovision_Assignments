
package com.UsageService.controller;

import com.UsageService.dto.UsageLogDto;
import com.UsageService.service.UsageLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/usage")
@RequiredArgsConstructor
public class UsageController {

    private final UsageLogService usageLogService;

    @GetMapping("/all")
    public List<UsageLogDto> getAllLogs() {
        return usageLogService.getAllLogs().stream().map(log -> usageLogService.getLogsByCustomerId(log.getCustomerId()).get(0)).toList();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<UsageLogDto>> getUserLogs(@PathVariable Long id) {
        return ResponseEntity.ok(usageLogService.getLogsByCustomerId(id));
    }


    @GetMapping("/user/{id}/total-data")
    public ResponseEntity<Double> getTotalDataUsed(@PathVariable Long id) {
        return ResponseEntity.ok(usageLogService.getTotalDataUsed(id));
    }

    @GetMapping("/user/{id}/total-minutes")
    public ResponseEntity<Integer> getTotalMinutesUsed(@PathVariable Long id) {
        return ResponseEntity.ok(usageLogService.getTotalMinutesUsed(id));
    }

    @GetMapping("/user/{id}/filter")
    public ResponseEntity<List<UsageLogDto>> getLogsByDateRange(
            @PathVariable Long id,
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {
        return ResponseEntity.ok(usageLogService.getLogsByCustomerIdAndDate(id, start, end));
    }

    @GetMapping("/plan/{planId}")
    public ResponseEntity<List<UsageLogDto>> getLogsByPlan(@PathVariable Long planId) {
        return ResponseEntity.ok(usageLogService.getLogsByPlanId(planId));
    }
}
