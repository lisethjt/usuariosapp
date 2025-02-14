package com.usuarios.infrastructure.service.auth;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.usuarios.application.usercase.GoogleAuthenticationService;
import com.usuarios.infrastructure.entities.UserEntity;
import com.usuarios.infrastructure.payload.auth.GoogleRequest;
import com.usuarios.infrastructure.payload.auth.LoginResponse;
import com.usuarios.infrastructure.repository.UserRepository;

@Service
public class GoogleAuthenticationServiceImpl implements GoogleAuthenticationService {

	@Value("${google.client.id}")
	private String clientId;

	@Value("${google.client.secret}")
	private String clientSecret;
	
	private UserRepository userRepository;
		
	
	public GoogleAuthenticationServiceImpl(UserRepository userRepository) {		
		this.userRepository = userRepository;
	}


	@Override
	public LoginResponse authenticate(GoogleRequest request) {
		LoginResponse loginResponse = new LoginResponse();
		try {

			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
					new NetHttpTransport(),
					new JacksonFactory())
					.setAudience(Collections.singletonList(clientId))
					.build();

			GoogleIdToken idToken = null;

			idToken = verifier.verify(request.getToken());

			if (idToken != null) {
				GoogleIdToken.Payload payload = idToken.getPayload();
				String userId = payload.getSubject();
				String email = payload.getEmail().toString();
				boolean emailVerified = Boolean.valueOf(payload.getEmailVerified());
				String name = (String) payload.get("name");
				
				//validarEmail
				Optional<UserEntity> user = this.userRepository.findByEmail(email);
				if(!user.isPresent()) {
					this.userRepository.save(UserEntity.builder()
	                           .name(name)
	                           .password(name)
	                           .email(email)
	                           .image("")
	                           .role("")
	                           .google(true)
	                           .build()
                    );
				}		
								
				loginResponse.setAccessToken(JwtHelper.generateToken(email));
				loginResponse.setMessage("Autenticación exitosa");
				return loginResponse;
			} else {
				loginResponse.setMessage("Token de autenticación no válido");
				return loginResponse;				
			}

		} catch (GeneralSecurityException | IOException e) {
			loginResponse.setMessage("Token de autenticación no válido.. ");
			return loginResponse;				
		}
	}
}