package com.example.Smart_NutriFit.controller;

import com.example.Smart_NutriFit.dto.request.GymRequest;
import com.example.Smart_NutriFit.dto.response.GymResponse;
import com.example.Smart_NutriFit.service.GymService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/gyms")
@RequiredArgsConstructor
public class GymController {

    private final GymService gymService;

    @GetMapping
    public ResponseEntity<List<GymResponse>> rechercherSalles(
            @RequestParam(required = false) String ville,
            @RequestParam(required = false) BigDecimal prixMax
    ) {
        List<GymResponse> gyms = gymService.rechercherSalles(ville, prixMax);
        return ResponseEntity.ok(gyms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GymResponse> getSalleById(@PathVariable Long id) {
        GymResponse gym = gymService.getSalleById(id);
        return ResponseEntity.ok(gym);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<GymResponse> creerSalle(@Valid @RequestBody GymRequest request) {
        GymResponse response = gymService.creerSalle(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> supprimerSalle(@PathVariable Long id) {
        gymService.supprimerSalle(id);
        return ResponseEntity.noContent().build();
    }
}

