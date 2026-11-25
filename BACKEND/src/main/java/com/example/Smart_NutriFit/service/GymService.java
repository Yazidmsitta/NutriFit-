package com.example.Smart_NutriFit.service;

import com.example.Smart_NutriFit.dto.request.GymRequest;
import com.example.Smart_NutriFit.dto.response.GymResponse;

import java.math.BigDecimal;
import java.util.List;

public interface GymService {

    List<GymResponse> rechercherSalles(String ville, BigDecimal prixMax);

    GymResponse creerSalle(GymRequest request);

    GymResponse getSalleById(Long id);

    void supprimerSalle(Long id);
}

