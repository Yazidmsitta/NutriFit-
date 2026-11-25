package com.example.Smart_NutriFit.repository;

import com.example.Smart_NutriFit.entity.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {

    Optional<Equipement> findByNomIgnoreCase(String nom);
}

