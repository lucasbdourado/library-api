package br.com.lucasbdourado.library.dto.city;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CityJsonDTO(
	@JsonProperty("Id") Long id,
	@JsonProperty("Codigo") Long code,
	@JsonProperty("Nome") String name,
	@JsonProperty("Uf") String stateAcronym)
{
}
