package com.example.Smart_NutriFit.dto.response;

import com.example.Smart_NutriFit.entity.Objectif;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NutritionPlanResponse {

    private int bmr;
    private int tdee;
    private int targetCalories;
    private int targetProteins;
    private int targetCarbs;
    private int targetFats;
    private Objectif objectif;
    private String advice;
}

