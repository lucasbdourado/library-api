package br.com.lucasbdourado.library.service.authentication;

import org.springframework.security.core.Authentication;
import br.com.lucasbdourado.library.entity.user.User;

public interface IAuthenticationService
{
	String login(Authentication authentication);

	User register(User user);
}
