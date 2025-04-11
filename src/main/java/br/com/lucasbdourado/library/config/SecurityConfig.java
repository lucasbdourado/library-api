package br.com.lucasbdourado.library.config;

import br.com.lucasbdourado.library.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig
{

	private final JwtAuthenticationFilter jwtAuthFilter;

	private final UserDetailsService userDetailsService;

	public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter, UserDetailsService userDetailsService)
	{
		this.jwtAuthFilter = jwtAuthFilter;
		this.userDetailsService = userDetailsService;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception
	{
		return http.csrf(AbstractHttpConfigurer::disable)
			.sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll()
				.requestMatchers(HttpMethod.POST, "/authors/").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, "/authors/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/authors/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.POST, "/books/").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, "/books/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/books/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.POST, "/groups/").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, "/groups/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/groups/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.GET, "/operations").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.GET, "/operations/").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.GET, "/operations/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.POST, "/operations/").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, "/operations/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/operations/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.POST, "/publishers/").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, "/publishers/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/publishers/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.POST, "/libraries/").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.PUT, "/libraries/**").hasAuthority("ROLE_ADMIN")
				.requestMatchers(HttpMethod.DELETE, "/libraries/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated())
			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
	}

	@Bean
	public AuthenticationProvider authenticationProvider()
	{
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}

	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
		throws Exception
	{
		return config.getAuthenticationManager();
	}
}
