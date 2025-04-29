package com.usuarios.application.port;

import java.util.List;

import com.usuarios.domain.Menu;

public interface MenuReposityoryPort {

	List<Menu> findMenuByRole(String role);
}