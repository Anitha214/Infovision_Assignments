package com.UsageService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "usage_logs")
public class UsageLog {

    @Id
    private String logId;

    private Long customerId;

    private Long subscribedPlanId;

    private Double mbUsed;

    private Integer minutesUsed;

    private LocalDateTime loggedAt;
}
