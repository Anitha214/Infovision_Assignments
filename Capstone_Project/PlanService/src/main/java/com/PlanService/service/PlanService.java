package com.PlanService.service;

import com.PlanService.dto.PlanRequestDTO;
import com.PlanService.dto.PlanResponseDTO;
import com.PlanService.entity.PlanEntity;
import com.PlanService.exception.ResourceNotFoundException;
import com.PlanService.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlanService {

    @Autowired
    private PlanRepository planRepository;

    public List<PlanResponseDTO> fetchAllPlans() {
        return planRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    public PlanResponseDTO addNewPlan(PlanRequestDTO planRequestDTO) {
        PlanEntity planEntity = convertToEntity(planRequestDTO);
        PlanEntity savedPlan = planRepository.save(planEntity);
        return convertToResponseDTO(savedPlan);
    }

    public PlanResponseDTO fetchPlanById(Long planId) {
        PlanEntity planEntity = planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id: " + planId));
        return convertToResponseDTO(planEntity);
    }

    public void removePlan(Long planId) {
        planRepository.deleteById(planId);
    }

    public PlanResponseDTO updateExistingPlan(Long planId, PlanRequestDTO updatedPlanDTO) {
        PlanEntity existingPlan = planRepository.findById(planId)
                .orElseThrow(() -> new ResourceNotFoundException("Plan not found with id: " + planId));

        existingPlan.setPlanName(updatedPlanDTO.getPlanName());
        existingPlan.setPlanCategory(updatedPlanDTO.getPlanCategory());
        existingPlan.setPlanPrice(updatedPlanDTO.getPlanPrice());
        existingPlan.setPlanValidity(updatedPlanDTO.getPlanValidity());
        existingPlan.setPlanFeatures(updatedPlanDTO.getPlanFeatures());
        existingPlan.setCostPerGb(updatedPlanDTO.getCostPerGb());
        existingPlan.setCostPerMinute(updatedPlanDTO.getCostPerMinute());

        PlanEntity updatedPlan = planRepository.save(existingPlan);
        return convertToResponseDTO(updatedPlan);
    }

    // New feature: find plans by category
    public List<PlanResponseDTO> fetchPlansByCategory(String category) {
        List<PlanEntity> plans = planRepository.findByPlanCategoryIgnoreCase(category);
        return plans.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Mapper methods
    private PlanResponseDTO convertToResponseDTO(PlanEntity planEntity) {
        PlanResponseDTO dto = new PlanResponseDTO();
        dto.setPlanId(planEntity.getPlanId());
        dto.setPlanName(planEntity.getPlanName());
        dto.setPlanCategory(planEntity.getPlanCategory());
        dto.setPlanPrice(planEntity.getPlanPrice());
        dto.setPlanValidity(planEntity.getPlanValidity());
        dto.setPlanFeatures(planEntity.getPlanFeatures());
        dto.setCostPerGb(planEntity.getCostPerGb());
        dto.setCostPerMinute(planEntity.getCostPerMinute());
        return dto;
    }

    private PlanEntity convertToEntity(PlanRequestDTO dto) {
        PlanEntity entity = new PlanEntity();
        entity.setPlanName(dto.getPlanName());
        entity.setPlanCategory(dto.getPlanCategory());
        entity.setPlanPrice(dto.getPlanPrice());
        entity.setPlanValidity(dto.getPlanValidity());
        entity.setPlanFeatures(dto.getPlanFeatures());
        entity.setCostPerGb(dto.getCostPerGb());
        entity.setCostPerMinute(dto.getCostPerMinute());
        return entity;
    }
}
