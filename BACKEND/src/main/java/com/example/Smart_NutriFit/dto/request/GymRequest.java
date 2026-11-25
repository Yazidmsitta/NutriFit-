package com.example.Smart_NutriFit.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GymRequest {

    @NotBlank(message = "Le nom de la salle est obligatoire")
    private String nom;

    @NotBlank(message = "L'adresse est obligatoire")
    private String adresse;

    @NotBlank(message = "La ville est obligatoire")
    private String ville;

    private Double latitude;

    private Double longitude;

    @NotNull(message = "Le prix mensuel est obligatoire")
    @Positive(message = "Le prix mensuel doit être supérieur à 0")
    private BigDecimal prixMensuel;

    private Double noteMoyenne;

    private Set<String> equipements;
}

