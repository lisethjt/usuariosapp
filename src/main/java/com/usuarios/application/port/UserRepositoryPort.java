package com.usuarios.application.port;

import java.util.List;

import com.usuarios.domain.User;

public interface UserRepositoryPort {

	User add(User user);

	List<User> findAll();
	
	User update(User user, Long id);	
	
	void deleteUser(Long id);
	
	User updateById(Long id, String image);
	
	List<User> findAll(int page, int size);
	
	List<User> findByName(String name);
}