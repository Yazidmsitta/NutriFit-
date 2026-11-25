package com.example.Smart_NutriFit.service;

import com.example.Smart_NutriFit.dto.response.UserProfileResponse;

public interface UserService {

    UserProfileResponse getCurrentUserProfile(String email);
}

