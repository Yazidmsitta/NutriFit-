package com.example.Smart_NutriFit.service;

import com.example.Smart_NutriFit.dto.request.LoginRequest;
import com.example.Smart_NutriFit.dto.request.RegisterRequest;
import com.example.Smart_NutriFit.dto.response.AuthResponse;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}

