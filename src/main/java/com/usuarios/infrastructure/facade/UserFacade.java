package com.usuarios.infrastructure.facade;

import com.usuarios.infrastructure.payload.ImageRequest;
import com.usuarios.infrastructure.payload.ImageResponse;
import com.usuarios.infrastructure.payload.UserListResponse;
import com.usuarios.infrastructure.payload.UserRequest;
import com.usuarios.infrastructure.payload.UserResponse;

public interface UserFacade {

	UserResponse add(UserRequest user);

	UserListResponse findAll();

	UserResponse update(UserRequest user, Long id);

	UserResponse deleteUser(Long id);
	
	ImageResponse updateImage(ImageRequest image, Long id);
	
	UserListResponse findAll(int page, int size);

	UserListResponse findByName(String name);
}