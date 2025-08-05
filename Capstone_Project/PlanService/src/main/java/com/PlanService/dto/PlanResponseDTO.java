package com.PlanService.dto;

import lombok.Data;

@Data
public class PlanResponseDTO {
    private Long planId;
    private String planName;
    private String planCategory;
    private Double planPrice;
    private String planValidity;
    private String planFeatures;
    private Double costPerGb;
    private Double costPerMinute;
}
