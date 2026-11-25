package com.example.Smart_NutriFit.dto.response;

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
public class GymResponse {

    private Long id;
    private String nom;
    private String adresse;
    private String ville;
    private Double latitude;
    private Double longitude;
    private BigDecimal prixMensuel;
    private Double noteMoyenne;
    private Set<String> equipements;
}

