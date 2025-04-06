package br.com.lucasbdourado.library.service.user.implement;

import br.com.lucasbdourado.library.dto.authentication.AuthenticationDTO;
import br.com.lucasbdourado.library.entity.user.User;
import br.com.lucasbdourado.library.exception.UnauthorizedException;
import br.com.lucasbdourado.library.exception.UniqueFieldException;
import br.com.lucasbdourado.library.repository.user.UserRepository;
import br.com.lucasbdourado.library.service.user.IUserService;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService
{
	private static final String NOT_FOUND = "Not Found";

	@Autowired
	private final UserRepository repository;

	public UserService(UserRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public User login(AuthenticationDTO authentication)
	{
		User user = repository.findByEmail(authentication.email());

		if (Objects.isNull(user) || Boolean.FALSE.equals(user.isActive()))
		{
			throw new UnauthorizedException("Não existe uma conta associada ao e-mail informado.");
		}

		return user;
	}

	@Override
	public User register(User user)
	{
		if (repository.existsByEmail(user.getEmail()))
		{
			throw new UniqueFieldException("Já existe uma conta associada ao e-mail informado.");
		}

		User newUser = new User();

		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());

		return repository.save(newUser);
	}
}
