package com.usuarios.infrastructure.repository;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.usuarios.application.port.MenuReposityoryPort;
import com.usuarios.domain.Menu;
import com.usuarios.infrastructure.mapper.MenuDboMapper;

@Repository
public class MenuRespositoryPortImpl implements MenuReposityoryPort {

	private MenuRepository menuRepository;

	public MenuRespositoryPortImpl(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}

	@Override
	public List<Menu> findMenuByRole(String role) {
		return menuRepository.findByRole(role).stream().map(MenuDboMapper::toMenu)
				.collect(Collectors.toList());
	}
}