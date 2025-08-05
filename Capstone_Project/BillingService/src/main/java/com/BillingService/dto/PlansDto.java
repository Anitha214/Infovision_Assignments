package com.BillingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlansDto {
    private Long planId;
    private String planName;
    private String planCategory;
    private Double planPrice;
    private String planValidity;
    private String planFeatures;
    private Double costPerGb;
    private Double costPerMinute;
}
