package br.com.lucasbdourado.library.dto.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AuthenticationDTO(
	@NotNull(message = "O e-mail não pode ser nulo") @NotBlank(message = "O e-mail precisa estar preenchido") @Email(message = "O endereço de e-mail deve ser um e-mail válido") String email,
	@NotNull(message = "A senha não pode ser nula") @NotBlank(message = "A senha precisa ser preenchida") String password)
{
}
