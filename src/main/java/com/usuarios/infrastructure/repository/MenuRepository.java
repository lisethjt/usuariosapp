package com.usuarios.infrastructure.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuarios.infrastructure.entities.MenuEntity;

public interface MenuRepository extends JpaRepository<MenuEntity, Long> {

	List<MenuEntity> findByRole(String role);
}