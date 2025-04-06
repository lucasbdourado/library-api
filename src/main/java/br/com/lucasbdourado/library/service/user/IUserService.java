package br.com.lucasbdourado.library.service.user;

import br.com.lucasbdourado.library.dto.authentication.AuthenticationDTO;
import br.com.lucasbdourado.library.entity.user.User;
import br.com.lucasbdourado.library.exception.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService
{
	UserDetails findByEmail(String email) throws NotFoundException;

	User login(AuthenticationDTO authentication);

	User register(User user);
}
