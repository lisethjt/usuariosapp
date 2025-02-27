package com.usuarios.application.usercase;

import java.util.List;

import com.usuarios.domain.User;

public interface AdminUserService {

	User add(User user);

	List<User> findAll();

	User update(User user, Long id);

	void deleteUser(Long id);
	
	User updateImage(String image, Long id);
	
	List<User> findAll(int page, int size);
	
	List<User> findByName(String name);
}