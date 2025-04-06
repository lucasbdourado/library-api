package br.com.lucasbdourado.library.service.user;

import br.com.lucasbdourado.library.entity.user.User;
import br.com.lucasbdourado.library.exception.NotFoundException;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserService
{
	UserDetails findByEmail(String email) throws NotFoundException;
}
