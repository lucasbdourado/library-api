package br.com.lucasbdourado.library.mapper.user;

import br.com.lucasbdourado.library.dto.user.UserResponse;
import br.com.lucasbdourado.library.entity.user.User;

public class UserMapper
{

	public static UserResponse toResponse(User user)
	{
		return new UserResponse(user.getId(), user.getEmail(), user.getUsername(), user.isActive(),
			user.getRole());
	}
}
