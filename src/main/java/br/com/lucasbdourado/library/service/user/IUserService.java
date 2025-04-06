package br.com.lucasbdourado.library.service.user;

import br.com.lucasbdourado.library.dto.authentication.AuthenticationDTO;
import br.com.lucasbdourado.library.entity.user.User;

public interface IUserService
{
	User login(AuthenticationDTO authentication);

	User register(User user);
}
