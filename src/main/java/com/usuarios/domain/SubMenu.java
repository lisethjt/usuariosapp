package com.usuarios.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubMenu {

	//private Long id;
	@JsonProperty("titulo")
	private String title;
	private String url;	
	//private String icon;	
}