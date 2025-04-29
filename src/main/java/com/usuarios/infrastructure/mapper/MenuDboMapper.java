package com.usuarios.infrastructure.mapper;

import java.util.stream.Collectors;

import com.usuarios.domain.Menu;
import com.usuarios.domain.SubMenu;
import com.usuarios.infrastructure.entities.MenuEntity;
import com.usuarios.infrastructure.entities.SubMenuEntity;

public final class MenuDboMapper {
	
	public static MenuEntity toMenuEntity(Menu menu) {
		return MenuEntity.builder()	
				//.id(menu.getId())
				.title(menu.getTitle())
				.icon(menu.getIcon())
			//	.role(menu.getRole())				
				.build();
	}
	
	public static Menu toMenu(MenuEntity menu) {
		return Menu.builder()
				//.id(menu.getId())
				.title(menu.getTitle())
				.icon(menu.getIcon())
				//.role(menu.getRole())	
				.submenu(menu.getSubmenus().stream()
						.map(MenuDboMapper::toSubMenu)
						.collect(Collectors.toList()))
				.build();
	}	
	
	public static SubMenu toSubMenu(SubMenuEntity submenu) {
		return SubMenu.builder()
				//.id(submenu.getId())
				.title(submenu.getTitle())
				//.icon(submenu.getIcon())
				.url(submenu.getUrl())				
				.build();
	}
	
}