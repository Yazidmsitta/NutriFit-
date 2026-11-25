package com.example.Smart_NutriFit.service;

import com.example.Smart_NutriFit.dto.response.NutritionPlanResponse;
import com.example.Smart_NutriFit.entity.Objectif;
import com.example.Smart_NutriFit.entity.User;
import com.example.Smart_NutriFit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NutritionServiceImpl implements NutritionService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public NutritionPlanResponse calculatePlanForUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));

        double poids = user.getPoids() != null ? user.getPoids() : 70.0;
        double taille = user.getTaille() != null ? user.getTaille() : 175.0;
        int age = user.getAge() != null ? user.getAge() : 25;
        Objectif objectif = user.getObjectif() != null ? user.getObjectif() : Objectif.MAINTIEN;

        // Formule de Mifflin-St Jeor
        int bmr = (int) Math.round((10 * poids) + (6.25 * taille) - (5 * age) + 5);

        // TDEE (Dépense énergétique totale journalière avec niveau d'activité modéré = 1.375)
        int tdee = (int) Math.round(bmr * 1.375);

        int targetCalories;
        String advice;

        switch (objectif) {
            case PERTE_POIDS:
                targetCalories = tdee - 400;
                advice = "Déficit calorique modéré (-400 kcal) pour brûler les graisses tout en préservant la masse musculaire.";
                break;
            case PRISE_MASSE:
                targetCalories = tdee + 350;
                advice = "Léger surplus calorique (+350 kcal) pour stimuler l'hypertrophie musculaire sans prise excessive de gras.";
                break;
            case MAINTIEN:
            default:
                targetCalories = tdee;
                advice = "Équilibre énergétique isocalorique pour stabiliser le poids et optimiser la vitalité.";
                break;
        }

        // Cibles en macronutriments
        // Protéines : ~2.0g par kg de poids de corps
        int targetProteins = (int) Math.round(poids * 2.0);
        // Lipides : ~0.9g par kg de poids de corps
        int targetFats = (int) Math.round(poids * 0.9);

        // Glucides : apport restant (1g protéine = 4 kcal, 1g lipide = 9 kcal, 1g glucide = 4 kcal)
        int caloriesFromProteinAndFat = (targetProteins * 4) + (targetFats * 9);
        int remainingCaloriesForCarbs = Math.max(0, targetCalories - caloriesFromProteinAndFat);
        int targetCarbs = (int) Math.round(remainingCaloriesForCarbs / 4.0);

        return NutritionPlanResponse.builder()
                .bmr(bmr)
                .tdee(tdee)
                .targetCalories(targetCalories)
                .targetProteins(targetProteins)
                .targetCarbs(targetCarbs)
                .targetFats(targetFats)
                .objectif(objectif)
                .advice(advice)
                .build();
    }
}

