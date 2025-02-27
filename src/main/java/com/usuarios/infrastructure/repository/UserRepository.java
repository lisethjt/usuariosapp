package com.usuarios.infrastructure.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usuarios.infrastructure.entities.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	List<UserEntity> findByNameLike(String name);

	Optional<UserEntity> findByEmail(String email);
}