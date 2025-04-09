package br.com.lucasbdourado.library.dto.state;

import com.fasterxml.jackson.annotation.JsonProperty;

public record StateJsonDTO(
	@JsonProperty("Id") Long id,
	@JsonProperty("CodigoUf") String code,
	@JsonProperty("Nome") String name,
	@JsonProperty("Uf") String acronym
)
{
}
