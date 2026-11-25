package com.example.Smart_NutriFit.repository;

import com.example.Smart_NutriFit.entity.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface GymRepository extends JpaRepository<Gym, Long> {

    List<Gym> findByVilleAndPrixMensuelLessThanEqual(String ville, BigDecimal prixMax);

    List<Gym> findByVilleIgnoreCaseAndPrixMensuelLessThanEqual(String ville, BigDecimal prixMax);

    List<Gym> findByVilleIgnoreCase(String ville);

    List<Gym> findByPrixMensuelLessThanEqual(BigDecimal prixMax);

    @Query("SELECT g FROM Gym g WHERE " +
           "(:ville IS NULL OR LOWER(g.ville) = LOWER(:ville)) AND " +
           "(:prixMax IS NULL OR g.prixMensuel <= :prixMax)")
    List<Gym> rechercherSalles(@Param("ville") String ville, @Param("prixMax") BigDecimal prixMax);
}

