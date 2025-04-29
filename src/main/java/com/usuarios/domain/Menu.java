package com.usuarios.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Menu {

	//private Long id;
	@JsonProperty("titulo")
	private String title;
	@JsonProperty("icono")
	private String icon;	
	//private String role;
	
	private List<SubMenu> submenu;
}