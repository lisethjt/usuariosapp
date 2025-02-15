package com.usuarios.infrastructure.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usuarios.application.usercase.AuthenticationService;
import com.usuarios.application.usercase.GoogleAuthenticationService;
import com.usuarios.infrastructure.payload.UserResponse;
import com.usuarios.infrastructure.payload.auth.GoogleRequest;
import com.usuarios.infrastructure.payload.auth.LoginRequest;
import com.usuarios.infrastructure.payload.auth.LoginResponse;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthController {

	private AuthenticationService authenticationService;
	private GoogleAuthenticationService googleAuthenticationService;

	public AuthController(AuthenticationService authenticationService,
			GoogleAuthenticationService googleAuthenticationService) {
		this.authenticationService = authenticationService;
		this.googleAuthenticationService = googleAuthenticationService;
	}

	@PostMapping(value = "/login")
	public LoginResponse authenticate(@RequestBody LoginRequest request) {
		return this.authenticationService.authenticate(request);
	}

	@PostMapping("/google")
	public LoginResponse authenticate(@RequestBody GoogleRequest request) {
		return this.googleAuthenticationService.authenticate(request);
	}
	
	@GetMapping("/renew")
	public UserResponse renew(@RequestHeader("token") String token) {		
		return this.authenticationService.renewToken(token);
	}
}