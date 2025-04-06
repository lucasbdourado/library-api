package br.com.lucasbdourado.library.service.user.implement;

import br.com.lucasbdourado.library.dto.authentication.AuthenticationDTO;
import br.com.lucasbdourado.library.entity.user.User;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.exception.UnauthorizedException;
import br.com.lucasbdourado.library.exception.UniqueFieldException;
import br.com.lucasbdourado.library.repository.user.UserRepository;
import br.com.lucasbdourado.library.service.user.IUserService;
import java.util.GregorianCalendar;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService, UserDetailsService
{
	private static final String NOT_FOUND = "Not Found";

	@Autowired
	private final UserRepository repository;

	public UserService(UserRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
	}

	@Override
	public UserDetails findByEmail(String email) throws NotFoundException
	{
		return repository.findByEmail(email).orElseThrow(() -> new NotFoundException(NOT_FOUND));
	}

	@Override
	public User login(AuthenticationDTO authentication)
	{
		Optional<User> optionalUser = repository.findByEmail(authentication.email());

		if (optionalUser.isEmpty() || Boolean.FALSE.equals(optionalUser.get().isActive()))
		{
			throw new UnauthorizedException("Não existe uma conta associada ao e-mail informado.");
		}

		User user = optionalUser.get();

		user.setLastLoginDate(new GregorianCalendar());

		return repository.save(user);
	}

	@Override
	public User register(User user)
	{
		if (repository.existsByEmail(user.getEmail()))
		{
			throw new UniqueFieldException("Já existe uma conta associada ao e-mail informado.");
		}

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user.setCreationDate(new GregorianCalendar());
		user.setUpdateDate(new GregorianCalendar());

		return repository.save(user);
	}
}
