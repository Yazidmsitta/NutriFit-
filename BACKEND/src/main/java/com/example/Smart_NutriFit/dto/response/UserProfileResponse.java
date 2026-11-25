package com.example.Smart_NutriFit.dto.response;

import com.example.Smart_NutriFit.entity.Objectif;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileResponse {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String role;
    private Integer age;
    private Double taille;
    private Double poids;
    private Objectif objectif;
    private LocalDateTime createdAt;
}

