package com.example.Smart_NutriFit.controller;

import com.example.Smart_NutriFit.dto.response.NutritionPlanResponse;
import com.example.Smart_NutriFit.service.NutritionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/nutrition")
@RequiredArgsConstructor
public class NutritionController {

    private final NutritionService nutritionService;

    @GetMapping("/my-plan")
    public ResponseEntity<NutritionPlanResponse> getMyNutritionPlan(@AuthenticationPrincipal UserDetails userDetails) {
        NutritionPlanResponse plan = nutritionService.calculatePlanForUser(userDetails.getUsername());
        return ResponseEntity.ok(plan);
    }
}

