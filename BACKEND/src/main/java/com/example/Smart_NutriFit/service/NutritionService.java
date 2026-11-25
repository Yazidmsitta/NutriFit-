package com.example.Smart_NutriFit.service;

import com.example.Smart_NutriFit.dto.response.NutritionPlanResponse;

public interface NutritionService {

    NutritionPlanResponse calculatePlanForUser(String email);
}

