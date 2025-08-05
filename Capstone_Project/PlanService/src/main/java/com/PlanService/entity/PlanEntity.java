package com.PlanService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "mobile_plans")
@AllArgsConstructor
@NoArgsConstructor
public class PlanEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planId;

    private String planName;

    private String planCategory;

    private Double planPrice;

    private String planValidity;

    private String planFeatures;

    private Double costPerGb;

    private Double costPerMinute;
}
