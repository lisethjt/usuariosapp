package com.usuarios.infrastructure.service.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.usuarios.application.port.MenuReposityoryPort;
import com.usuarios.application.usercase.AuthenticationService;
import com.usuarios.domain.Menu;
import com.usuarios.infrastructure.entities.UserEntity;
import com.usuarios.infrastructure.mapper.UserDboMapper;
import com.usuarios.infrastructure.payload.MessageResponse;
import com.usuarios.infrastructure.payload.UserDto;
import com.usuarios.infrastructure.payload.UserResponse;
import com.usuarios.infrastructure.payload.auth.LoginRequest;
import com.usuarios.infrastructure.payload.auth.LoginResponse;
import com.usuarios.infrastructure.repository.UserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.security.SignatureException;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	private final AuthenticationManager authenticationManager;
	private UserRepository userRepository;	
	private MenuReposityoryPort menuReposityory;


	public AuthenticationServiceImpl(AuthenticationManager authenticationManager, UserRepository userRepository, MenuReposityoryPort menuReposityory) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;	
		this.menuReposityory = menuReposityory;
	}

	@Override
	public LoginResponse authenticate(LoginRequest loginRequest) {
		// Autenticamos al usuario
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
		String token = JwtHelper.generateToken(loginRequest.getEmail());
		return LoginResponse.builder()
				.accessToken(token)
				.message("ok")
				.build();
	}

	@Override
	public UserResponse renewToken(String token) {
		MessageResponse messageResponse = new MessageResponse();
		messageResponse.setCode("201");
		messageResponse.setMessage("Exito");
		UserDto userDto = new UserDto();	
		List<Menu> menu = new ArrayList<>();
		try {
			token = token.replace("Bearer ", "");
			Claims claims = JwtHelper.decodeToken(token);
			UserEntity user = this.userRepository.findByEmail(claims.getSubject())
					.orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + claims.getSubject()));
			userDto = UserDboMapper.toUserDto(user);
			menu = this.menuReposityory.findMenuByRole(userDto.getRole());
			
		} catch (SignatureException e) {
			messageResponse.setCode("403");
			messageResponse.setMessage("Token inválido");
		}		
		return UserResponse.builder()
				           .message(messageResponse)
				           .token(token)
				           .user(userDto)	
				           .menu(menu)
				           .build();
	}
}