package com.example.Smart_NutriFit.service;

import com.example.Smart_NutriFit.dto.response.UserProfileResponse;
import com.example.Smart_NutriFit.entity.User;
import com.example.Smart_NutriFit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserProfileResponse getCurrentUserProfile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec l'email : " + email));

        return UserProfileResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().name())
                .age(user.getAge())
                .taille(user.getTaille())
                .poids(user.getPoids())
                .objectif(user.getObjectif())
                .createdAt(user.getCreatedAt())
                .build();
    }
}

