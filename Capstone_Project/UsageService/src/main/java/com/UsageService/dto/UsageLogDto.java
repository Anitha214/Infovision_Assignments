package com.UsageService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsageLogDto {
    private String logId;
    private Long customerId;
    private Long subscribedPlanId;
    private Double mbUsed;
    private Integer minutesUsed;
    private LocalDateTime loggedAt;
}
