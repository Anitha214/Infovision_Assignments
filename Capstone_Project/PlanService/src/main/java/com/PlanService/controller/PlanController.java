package com.PlanService.controller;

import com.PlanService.dto.PlanRequestDTO;
import com.PlanService.dto.PlanResponseDTO;
import com.PlanService.service.PlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plan")
public class PlanController {

    @Autowired
    private PlanService planService;

    @GetMapping("/all")
    public ResponseEntity<List<PlanResponseDTO>> getAllPlans() {
        return ResponseEntity.ok(planService.fetchAllPlans());
    }

    @GetMapping("/{planId}")
    public ResponseEntity<PlanResponseDTO> getPlanById(@PathVariable Long planId) {
        return ResponseEntity.ok(planService.fetchPlanById(planId));
    }

    @PostMapping("/add")
    public ResponseEntity<PlanResponseDTO> createPlan(@RequestBody PlanRequestDTO planRequestDTO) {
        PlanResponseDTO createdPlan = planService.addNewPlan(planRequestDTO);
        return ResponseEntity.ok(createdPlan);
    }

    @PutMapping("/modify/{planId}")
    public ResponseEntity<PlanResponseDTO> updatePlan(@PathVariable Long planId, @RequestBody PlanRequestDTO planRequestDTO) {
        PlanResponseDTO updatedPlan = planService.updateExistingPlan(planId, planRequestDTO);
        return ResponseEntity.ok(updatedPlan);
    }

    @DeleteMapping("/del/{planId}")
    public ResponseEntity<Void> deletePlan(@PathVariable Long planId) {
        planService.removePlan(planId);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<List<PlanResponseDTO>> getPlansByCategory(@PathVariable String category) {
        return ResponseEntity.ok(planService.fetchPlansByCategory(category));
    }
}
