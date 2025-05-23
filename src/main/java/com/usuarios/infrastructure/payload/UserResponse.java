package com.usuarios.infrastructure.payload;

import java.util.List;

import com.usuarios.domain.Menu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponse {

	private UserDto user;
	private String token;
	private MessageResponse message;
	private List<Menu> menu;
}