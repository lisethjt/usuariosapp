package com.usuarios.application.usercase;

import com.usuarios.infrastructure.payload.auth.GoogleRequest;
import com.usuarios.infrastructure.payload.auth.LoginResponse;

public interface GoogleAuthenticationService {

	LoginResponse authenticate(GoogleRequest request);
}