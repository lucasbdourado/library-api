package br.com.lucasbdourado.library.filter;

import br.com.lucasbdourado.library.exception.InvalidTokenException;
import br.com.lucasbdourado.library.service.jwt.JWTService;
import br.com.lucasbdourado.library.service.user.IUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
	private final JWTService jwtService;
	private final IUserService userService;

	public JwtAuthenticationFilter(JWTService jwtService, IUserService userService) {
		this.jwtService = jwtService;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response,
		@NonNull FilterChain filterChain) throws ServletException, IOException
	{
		final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authHeader == null || Boolean.FALSE.equals(authHeader.startsWith("Bearer ")))
		{
			filterChain.doFilter(request, response);

			return;
		}

		final String token = authHeader.substring(7);

		final String email = jwtService.extractEmail(token);

		if (Objects.nonNull(email) && SecurityContextHolder.getContext().getAuthentication() == null)
		{
			if (email.isBlank() || Boolean.FALSE.equals(jwtService.isTokenValid(token, email)))
			{
				throw new InvalidTokenException("Token Invalido ou Expirado");
			}

			UserDetails userDetails = userService.findByEmail(email);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
				userDetails, null, userDetails.getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(authToken);
		}

		filterChain.doFilter(request, response);
	}
}
