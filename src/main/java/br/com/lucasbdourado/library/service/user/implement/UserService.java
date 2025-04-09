package br.com.lucasbdourado.library.service.user.implement;

import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.repository.user.UserRepository;
import br.com.lucasbdourado.library.service.user.IUserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService
{
	private static final String NOT_FOUND = "Not Found";

	private final UserRepository repository;

	public UserService(UserRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public UserDetails findByEmail(String email) throws NotFoundException
	{
		return repository.findByEmail(email).orElseThrow(() -> new NotFoundException(NOT_FOUND));
	}
}
