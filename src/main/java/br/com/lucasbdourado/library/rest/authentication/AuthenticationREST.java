package br.com.lucasbdourado.library.rest.authentication;

import br.com.lucasbdourado.library.dto.authentication.AuthenticationRequestDto;
import br.com.lucasbdourado.library.dto.authentication.AuthenticationResponseDto;
import br.com.lucasbdourado.library.dto.user.UserResponse;
import br.com.lucasbdourado.library.entity.user.User;
import br.com.lucasbdourado.library.exception.UnauthorizedException;
import br.com.lucasbdourado.library.mapper.user.UserMapper;
import br.com.lucasbdourado.library.service.authentication.IAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationREST
{
	private final IAuthenticationService service;

	private final AuthenticationManager authenticationManager;

	public AuthenticationREST(IAuthenticationService service,
		AuthenticationManager authenticationManager)
	{
		this.service = service;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	@Operation(summary = "Logar com um usuário")
	public ResponseEntity<Object> login(@RequestBody AuthenticationRequestDto authenticationRequestDto)
	{
		try
		{
			Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequestDto.email(),
					authenticationRequestDto.password()));

			return ResponseEntity.status(HttpStatus.OK)
				.body(new AuthenticationResponseDto(service.login(authentication)));
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
			UserResponse userResponse = UserMapper.toResponse(service.register(user));

			return ResponseEntity.ok().body(userResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
