package br.com.lucasbdourado.library.service.authentication.implement;

import br.com.lucasbdourado.library.entity.user.User;
import br.com.lucasbdourado.library.exception.UnauthorizedException;
import br.com.lucasbdourado.library.exception.UniqueFieldException;
import br.com.lucasbdourado.library.repository.user.UserRepository;
import br.com.lucasbdourado.library.service.authentication.IAuthenticationService;
import br.com.lucasbdourado.library.service.jwt.JWTService;
import java.util.GregorianCalendar;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService, UserDetailsService
{
	@Autowired
	private final UserRepository repository;

	@Autowired
	private JWTService jwtService;


	public AuthenticationService(UserRepository repository)
	{
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
	{
		return repository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));
	}

	public String login(Authentication authentication)
	{
		User user = (User) authentication.getPrincipal();

		if(Objects.isNull(user))
		{
			throw new UnauthorizedException("Não foi possível autenticar o usuário.");
		}

		user.setLastLoginDate(new GregorianCalendar());

		repository.save(user);

		return jwtService.generateToken(user.getEmail());
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
