package br.com.lucasbdourado.library.rest.authentication;

import br.com.lucasbdourado.library.dto.authentication.AuthenticationDTO;
import br.com.lucasbdourado.library.entity.user.User;
import br.com.lucasbdourado.library.exception.UnauthorizedException;
import br.com.lucasbdourado.library.service.user.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationREST
{

	@Autowired
	private final IUserService service;

	public AuthenticationREST(IUserService service)
	{
		this.service = service;
	}

	@PostMapping("/login")
	@Operation(summary = "Logar com um usuário")
	public ResponseEntity<Object> login(@RequestBody AuthenticationDTO authenticationDTO)
	{
		try
		{
			return ResponseEntity.ok().body(service.login(authenticationDTO));
		}
		catch (UnauthorizedException e)
		{

			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping("/register")
	@Operation(summary = "Registrar um usuário")
	public ResponseEntity<Object> register(@RequestBody User user)
	{
		try
		{
			return ResponseEntity.ok().body(service.register(user));
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
