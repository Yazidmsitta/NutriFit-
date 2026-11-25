package com.example.Smart_NutriFit.service;

import com.example.Smart_NutriFit.dto.request.GymRequest;
import com.example.Smart_NutriFit.dto.response.GymResponse;
import com.example.Smart_NutriFit.entity.Equipement;
import com.example.Smart_NutriFit.entity.Gym;
import com.example.Smart_NutriFit.exception.ResourceNotFoundException;
import com.example.Smart_NutriFit.repository.EquipementRepository;
import com.example.Smart_NutriFit.repository.GymRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GymServiceImpl implements GymService {

    private final GymRepository gymRepository;
    private final EquipementRepository equipementRepository;

    @Override
    @Transactional(readOnly = true)
    public List<GymResponse> rechercherSalles(String ville, BigDecimal prixMax) {
        String villeNormalisee = (ville != null && !ville.trim().isEmpty()) ? ville.trim() : null;

        List<Gym> gyms;
        if (villeNormalisee != null && prixMax != null) {
            gyms = gymRepository.findByVilleIgnoreCaseAndPrixMensuelLessThanEqual(villeNormalisee, prixMax);
        } else if (villeNormalisee != null) {
            gyms = gymRepository.findByVilleIgnoreCase(villeNormalisee);
        } else if (prixMax != null) {
            gyms = gymRepository.findByPrixMensuelLessThanEqual(prixMax);
        } else {
            gyms = gymRepository.findAll();
        }

        return gyms.stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public GymResponse creerSalle(GymRequest request) {
        Set<Equipement> equipements = new HashSet<>();
        if (request.getEquipements() != null && !request.getEquipements().isEmpty()) {
            for (String nomEquipement : request.getEquipements()) {
                if (nomEquipement != null && !nomEquipement.trim().isEmpty()) {
                    String nomTrim = nomEquipement.trim();
                    Equipement equipement = equipementRepository.findByNomIgnoreCase(nomTrim)
                            .orElseGet(() -> equipementRepository.save(
                                    Equipement.builder()
                                            .nom(nomTrim)
                                            .build()
                            ));
                    equipements.add(equipement);
                }
            }
        }

        Gym gym = Gym.builder()
                .nom(request.getNom())
                .adresse(request.getAdresse())
                .ville(request.getVille())
                .latitude(request.getLatitude())
                .longitude(request.getLongitude())
                .prixMensuel(request.getPrixMensuel())
                .noteMoyenne(request.getNoteMoyenne() != null ? request.getNoteMoyenne() : 0.0)
                .equipements(equipements)
                .build();

        Gym savedGym = gymRepository.save(gym);
        return mapToResponse(savedGym);
    }

    @Override
    @Transactional(readOnly = true)
    public GymResponse getSalleById(Long id) {
        Gym gym = gymRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Salle de sport non trouvée avec l'identifiant : " + id));
        return mapToResponse(gym);
    }

    @Override
    @Transactional
    public void supprimerSalle(Long id) {
        if (!gymRepository.existsById(id)) {
            throw new ResourceNotFoundException("Impossible de supprimer : salle non trouvée avec l'id : " + id);
        }
        gymRepository.deleteById(id);
    }

    private GymResponse mapToResponse(Gym gym) {
        Set<String> equipementsNames = gym.getEquipements() != null
                ? gym.getEquipements().stream().map(Equipement::getNom).collect(Collectors.toSet())
                : Set.of();

        return GymResponse.builder()
                .id(gym.getId())
                .nom(gym.getNom())
                .adresse(gym.getAdresse())
                .ville(gym.getVille())
                .latitude(gym.getLatitude())
                .longitude(gym.getLongitude())
                .prixMensuel(gym.getPrixMensuel())
                .noteMoyenne(gym.getNoteMoyenne())
                .equipements(equipementsNames)
                .build();
    }
}

