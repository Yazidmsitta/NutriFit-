package com.example.Smart_NutriFit.dto.request;

import com.example.Smart_NutriFit.entity.Objectif;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "Le prénom est obligatoire")
    private String firstName;

    @NotBlank(message = "Le nom est obligatoire")
    private String lastName;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Format d'email invalide")
    private String email;

    @NotBlank(message = "Le mot de passe est obligatoire")
    @Size(min = 6, message = "Le mot de passe doit contenir au moins 6 caractères")
    private String password;

    @NotNull(message = "L'âge est obligatoire")
    @Min(value = 10, message = "L'âge minimum est de 10 ans")
    @Max(value = 120, message = "L'âge maximum est de 120 ans")
    private Integer age;

    @NotNull(message = "La taille est obligatoire")
    @Positive(message = "La taille doit être un nombre positif")
    private Double taille;

    @NotNull(message = "Le poids est obligatoire")
    @Positive(message = "Le poids doit être un nombre positif")
    private Double poids;

    @NotNull(message = "L'objectif est obligatoire (PERTE_POIDS, PRISE_MASSE, MAINTIEN)")
    private Objectif objectif;
}

