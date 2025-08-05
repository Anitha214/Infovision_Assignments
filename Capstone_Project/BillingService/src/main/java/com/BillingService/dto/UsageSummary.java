package com.BillingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsageSummary {
    private String logId;
    private Long customerId;
    private Long subscribedPlanId;
    private Double mbUsed;
    private Integer minutesUsed;
    private LocalDateTime loggedAt;
}
