package com.proyect.service;

import com.proyect.dto.AuthResponse;
import com.proyect.dto.LoginRequest;
import com.proyect.dto.RegisterRequest;

public interface IAuthService {

    AuthResponse login (LoginRequest loginRequest) ;

    AuthResponse register(RegisterRequest registerRequest);
}
